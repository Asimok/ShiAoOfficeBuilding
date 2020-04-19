package ShiAoOfficeBuilding.Usual_Per_Clock_Data;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
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


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Usual_Per_Clock_Data_Activity extends AppCompatActivity {
    private ArrayList<Usual_Per_Clock_Data_Info> usual_per_clock_data_infos;
    private  String count;

    private ListView ele_warn_listview;
    private TextView usual_searchDate;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usual_per_data_list);
        usual_per_clock_data_infos = new ArrayList<Usual_Per_Clock_Data_Info>();

        usual_searchDate = findViewById(R.id.usual_searchdate);

        ele_warn_listview = findViewById(R.id.usual_per_clo_data_lv);
        usual_searchDate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlgForTime();
                    return true;
                }
                return false;
            }
        });
        usual_searchDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlgForTime();
                }
            }
        });





    }
    public void searchUsual(View view) {
        getUsualPersonData();
    }
    protected void showDatePickDlgForTime() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Usual_Per_Clock_Data_Activity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String startTime=String.format("%02d", monthOfYear);

                Usual_Per_Clock_Data_Activity.this.usual_searchDate.setText(year + "-" + startTime + "-" + dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    private void getUsualPersonData() {
        usual_per_clock_data_infos.clear();
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                //count在这里更改
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=fixedlog&date="+usual_searchDate.getText()+"&index=1&count=100000")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Usual_Per_Clock_Data_Activity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                try {
                    JSONObject jsonObj = new JSONObject(res);
                    Log.d("aa",res);
                    //获取结果
                    String result = jsonObj.getString("result");
                    Log.d("aa", result);
                    //获取到的数据数量
                    count = jsonObj.getString("count");
                    Log.d("aa", count);

                    //获取到的房间数据
                    String roomlist = jsonObj.getString("data");
                    //转换成JSON数组
                    JSONArray jsonArray = new JSONArray(roomlist);
                    for (int i = 0; i < Integer.parseInt(count); i++) {
                        int pos =i;
                        //遍历获取数据
                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);

                        String personname = jsonObj2.getString("personname");

                        String personidnum = jsonObj2.getString("personidnum");



                        String personage = jsonObj2.getString("personage");

                        String personmobile = jsonObj2.getString("personmobile");

                        String clocktime = jsonObj2.getString("clocktime");

                        String clocktype = jsonObj2.getString("clocktype");


                        Log.d("aa","5");
                        showRoomlistResult(personname,personidnum,personage,personmobile,clocktime,clocktype);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String personname,final String personidnum,final  String personage ,final  String personmobile,final  String clocktime,final  String clocktype)
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Usual_Per_Clock_Data_Info mapx = new Usual_Per_Clock_Data_Info();
                mapx.setPersonname(personname);
                mapx.setPersonage(personage);
                mapx.setPersonidnum(personidnum);
                mapx.setClocktime(clocktime);
                mapx.setPersonmobile(personmobile);
                mapx.setClocktype(clocktype);
                usual_per_clock_data_infos.add(mapx);

                //数据适配器
                Usual_Per_Clock_Data_Adapter elec_warn_adapter = new Usual_Per_Clock_Data_Adapter(Usual_Per_Clock_Data_Activity.this,Integer.parseInt(count),usual_per_clock_data_infos);
                ele_warn_listview.setAdapter(elec_warn_adapter);
            }
        });
    }


}

