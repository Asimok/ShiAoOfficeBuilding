package ShiAoOfficeBuilding.Chart;

import android.content.Context;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ShiAoOfficeBuilding.tools.dateUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class returnData extends AppCompatActivity {
    String count;
    String url;
    public List<Float> userinfo;
    private Context content;

    public void setContent(Context content) {
        this.content = content;
    }
    public void GetUrl(String roomnum1) throws ParseException {
        userinfo=new ArrayList<>();
        String yy = dateUtils.getYear();

        yy = dateUtils.subyear(yy,-1);
        Log.d("ff","yy "+yy);
        url = "http://47.93.103.150/OfficeWebApp/Office/service/GetJsonDataX.ashx?opera=meterinfo" +
                "&buildguid=20180518063531-d1dcf9d1-3b98-4ddc-9588-e9bf55779f15&roomnum="+roomnum1+"&year="+yy;

        final OkHttpClient okHttpClient = new OkHttpClient();


        final Request request = new Request.Builder()

                .url(url)
                .get()
                .build();
        //异步方法
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(content, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();//获取到传过来的字符串
                Log.d("ff","  res  " +res);
                try {
                    JSONObject jsonObj = new JSONObject(res);

                    //获取结果
                    String result = jsonObj.getString("result");
                    //Log.d("aa", result);
                    //获取到的数据数量
                    count = jsonObj.getString("count");
//                    Log.d("aa", count);

                    //获取到的房间数据
                    String roomlist = jsonObj.getString("data");
                    //转换成JSON数组
                    JSONArray jsonArray = new JSONArray(roomlist);
                    for (int i = 0; i < Integer.parseInt(count); i++) {
                        int pos = i;
                        //遍历获取数据
                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);
                        //房间号
                        String year = jsonObj2.getString("year");
                        //房间
                        String usenum01 = jsonObj2.getString("usenum01");
                        //使用
                        String usenum02 = jsonObj2.getString("usenum02");
                        String usenum03 = jsonObj2.getString("usenum03");
                        String usenum04 = jsonObj2.getString("usenum04");
                        String usenum05 = jsonObj2.getString("usenum05");
                        String usenum06 = jsonObj2.getString("usenum06");
                        String usenum07 = jsonObj2.getString("usenum07");
                        String usenum08 = jsonObj2.getString("usenum08");
                        String usenum09 = jsonObj2.getString("usenum09");
                        String usenum10 = jsonObj2.getString("usenum10");
                        String usenum11 = jsonObj2.getString("usenum11");
                        String usenum12 = jsonObj2.getString("usenum12");


                        float usernum01 = usenum01.equals("")?0:Float.parseFloat(usenum01);
                        float usernum02 = usenum02.equals("")?0:Float.parseFloat(usenum02);
                        float usernum03 = usenum03.equals("")?0:Float.parseFloat(usenum03);
                        float usernum04 = usenum04.equals("")?0:Float.parseFloat(usenum04);
                        float usernum05 = usenum05.equals("")?0:Float.parseFloat(usenum05);
                        float usernum06 = usenum06.equals("")?0:Float.parseFloat(usenum06);
                        float usernum07 = usenum07.equals("")?0:Float.parseFloat(usenum07);
                        float usernum08 = usenum08.equals("")?0:Float.parseFloat(usenum08);
                        float usernum09 = usenum09.equals("")?0:Float.parseFloat(usenum09);
                        float usernum10 = usenum10.equals("")?0:Float.parseFloat(usenum10);
                        float usernum11 = usenum11.equals("")?0:Float.parseFloat(usenum11);
                        float usernum12 = usenum12.equals("")?0:Float.parseFloat(usenum12);



                        userinfo.add(usernum01);
                        userinfo.add(usernum02);
                        userinfo.add(usernum03);
                        userinfo.add(usernum04);
                        userinfo.add(usernum05);
                        userinfo.add(usernum06);
                        userinfo.add(usernum07);
                        userinfo.add(usernum08);
                        userinfo.add(usernum09);
                        userinfo.add(usernum10);
                        userinfo.add(usernum11);
                        userinfo.add(usernum12);

                        Log.d("gg","water "+userinfo.get(0));

//                        showRoomlistResult(usernum01
//                                , usernum02
//                                , usernum03
//                                , usernum04
//                                , usernum05
//                                , usernum06
//                                , usernum07
//                                , usernum08
//                                , usernum09
//                                , usernum10
//                                , usernum11
//                                , usernum12
//                        );


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void showRoomlistResult(
                                   final float usenum01,
                                   final float usenum02,
                                   final float usenum03,
                                   final float usenum04,
                                   final float usenum05,
                                   final float usenum06,
                                   final float usenum07,
                                   final float usenum08,
                                   final float usenum09,
                                   final float usenum10,
                                   final float usenum11,
                                   final float usenum12 )
    {
        userinfo.add(usenum01);
        userinfo.add(usenum02);
        userinfo.add(usenum03);
        userinfo.add(usenum04);
        userinfo.add(usenum05);
        userinfo.add(usenum06);
        userinfo.add(usenum07);
        userinfo.add(usenum08);
        userinfo.add(usenum09);
        userinfo.add(usenum10);
        userinfo.add(usenum11);
        userinfo.add(usenum12);







    }

   public List<Float> returndata() {
        return userinfo;
   }
}