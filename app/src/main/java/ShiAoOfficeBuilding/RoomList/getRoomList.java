package ShiAoOfficeBuilding.RoomList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shiaoofficebuilding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class getRoomList extends AppCompatActivity {
    TextView tv;
    private ListView roomlv;
    private ArrayList<roomlistAdapterInfo> roomInfoForAdapter;
    private  String count;
    adapterForRoomList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlist_listview_layout);
        roomInfoForAdapter = new ArrayList<roomlistAdapterInfo>();
        roomlv = findViewById(R.id.searchroomlv);
        getRoomlist();//get方法请求获取数据
        getBulidlist();

    }

    private void getRoomlist() {

        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=roomlist")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getRoomList.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                try {
                    JSONObject jsonObj = new JSONObject(res);

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
                        //房间号
                        String roomnum = jsonObj2.getString("roomnum");
                        //房间
                        String statusname = jsonObj2.getString("statusname");
                        //使用
                        String usetypename = jsonObj2.getString("usetypename");

                        showRoomlistResult(roomnum,statusname,usetypename,pos);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void getBulidlist() {


        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=buildlist")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getRoomList.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                try {
                    JSONObject jsonObj = new JSONObject(res);

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
                        //写字楼名称
                        String name = jsonObj2.getString("name");

                        showBulidlist(name);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void showRoomlistResult(final String res,final String statusname,final String usetypename,final  int pos )
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                roomlistAdapterInfo mapx = new roomlistAdapterInfo();
                mapx.setRoomNumber(res);
                mapx.setStatusname(statusname);
                mapx.setUsetypename(usetypename);

                roomInfoForAdapter.add(mapx);
                //数据适配器
                adapter = new adapterForRoomList(getRoomList.this,R.layout.showroomlist,roomInfoForAdapter);
                roomlv.setAdapter(adapter);
            }
        });
    }
    public void showBulidlist(final String name )
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String bulidname="";
                bulidname=bulidname+name;
                setTitle(bulidname);
            }
        });
    }







}
