package ShiAoOfficeBuilding.Usual_Per_Warning;

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

import ShiAoOfficeBuilding.TemperaryPersonWarning.Temp_Per_Warn_Adapter;
import ShiAoOfficeBuilding.TemperaryPersonWarning.Temp_Per_Warning_Info;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Usual_Per_Warn_Activity extends AppCompatActivity {
    private ArrayList<Usual_Per_Warning_Info> usual_per_warning_infos;
    private ArrayList<Usual_Per_Warning_Info> usual_per_warning_infosForSearch;
    private  String count;

    private ListView templistview;
    private EditText room;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templistview);
        usual_per_warning_infos = new ArrayList<Usual_Per_Warning_Info>();
        usual_per_warning_infosForSearch= new ArrayList<Usual_Per_Warning_Info>();
        templistview = findViewById(R.id.templistview);
        room = findViewById(R.id.room);

        getimportantlist();



    }

    private void getimportantlist() {
        usual_per_warning_infos.clear();
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=fixedwarn")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Usual_Per_Warn_Activity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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
                        Log.d("aa","0");
                        String rulename = jsonObj2.getString("rulename");
                        Log.d("aa","1");
                        String roomnum = jsonObj2.getString("roomnum");
                        Log.d("aa","2");
                        String clocknum = jsonObj2.getString("clocknum");
                        Log.d("aa","3");
                        String warndate = jsonObj2.getString("warndate");
                        Log.d("aa","4");

                        Log.d("aa","5");
                        showRoomlistResult(rulename,roomnum,clocknum,warndate);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String rulename,final String roomnum,final String clocknum,final  String warndate )
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Usual_Per_Warning_Info mapx = new Usual_Per_Warning_Info();
                mapx.setClocknum(clocknum);
                mapx.setRulename(rulename);
                mapx.setRoomnum(roomnum);
                mapx.setWarndate(warndate);

                usual_per_warning_infos.add(mapx);

                //数据适配器
                Usual_Per_Warn_Adapter apartmentAdapter = new Usual_Per_Warn_Adapter(Usual_Per_Warn_Activity.this,Integer.parseInt(count),usual_per_warning_infos);
                templistview.setAdapter(apartmentAdapter);
            }
        });
    }

    public void shaixuan(View view) {
        usual_per_warning_infosForSearch.clear();
        for (int i = 0; i < usual_per_warning_infos.size(); i++) {

            if (usual_per_warning_infos.get(i).getRoomnum().equals(room.getText().toString())) {

                Usual_Per_Warning_Info mapx = new Usual_Per_Warning_Info();
                mapx.setClocknum(usual_per_warning_infos.get(i).getClocknum());
                mapx.setRoomnum(usual_per_warning_infos.get(i).getRoomnum());
                mapx.setRulename(usual_per_warning_infos.get(i).getRulename());
                mapx.setWarndate(usual_per_warning_infos.get(i).getWarndate());
                usual_per_warning_infosForSearch.add(mapx);
            }
        }
    }
}
