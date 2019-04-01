package com.example.shiaoofficebuilding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tvtext);
    }

    private void sendRequest() {


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
                        Toast.makeText(MainActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                try {
                    JSONObject jsonObj = new JSONObject(res);


                    String result = jsonObj.getString("result");
                    Log.d("aa", result);
                    String count = jsonObj.getString("count");
                    Log.d("aa", count);

                    String data = jsonObj.getString("data");
                    data=data.replace("[","");
                    data=data.replace("]","");
                    Log.d("aa", data);
                    JSONObject jsonObj1 = new JSONObject(data);
                    String guidtxt = jsonObj1.getString("guidtxt");
                    Log.d("aa", guidtxt);


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });
    }

    public void data(final String res)

    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(res.trim());
            }
        });
    }
    public  void qq(View v)
    {
        sendRequest();
    }

}
