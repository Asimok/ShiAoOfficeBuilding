package ShiAoOfficeBuilding.RoomList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
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


public class getRoomList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tv;
    private ListView roomlv;
    private ArrayList<roomlistAdapterInfo> roomInfoForAdapter;
    private  String count;
    adapterForRoomList adapter;
    Spinner one,two,three,four;
    int time=0;
    private String showfloor="02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlist_listview_layout);
        roomInfoForAdapter = new ArrayList<roomlistAdapterInfo>();
        roomlv = findViewById(R.id.searchroomlv);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);

        one.setSelection(1, true);
        two.setSelection(0, true);
        three.setSelection(0, true);
        four.setSelection(0, true);
        one.setOnItemSelectedListener(this);
        two.setOnItemSelectedListener(this);
        three.setOnItemSelectedListener(this);
        four.setOnItemSelectedListener(this);


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

                        String companyname = jsonObj2.getString("companyname");

                        String companytype = jsonObj2.getString("companytype");

                        String useobjguid = jsonObj2.getString("useobjguid");

                        if(roomnum.substring(0,2).equals(showfloor)) {
                            showRoomlistResult(roomnum, statusname, usetypename, pos,companytype,companyname,useobjguid);
                        }

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
    public void showRoomlistResult(final String res,final String statusname,final String usetypename,final  int pos,final String companytype,final String companyname,final String useobjguid )
    //封装遍历的数据
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                roomlistAdapterInfo mapx = new roomlistAdapterInfo();
                mapx.setRoomNumber(res);
                mapx.setStatusname(statusname);
                mapx.setUsetypename(usetypename);
                mapx.setCompanytype(companytype);
                mapx.setCompanyname(companyname);
                mapx.setUseobjguid(useobjguid);
                roomInfoForAdapter.add(mapx);
                //数据适配器
                adapter = new adapterForRoomList(getRoomList.this,R.layout.showroomlist,roomInfoForAdapter,showfloor);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String content = parent.getItemAtPosition(position).toString();
        switch (parent.getId()) {
            case R.id.one:

                    Log.d("DD1", "来了一次 one");
                    roomInfoForAdapter.clear();
                    showfloor = judgefloor(content);
                    getRoomlist();//get方法请求获取数据
                    break;

            case R.id.two:
                Log.d("DD1","来了一次 two");
                roomInfoForAdapter.clear();
                showfloor =  judgefloor(content);
                getRoomlist();//get方法请求获取数据
                break;
            case R.id.three:
                Log.d("DD1","来了一次 three");
                roomInfoForAdapter.clear();
                showfloor =  judgefloor(content);
                getRoomlist();//get方法请求获取数据
                break;
            case R.id.four:
                Log.d("DD1","来了一次 four");
                roomInfoForAdapter.clear();
                showfloor =  judgefloor(content);
                getRoomlist();//get方法请求获取数据
                break;
            default:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
public  String judgefloor(String floor)
{
    String numfloor = "02";
    if(floor.equals("1层"))
    {
        numfloor="01";
    }
    else  if(floor.equals("2层"))
    {
        numfloor="02";
    }
    else  if(floor.equals("3层"))
    {
        numfloor="03";
    }
    else  if(floor.equals("4层"))
    {
        numfloor="04";
    }
    else  if(floor.equals("5层"))
    {
        numfloor="05";
    }
    else  if(floor.equals("6层"))
    {
        numfloor="06";
    }
    else  if(floor.equals("7层"))
    {
        numfloor="07";
    }
    else  if(floor.equals("8层"))
    {
        numfloor="08";
    }
    else  if(floor.equals("9层"))
    {
        numfloor="09";
    }
    else  if(floor.equals("10层"))
    {
        numfloor="10";
    }
    else  if(floor.equals("11层"))
    {
        numfloor="11";
    }
    else  if(floor.equals("12层"))
    {
        numfloor="12";
    } else  if(floor.equals("13层"))
    {
        numfloor="13";
    }
    else  if(floor.equals("14层"))
    {
        numfloor="14";
    } else  if(floor.equals("15层"))
    {
        numfloor="15";
    } else  if(floor.equals("16层"))
    {
        numfloor="16";
    } else  if(floor.equals("17层"))
    {
        numfloor="17";
    } else  if(floor.equals("18层"))
    {
        numfloor="18";
    } else  if(floor.equals("19层"))
    {
        numfloor="19";
    } else  if(floor.equals("20层"))
    {
        numfloor="20";
    } else  if(floor.equals("21层"))
    {
        numfloor="21";
    } else  if(floor.equals("22层"))
    {
        numfloor="22";
    } else  if(floor.equals("23层"))
    {
        numfloor="23";
    } else  if(floor.equals("24层"))
    {
        numfloor="24";
    }

    return numfloor;
}

}
