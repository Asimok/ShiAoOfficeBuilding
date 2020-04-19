package ShiAoOfficeBuilding.Service;
 
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.shiaoofficebuilding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import ShiAoOfficeBuilding.tools.MyNotification;
import ShiAoOfficeBuilding.tools.dateUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestServiceTwo extends Service {

    private final String TAG = "TestServiceTwo";
    private int count = 0;
    private boolean quit = false;
    private  String count1;
    private MyBinder myBinder = new MyBinder();
    //定义onBinder方法所返回的对象
    public class MyBinder extends Binder {
 
        public int getCount() {
            return count;
        }
    }
 
    //必须实现的方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind方法被调用");
        return myBinder;
    }
 
    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate方法被调用");
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit) {

                    try {
                        Thread.sleep(60000);//一分钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getimportantlist();


                }
            }
        }).start();
    }
 
    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind方法被调用!");
        return true;
    }
 
    //Service被销毁时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy方法被调用");
        this.quit = true;
    }
 
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind方法被调用!");
    }

    private void getimportantlist() {
        OkHttpClient okhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=" +
                        "importantperson&sdate=2018-01-01&edate=2019-01-02")
                .get()
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                try {
                    JSONObject jsonObj = new JSONObject(res);
                    Log.d("gg",res);
                    //获取结果
                    String result = jsonObj.getString("result");
                    Log.d("aa", result);
                    //获取到的数据数量
                    count1 = jsonObj.getString("count");
                    Log.d("aa", count1);

                    //获取到的房间数据
                    String roomlist = jsonObj.getString("data");
                    //转换成JSON数组
                    JSONArray jsonArray = new JSONArray(roomlist);
                    for (int i = 0; i < Integer.parseInt(count1); i++) {
                        //遍历获取数据
                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);
                        String buildguid = jsonObj2.getString("buildguid");
                        String buildname = jsonObj2.getString("buildname");
                        String checktype = jsonObj2.getString("checktype");
                        String name = jsonObj2.getString("name");
                        String clocktime = jsonObj2.getString("clocktime");
                        String checktime = jsonObj2.getString("checktime");

                        Log.d("aa", buildname+"  "+name);


                        showpeople(buildname,name,checktype,clocktime,i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showpeople(String buildname,String name, String checktype,String clocktime,int id) {
        boolean next1=false;
        boolean next2=false;
        try {
            String nowdate= dateUtils.getdetailYear();//当前时间
            String getday=clocktime;//获取到的时间
            String onehourBefor=dateUtils.subhour(nowdate,-1);//一小时前的时间
            Log.d("ee","时间  "+"nowdate   "+nowdate+"  getday  "+getday+"  onehourBefor   "+onehourBefor);
             next1=dateUtils.comparedate(getday,nowdate);//获取到的时间在当前时间之前
             next2=dateUtils.comparedate(onehourBefor,getday);//获取到的时间在当前时间按前一小时之后
        } catch (ParseException e) {
            e.printStackTrace();
        }
       if(next1&&next2) {
           //提示一小时之内的
           MyNotification notify = new MyNotification(getApplication());
           notify.MyNotification(buildname, name + "  " + checktype + "  " + clocktime,
                   R.drawable.function3, "badpeople", "嫌疑人", id, "嫌疑人");

       }

    }
}