package com.example.shiaoofficebuilding;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    private ListView roomlv;
    private List<roomlistAdapterInfo> roomInfoForAdapter;
    private  String count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchroom_lv_layout);
        roomInfoForAdapter = new ArrayList<roomlistAdapterInfo>();
        roomlv = findViewById(R.id.searchroomlv);
        sendRequest();//get方法请求获取数据
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
                        //房间状态
                        String statusname = jsonObj2.getString("statusname");
                        //使用状态
                        String usetypename = jsonObj2.getString("usetypename");

                        showRoomlistResult(roomnum,statusname,usetypename,pos);

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

                roomlv.setAdapter(new MainActivity.MyAdapter());
            }
        });
    }


//数据适配器
//    private class MyAdapter extends BaseAdapter {
//        int sumCount,count;
//        MyAdapter(String count1) {
//            count = Integer.parseInt(count1);
//        }
//        @Override
//        public int getCount() {
//
//            if (count % 3 == 0) {
//                sumCount = count / 3; // 如果是双数直接减半
//            } else {
//                sumCount = (int) Math.floor((double) count / 3) + 1;
//            }
//            return sumCount;
//
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View view = View.inflate(MainActivity.this, R.layout.showroomlist, null);
//
//            TextView roomnum = view.findViewById(R.id.roomnum1);
//            TextView statusnum = view.findViewById(R.id.statusname1);
//            TextView usertypename = view.findViewById(R.id.usetypename1);
//            roomnum.setText(roomInfoForAdapter.get(position).getRoomNumber());
//            statusnum.setText(roomInfoForAdapter.get(position).getStatusname());
//            usertypename.setText(roomInfoForAdapter.get(position).getUsetypename());
//          //  Log.d("aa",roomInfoForAdapter.get(0).getRoomNumber());
//            return view;
//
//
//        }
//    }


//    private class MyAdapter extends BaseAdapter {
//        int sumCount,count,pos;
//        MyAdapter(String count1,int Pos) {
//            count = Integer.parseInt(count1);
//            this.pos = Pos;
//        }
//        @Override
//        public int getCount() {
//
//            if (count % 2 == 0) {
//                sumCount = count / 2; // 如果是双数直接减半
//            } else {
//                sumCount = (int) Math.floor((double) count / 2) + 1;
//            }
//            return sumCount;
//
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View view1 = View.inflate(MainActivity.this, R.layout.showroomlist, null);
////            View view1 = View.inflate(MainActivity.this, R.layout.showroomlist2, null);
////            View view1 = View.inflate(MainActivity.this, R.layout.showroomlist3, null);
//
//            TextView roomnum1 = view1.findViewById(R.id.roomnum1);
//            TextView statusnum1 = view1.findViewById(R.id.statusname1);
//            TextView usertypename1 = view1.findViewById(R.id.usetypename1);
//
//            TextView roomnum2 = view1.findViewById(R.id.roomnum2);
//            TextView statusnum2 = view1.findViewById(R.id.statusname2);
//            TextView usertypename2 = view1.findViewById(R.id.usetypename2);
//
//            TextView roomnum3 = view1.findViewById(R.id.roomnum3);
//            TextView statusnum3 = view1.findViewById(R.id.statusname3);
//            TextView usertypename3 = view1.findViewById(R.id.usetypename3);
//
//
//                Log.d("bb","pos  "+position);
//
//                if (position % 3 == 0) {
//
//                    roomnum1.setText(roomInfoForAdapter.get(position).getRoomNumber());
//                    Log.d("aa",roomInfoForAdapter.get(position).getRoomNumber());
//                    statusnum1.setText(roomInfoForAdapter.get(position).getUsetypename());
//                    usertypename1.setText(roomInfoForAdapter.get(position).getStatusname());
//                }
//                else if (position % 3 == 1) {
//
//                    roomnum2.setText(roomInfoForAdapter.get(position*2).getRoomNumber());
//                    statusnum2.setText(roomInfoForAdapter.get(position*2).getUsetypename());
//                    usertypename2.setText(roomInfoForAdapter.get(position*2).getStatusname());
//                }
//                else if (position % 3 == 2) {
//
//                    roomnum3.setText(roomInfoForAdapter.get(position*3).getRoomNumber());
//                    statusnum3.setText(roomInfoForAdapter.get(position*3).getUsetypename());
//                    usertypename3.setText(roomInfoForAdapter.get(position*3).getStatusname());
//                }
//
//            return view1;
//
//
//        }
//    }

    public class MyAdapter extends BaseAdapter {


        private int sumCount;


        @Override
        public int getCount() {
            int count = roomInfoForAdapter.size();
            if (count % 3 == 0) {
                sumCount = count / 3; // 如果是双数直接减半
            } else {
                sumCount = (int) Math.floor((double) count / 3) + 1;
            }
            return sumCount;
        }

        @Override
        public Object getItem(int position) {
            return roomInfoForAdapter.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            PayItem payitem1;
            PayItem payitem2;
            PayItem payitem3;
        }

        @SuppressLint("WrongViewCast")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.showroomlist, null);
                holder.payitem1 = (PayItem)convertView.findViewById(R.id.roomnum1);
                holder.payitem2 =  (PayItem)convertView.findViewById(R.id.roomnum2);
                holder.payitem3 =  (PayItem)convertView.findViewById(R.id.roomnum3);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.payitem1.setDescription(roomInfoForAdapter.get(position * 3).getRoomNumber());

            if (position * 3 + 1 == roomInfoForAdapter.size()) {
                holder.payitem2.setVisibility(View.INVISIBLE); // 如果是单数的话，那么最后一个item，右侧内容为空
            } else {
                holder.payitem2.setVisibility(View.VISIBLE); // 必须进行设置，负责存在复用holder的时候，会出现右侧的出现留白，跟最后一个一样，这个也是我写这篇文章最想锁


            }

            return convertView;
        }



    }


}
