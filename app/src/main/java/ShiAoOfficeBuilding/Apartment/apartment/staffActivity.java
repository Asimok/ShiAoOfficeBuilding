package ShiAoOfficeBuilding.Apartment.apartment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shiaoofficebuilding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import ShiAoOfficeBuilding.RoomList.adapterForRoomList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class staffActivity extends AppCompatActivity {
    TextView tv;
    private ListView roomlv;
    private ArrayList<ApartmentlistInfo> apartmentlistInfo;
    private  String count;
    adapterForRoomList adapter;
    private ListView searchroomlv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_listview_layout);
        apartmentlistInfo = new ArrayList<ApartmentlistInfo>();
        searchroomlv = findViewById(R.id.apartmentlv);
        Intent intent=getIntent();
        String  guid=intent.getStringExtra("useobjguid");
        getdormitorylist(guid);//get方法请求获取数据


    }

    private void getdormitorylist(String guid) {

        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=workerlist&guid="+guid)
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(staffActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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

                        String idname = jsonObj2.getString("idname");

                        String sexname = jsonObj2.getString("sexname");

                        String idnum = jsonObj2.getString("idnum");

                        String mobile = jsonObj2.getString("mobile");

                        showRoomlistResult(idname,sexname,idnum,mobile);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showRoomlistResult(final String idname,final String sexname,final String idnum,final  String mobile )
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ApartmentlistInfo mapx = new ApartmentlistInfo();
                mapx.setIdname(idname);
                mapx.setSexname(sexname);
                mapx.setIdnum(idnum);
                mapx.setMobile(mobile);
                apartmentlistInfo.add(mapx);
                //数据适配器
                ApartmentAdapter apartmentAdapter = new ApartmentAdapter(staffActivity.this,Integer.parseInt(count),apartmentlistInfo);
                searchroomlv.setAdapter(apartmentAdapter);
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
