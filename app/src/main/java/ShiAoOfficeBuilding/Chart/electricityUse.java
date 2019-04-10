package ShiAoOfficeBuilding.Chart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shiaoofficebuilding.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ShiAoOfficeBuilding.tools.dateUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class electricityUse extends AppCompatActivity {
    private String count,roomnum="",thisyear="2019";
    private String url;
    private List<Float> userinfo;
    private List<Float> newuserinfo;
    private Context content;
    private List<String> mList;
    private List<String> mList2;
    private EditText edyear;
    private List<Entry> entries;



private returnData getwaterUse;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.electricity_layout);
        edyear=findViewById(R.id.year);
        mList=new ArrayList<>();
        mList2=new ArrayList<>();
        userinfo=new ArrayList<>();
        newuserinfo=new ArrayList<>();
        entries=new ArrayList<>();
        thisyear=dateUtils.getYear();
        Intent intent=getIntent();
        roomnum=intent.getStringExtra("roomnum");
        Log.v("ee","电 "+roomnum);
        try {
            thisyear = dateUtils.getYear();
            GetUrl(roomnum,thisyear);
            Log.d("ee","电 的  roomnum"+roomnum+"  thisyear "+thisyear);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public void GetUrl(String roomnum1,String yy) throws ParseException {

            Log.d("ff",yy);
            url = "http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=meterinfo" +
                "&buildguid=20180518063531-d1dcf9d1-3b98-4ddc-9588-e9bf55779f15&roomnum="+roomnum1+"&year="+yy;

        final OkHttpClient okHttpClient = new OkHttpClient();


        final Request request = new Request.Builder()

                .url(url)
                .get()
                .build();
        //异步方法
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(electricityUse.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                Log.d("ee","  电的  " +res);
                try {
                    JSONObject jsonObj = new JSONObject(res);

                    //获取结果
                    String result = jsonObj.getString("result");
                    count = jsonObj.getString("count");
                    //获取到的房间数据
                    String roomlist = jsonObj.getString("data");
                    //转换成JSON数组
                    JSONArray jsonArray = new JSONArray(roomlist);


                        //遍历获取数据
                        JSONObject jsonObj2 = jsonArray.getJSONObject(0);
                        //房间号
                        String year = jsonObj2.getString("year");
                        //房间
                        String usenum01 = jsonObj2.getString("usenum01");
                        //使用
                        String usenum02 = jsonObj2.getString("usenum02");
                        String usenum03 = jsonObj2.getString("usenum03");
                        String usenum04 = jsonObj2.getString("usenum04");
                        String usenum05 = jsonObj2.getString("usenum05");
                        String usenum06 = jsonObj2.getString("usenum06");
                        String usenum07 = jsonObj2.getString("usenum07");
                        String usenum08 = jsonObj2.getString("usenum08");
                        String usenum09 = jsonObj2.getString("usenum09");
                        String usenum10 = jsonObj2.getString("usenum10");
                        String usenum11 = jsonObj2.getString("usenum11");
                        String usenum12 = jsonObj2.getString("usenum12");


                        float usernum01 = usenum01.equals("")?0:Float.parseFloat(usenum01);
                        float usernum02 = usenum02.equals("")?0:Float.parseFloat(usenum02);
                        float usernum03 = usenum03.equals("")?0:Float.parseFloat(usenum03);
                        float usernum04 = usenum04.equals("")?0:Float.parseFloat(usenum04);
                        float usernum05 = usenum05.equals("")?0:Float.parseFloat(usenum05);
                        float usernum06 = usenum06.equals("")?0:Float.parseFloat(usenum06);
                        float usernum07 = usenum07.equals("")?0:Float.parseFloat(usenum07);
                        float usernum08 = usenum08.equals("")?0:Float.parseFloat(usenum08);
                        float usernum09 = usenum09.equals("")?0:Float.parseFloat(usenum09);
                        float usernum10 = usenum10.equals("")?0:Float.parseFloat(usenum10);
                        float usernum11 = usenum11.equals("")?0:Float.parseFloat(usenum11);
                        float usernum12 = usenum12.equals("")?0:Float.parseFloat(usenum12);



                        userinfo.add(usernum01);
                        userinfo.add(usernum02);
                        userinfo.add(usernum03);
                        userinfo.add(usernum04);
                        userinfo.add(usernum05);
                        userinfo.add(usernum06);
                        userinfo.add(usernum07);
                        userinfo.add(usernum08);
                        userinfo.add(usernum09);
                        userinfo.add(usernum10);
                        userinfo.add(usernum11);
                        userinfo.add(usernum12);


                    LineChart mLineChart = (LineChart) findViewById(R.id.lineChart);
                    //显示边界
                    mLineChart.setDrawBorders(true);
                    for(int i=1;i<13;i++)
                    {

                        mList.add(thisyear+"年"+i+"月");

                    }
                    //设置数据



                    double maxvalue=0.0;
                    for (int i = 0; i < 12; i++) {
                        entries.add(new Entry(i, userinfo.get(i)));
                        if(maxvalue<userinfo.get(i))
                        {
                            maxvalue=userinfo.get(i);
                        }
                    }

                    XAxis xAxis = mLineChart.getXAxis();
                    xAxis.setLabelCount(12, true);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setLabelRotationAngle(90);
                    xAxis.setValueFormatter(new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return mList.get((int) value); //mList为存有月份的集合
                        }
                    });
//Y轴
                    YAxis leftYAxis = mLineChart.getAxisLeft();
                    YAxis rightYAxis = mLineChart.getAxisRight();

                    leftYAxis.setAxisMinimum(0f);
                    leftYAxis.setAxisMaximum((float) maxvalue+5);

                    rightYAxis.setEnabled(false); //右侧Y轴不显示


//描述
//隐藏描述
                    Description description = new Description();
                    description.setEnabled(false);
                    mLineChart.setDescription(description);

//        Description description = new Description();
//        description.setText("X轴描述");
//        description.setTextColor(Color.RED);
//        mLineChart.setDescription(description);

//图例
                    Legend legend = mLineChart.getLegend();
                    legend.setFormSize(15f);
                    legend.setTextColor(Color.CYAN); //设置Legend 文本颜色
                    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                    legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                    legend.setWordWrapEnabled(true);

                    //一个LineDataSet就是一条线
                    LineDataSet lineDataSet = new LineDataSet(entries, thisyear+"电费变化图");
                    //设置曲线值的圆点是实心还是空心
                    lineDataSet.setDrawCircleHole(false);
                    //设置显示值的字体大小
                    lineDataSet.setValueTextSize(9f);
                    LineData data = new LineData(lineDataSet);
                    mLineChart.setData(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void getinfo(View view) throws ParseException {
        if(!edyear.getText().toString().equals(""))
        {
            thisyear=edyear.getText().toString();
        }
        else
            thisyear=dateUtils.getYear();
        userinfo.clear();
        mList.clear();
        GetUrl(roomnum,thisyear);
    }
}
