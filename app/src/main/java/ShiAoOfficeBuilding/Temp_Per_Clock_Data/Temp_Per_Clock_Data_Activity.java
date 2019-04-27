package ShiAoOfficeBuilding.Temp_Per_Clock_Data;

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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Temp_Per_Clock_Data_Activity extends AppCompatActivity {
    private ArrayList<Temp_Per_Clock_Data_Info> temp_Per_Clock_Data_Info;
    private ArrayList<Temp_Per_Clock_Data_Info> temp_Per_Clock_Data_Info_Search;
    private  String count;

    private ListView ele_warn_listview;
    private EditText room;
    private TextView searchDate;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_per_clock_data_list);
        temp_Per_Clock_Data_Info = new ArrayList<Temp_Per_Clock_Data_Info>();
        temp_Per_Clock_Data_Info_Search = new ArrayList<Temp_Per_Clock_Data_Info>();
        searchDate = findViewById(R.id.searchdate);

        ele_warn_listview = findViewById(R.id.temp_per_clo_data_lv);
        room = findViewById(R.id.room);
        searchDate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlgForTime();
                    return true;
                }
                return false;
            }
        });
        searchDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlgForTime();
                }
            }
        });





    }
    public void searchTemp(View view) {
        Log.d("aa","1");
        getTempPersonData();
    }
    protected void showDatePickDlgForTime() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Temp_Per_Clock_Data_Activity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String startTime=String.format("%02d", monthOfYear);

                Temp_Per_Clock_Data_Activity.this.searchDate.setText(year + "-" + startTime + "-" + dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    private void getTempPersonData() {
        temp_Per_Clock_Data_Info.clear();
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                //count在这里更改
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=visitorlog&date="+searchDate.getText()+"&index=1&count=100000")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Temp_Per_Clock_Data_Activity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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

                        String sexname = jsonObj2.getString("sexname");

                        String personage = jsonObj2.getString("personage");

                        String personaddr = jsonObj2.getString("personaddr");

                        String clocktime = jsonObj2.getString("clocktime");

                        String deptname = jsonObj2.getString("deptname");


                        Log.d("aa","5");
                        showRoomlistResult(personname,personidnum,sexname,personage,personaddr,clocktime,deptname);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String personname,final String personidnum,final String sexname,final  String personage ,final  String personaddr,final  String clocktime,final  String deptname)
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Temp_Per_Clock_Data_Info mapx = new Temp_Per_Clock_Data_Info();
                mapx.setPersonname(personname);
                mapx.setPersonage(personage);
                mapx.setPersonsexname(sexname);
                mapx.setPersonidnum(personidnum);
                mapx.setClocktime(clocktime);
                mapx.setPersonaddr(personaddr);
                mapx.setDeptname(deptname);
                temp_Per_Clock_Data_Info.add(mapx);

                //数据适配器
                Temp_Per_Clock_Data_Adapter elec_warn_adapter = new Temp_Per_Clock_Data_Adapter(Temp_Per_Clock_Data_Activity.this,Integer.parseInt(count),temp_Per_Clock_Data_Info);
                ele_warn_listview.setAdapter(elec_warn_adapter);
            }
        });
    }

   /* public void shaixuan(View view) {
        temp_Per_Clock_Data_Info_Search.clear();
        for (int i = 0; i < temp_Per_Clock_Data_Info.size(); i++) {

            if (temp_Per_Clock_Data_Info.get(i).g().equals(room.getText().toString())) {

                Elec_Warn_info mapx = new Elec_Warn_info();
                mapx.setRoomnum(elec_warn_infos.get(i).getRoomnum());
                mapx.setYear(elec_warn_infos.get(i).getYear());
                mapx.setMonth(elec_warn_infos.get(i).getMonth());
                mapx.setWarndate(elec_warn_infos.get(i).getWarndate());
                mapx.setUsenum(elec_warn_infos.get(i).getUsenum());
                elec_warn_infosForSearch.add(mapx);
            }
        }*/
    }

   /* public void search(View view) {


        getElectricWarninglist();    }
}*/
