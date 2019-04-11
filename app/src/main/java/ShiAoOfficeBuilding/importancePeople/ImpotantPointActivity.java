package ShiAoOfficeBuilding.importancePeople;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiaoofficebuilding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


import ShiAoOfficeBuilding.tools.datepicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImpotantPointActivity extends AppCompatActivity {

    private ListView roomlv;
    private ArrayList<ImportantPointInfo> importantPointInfos;
    private ArrayList<ImportantPointInfo> importantPointInfosForSearch;
    private  String count;

    private ListView searchroomlv;
    private TextView starttime,endtime,name,idcard;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importance_listview_layout);
        importantPointInfos = new ArrayList<ImportantPointInfo>();
        importantPointInfosForSearch= new ArrayList<ImportantPointInfo>();
        searchroomlv = findViewById(R.id.searchroomlv);
        starttime = findViewById(R.id.starttime);
        endtime = findViewById(R.id.endtime);
        endtime = findViewById(R.id.endtime);
        name=findViewById(R.id.name);
        idcard=findViewById(R.id.idcard);
        starttime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlgForStartTime();
                    return true;
                }
                return false;
            }
        });
        starttime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlgForStartTime();
                }
            }
        });

        endtime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlgForEndTime();
                    return true;
                }
                return false;
            }
        });
        endtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlgForEndTime();
                }
            }
        });

    }
    public void res(View v) {
        if(starttime.getText().equals(""))
        {
            Toast.makeText(ImpotantPointActivity.this,"请选择开始时间",Toast.LENGTH_SHORT).show();
        }
        else if(endtime.getText().equals(""))
        {
            Toast.makeText(ImpotantPointActivity.this,"请选择结束时间",Toast.LENGTH_SHORT).show();
        }
        else
        getimportantlist();
    }
    private void getimportantlist() {
        importantPointInfos.clear();
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=importantperson&sdate="
                        +starttime.getText()+"&edate="+endtime.getText())
                .get()
                .build();
        String url ="http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=importantperson&sdate="
                +starttime.getText()+"&edate="+endtime.getText();
        Log.v("ee","url  "+url);
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ImpotantPointActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                Log.v("ee",res);
                try {
                    JSONObject jsonObj = new JSONObject(res);
                    //获取结果
                    String result = jsonObj.getString("result");
                    //获取到的数据数量
                    count = jsonObj.getString("count");

                    //获取到的房间数据
                    String roomlist = jsonObj.getString("data");
                    //转换成JSON数组
                    JSONArray jsonArray = new JSONArray(roomlist);
                    for (int i = 0; i < Integer.parseInt(count); i++) {
                        int pos =i;
                        //遍历获取数据
                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);
                        String checktype = jsonObj2.getString("checktype");
                        String idnum = jsonObj2.getString("idnum");
                        String clocktime = jsonObj2.getString("clocktime");
                        String checktime = jsonObj2.getString("checktime");
                        String name = jsonObj2.getString("name");
                        showRoomlistResult(name,checktype,idnum,clocktime,checktime);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String name,final String checktype,final String idnum,final  String clocktime ,final  String checktime)
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ImportantPointInfo mapx = new ImportantPointInfo();
                mapx.setName(name);
                mapx.setIdnum(idnum);
                mapx.setPersonalType(checktype);
                mapx.setClocktime(clocktime);
                mapx.setChecktime(checktime);
                importantPointInfos.add(mapx);
                ImportantPointAdapter apartmentAdapter = new ImportantPointAdapter(ImpotantPointActivity.this,Integer.parseInt(count),importantPointInfos);
                searchroomlv.setAdapter(apartmentAdapter);
            }
        });
    }
    protected void showDatePickDlgForStartTime() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ImpotantPointActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String endTime=String.format("%02d", monthOfYear);
                ImpotantPointActivity.this.starttime.setText(year + "-" + endTime + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    protected void showDatePickDlgForEndTime() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ImpotantPointActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String startTime=String.format("%02d", monthOfYear);

                ImpotantPointActivity.this.endtime.setText(year + "-" + startTime + "-" + dayOfMonth);
                Log.v("ee","加入  "+starttime.getText()+"   "+endtime.getText());
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public void search(View view) {

        if(name.getText().toString().equals("")&&idcard.getText().toString().equals(""))
        {

            Toast.makeText(ImpotantPointActivity.this, "请填写筛选条件！", Toast.LENGTH_SHORT).show();
        }
        importantPointInfosForSearch.clear();
        for (int i=0;i<importantPointInfos.size();i++)
        {
            if(importantPointInfos.get(i).getName().equals(name.getText().toString())&&idcard.getText().toString().equals(""))
            {
                ImportantPointInfo mapx = new ImportantPointInfo();
                mapx.setName(importantPointInfos.get(i).getName());
                mapx.setIdnum(importantPointInfos.get(i).getIdnum());
                mapx.setPersonalType(importantPointInfos.get(i).getPersonalType());
                mapx.setClocktime(importantPointInfos.get(i).getClocktime());
                mapx.setChecktime(importantPointInfos.get(i).getChecktime());
                importantPointInfosForSearch.add(mapx);
            }
            else  if(importantPointInfos.get(i).getIdnum().equals(idcard.getText().toString())&&name.getText().toString().equals(""))
            {
                ImportantPointInfo mapx = new ImportantPointInfo();
                mapx.setName(importantPointInfos.get(i).getName());
                mapx.setIdnum(importantPointInfos.get(i).getIdnum());
                mapx.setPersonalType(importantPointInfos.get(i).getPersonalType());
                mapx.setClocktime(importantPointInfos.get(i).getClocktime());
                mapx.setChecktime(importantPointInfos.get(i).getChecktime());
                importantPointInfosForSearch.add(mapx);
            }
            else if(importantPointInfos.get(i).getName().equals(name.getText().toString())&&
                    importantPointInfos.get(i).getIdnum().equals(idcard.getText().toString()))
            {
                ImportantPointInfo mapx = new ImportantPointInfo();
                mapx.setName(importantPointInfos.get(i).getName());
                mapx.setIdnum(importantPointInfos.get(i).getIdnum());
                mapx.setPersonalType(importantPointInfos.get(i).getPersonalType());
                mapx.setClocktime(importantPointInfos.get(i).getClocktime());
                mapx.setChecktime(importantPointInfos.get(i).getChecktime());
                importantPointInfosForSearch.add(mapx);
            }
        }
        ImportantPointAdapter apartmentAdapter = new ImportantPointAdapter(ImpotantPointActivity.this,Integer.parseInt(count),importantPointInfosForSearch);
        searchroomlv.setAdapter(apartmentAdapter);
    }
}