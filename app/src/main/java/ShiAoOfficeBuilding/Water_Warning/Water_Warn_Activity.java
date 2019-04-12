package ShiAoOfficeBuilding.Water_Warning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shiaoofficebuilding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import ShiAoOfficeBuilding.Electric_Warning.Elec_Warn_Adapter;
import ShiAoOfficeBuilding.Electric_Warning.Elec_Warn_info;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Water_Warn_Activity extends AppCompatActivity {
    private ArrayList<Water_Warn_info> water_warn_infos;
    private ArrayList<Water_Warn_info> water_warn_infosForSearch;
    private  String count;

    private ListView ele_warn_listview;
    private EditText room;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elec_show_listview);
        water_warn_infos = new ArrayList<Water_Warn_info>();
        water_warn_infosForSearch= new ArrayList<Water_Warn_info>();
        ele_warn_listview = findViewById(R.id.ele_warn_listview);
        room = findViewById(R.id.room);





    }
 public void search(View v) {
     getWaterWarninglist();
 }
    private void getWaterWarninglist() {
        water_warn_infos.clear();
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=waterwarn")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Water_Warn_Activity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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

                        String year = jsonObj2.getString("year");

                        String roomnum = jsonObj2.getString("roomnum");

                        String month = jsonObj2.getString("month");

                        String warndate = jsonObj2.getString("warndate");

                        String usenum = jsonObj2.getString("usenum");


                        Log.d("aa","5");
                        showRoomlistResult(roomnum,year,month,warndate,usenum);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String roomnum,final String year,final String month,final  String warndate ,final  String usenum)
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Water_Warn_info mapx = new Water_Warn_info();
                mapx.setRoomnum(roomnum);
                mapx.setYear(year);
                mapx.setMonth(month);
                mapx.setWarndate(warndate);
                mapx.setUsenum(usenum);

                water_warn_infos.add(mapx);

                //数据适配器
                Water_Warn_Adapter water_warn_adapter = new Water_Warn_Adapter(Water_Warn_Activity.this,Integer.parseInt(count),water_warn_infos);
                ele_warn_listview.setAdapter(water_warn_adapter);
            }
        });
    }

    public void shaixuan(View view) {
        water_warn_infosForSearch.clear();
        for (int i = 0; i < water_warn_infos.size(); i++) {

            if (water_warn_infos.get(i).getRoomnum().equals(room.getText().toString())) {

                Water_Warn_info mapx = new Water_Warn_info();
                mapx.setRoomnum(water_warn_infos.get(i).getRoomnum());
                mapx.setYear(water_warn_infos.get(i).getYear());
                mapx.setMonth(water_warn_infos.get(i).getMonth());
                mapx.setWarndate(water_warn_infos.get(i).getWarndate());
                mapx.setUsenum(water_warn_infos.get(i).getUsenum());
                water_warn_infosForSearch.add(mapx);
            }
        }
    }
}
