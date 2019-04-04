package com.example.shiaoofficebuilding;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 2列ListView的适配器
 * @author tongleer.com
 *
 */
public class MyAdapter extends BaseAdapter{
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<roomlistAdapterInfo> list;
    public MyAdapter(Context context, int resource, ArrayList<roomlistAdapterInfo> list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list==null){
            this.list=new ArrayList<>();
        }else{
            this.list = list;
        }
    }
    @Override
    public int getCount() {
        if(list.size()%4>0) {
            return list.size()/4+1;

        } else {
            return list.size()/4;
        }
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null ) {
            convertView = inflater.inflate(resource, null);
            vh = new ViewHolder();
            vh.tv1=(TextView)convertView.findViewById(R.id.roomnum1);
            vh.tv2=(TextView)convertView.findViewById(R.id.roomnum2);
            vh.tv3=(TextView)convertView.findViewById(R.id.roomnum3);
            vh.tv4=(TextView)convertView.findViewById(R.id.roomnum4);
            vh.tv11=(TextView)convertView.findViewById(R.id.statusname1);
            vh.tv12=(TextView)convertView.findViewById(R.id.statusname2);
            vh.tv13=(TextView)convertView.findViewById(R.id.statusname3);
            vh.tv14=(TextView)convertView.findViewById(R.id.statusname4);
            vh.tv21=(TextView)convertView.findViewById(R.id.usetypename1);
            vh.tv22=(TextView)convertView.findViewById(R.id.usetypename2);
            vh.tv23=(TextView)convertView.findViewById(R.id.usetypename3);
            vh.tv24=(TextView)convertView.findViewById(R.id.usetypename4);
            vh.parent=(RelativeLayout) convertView.findViewById(R.id.parent);
            vh.parent2=(RelativeLayout) convertView.findViewById(R.id.parent2);
            vh.parent3=(RelativeLayout) convertView.findViewById(R.id.parent3);
            vh.parent4=(RelativeLayout) convertView.findViewById(R.id.parent4);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }
        int distance =  list.size() - position*4;
        int cellCount = distance >= 4? 4:distance;

        final List<roomlistAdapterInfo> itemList = list.subList(position*4,position*4+cellCount);
        if(!itemList.isEmpty())
        {
            Log.d("cc","进入 "+position);

        if (itemList.size() >0) {
            vh.tv1.setText(itemList.get(0).getRoomNumber());
            vh.tv11.setText(itemList.get(0).getStatusname());

            switch (itemList.get(0).getStatusname()) {
                case "自用":
                    vh.tv1.setTextColor(Color.GREEN);
                    vh.tv11.setTextColor(Color.GREEN);
                    vh.tv21.setTextColor(Color.GREEN);
                    break;
                case "出租":
                    vh.tv1.setTextColor(Color.GRAY);
                    vh.tv11.setTextColor(Color.GRAY);
                    vh.tv21.setTextColor(Color.GRAY);
                    break;
                case "":
                    vh.tv11.setText("---");
                    vh.tv1.setTextColor(Color.WHITE);
                    vh.tv11.setTextColor(Color.WHITE);
                    vh.tv21.setTextColor(Color.WHITE);
                    break;
                case "自住":
                    vh.tv1.setTextColor(Color.LTGRAY);
                    vh.tv11.setTextColor(Color.LTGRAY);
                    vh.tv21.setTextColor(Color.LTGRAY);
                    break;
                default:
                    vh.tv1.setTextColor(Color.parseColor("#3abfd1"));
                    vh.tv11.setTextColor(Color.parseColor("#3abfd1"));
                    vh.tv21.setTextColor(Color.parseColor("#3abfd1"));
            }
            vh.tv21.setText(itemList.get(0).getUsetypename());
            switch (itemList.get(0).getUsetypename()) {
                case "公司":
                    vh.parent.setBackgroundColor(Color.BLUE);
                    break;
                case "库房":
                    vh.parent.setBackgroundColor(Color.YELLOW);
                    break;
                case "宿舍":
                    vh.parent.setBackgroundColor(Color.CYAN);
                    break;
                case "":
                    vh.tv21.setText("---");
                    vh.parent.setBackgroundColor(Color.RED);
                    break;


            }
            vh.tv1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemList.get(0).getRoomNumber(), Toast.LENGTH_SHORT).show();
                }
            });
            if (itemList.size() > 1) {
                vh.tv2.setVisibility(View.VISIBLE);
                vh.tv12.setVisibility(View.VISIBLE);
                vh.tv22.setVisibility(View.VISIBLE);
                vh.parent2.setVisibility(View.VISIBLE);
                vh.tv2.setText(itemList.get(1).getRoomNumber());

                vh.tv12.setText(itemList.get(1).getStatusname());

                switch (itemList.get(1).getStatusname().trim()) {
                    case "自用":
                        vh.tv2.setTextColor(Color.GREEN);
                        vh.tv12.setTextColor(Color.GREEN);
                        vh.tv22.setTextColor(Color.GREEN);
                        break;
                    case "出租":
                        vh.tv2.setTextColor(Color.GRAY);
                        vh.tv12.setTextColor(Color.GRAY);
                        vh.tv22.setTextColor(Color.GRAY);
                        break;
                    case "自住":
                        vh.tv2.setTextColor(Color.LTGRAY);
                        vh.tv12.setTextColor(Color.LTGRAY);
                        vh.tv22.setTextColor(Color.LTGRAY);
                        break;
                    case "":
                        vh.tv12.setText("---");
                        vh.tv2.setTextColor(Color.WHITE);
                        vh.tv12.setTextColor(Color.WHITE);
                        vh.tv22.setTextColor(Color.WHITE);
                        break;
                    default:
                        vh.tv2.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv12.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv22.setTextColor(Color.parseColor("#3abfd1"));

                }

                vh.tv22.setText(itemList.get(1).getUsetypename());


                switch (itemList.get(1).getUsetypename().trim()) {
                    case "公司":
                        vh.parent2.setBackgroundColor(Color.BLUE);
                        break;
                    case "库房":
                        vh.parent2.setBackgroundColor(Color.YELLOW);
                        break;
                    case "宿舍":
                        vh.parent2.setBackgroundColor(Color.CYAN);
                        break;
                    case "":
                        vh.tv22.setText("---");
                        vh.parent2.setBackgroundColor(Color.RED);
                        break;

                }
                vh.tv2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(1).getRoomNumber(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                vh.tv2.setVisibility(View.INVISIBLE);
                vh.tv21.setVisibility(View.INVISIBLE);
                vh.tv22.setVisibility(View.INVISIBLE);
                vh.parent2.setVisibility(View.INVISIBLE);





            }

            if (itemList.size() > 2) {
                vh.tv3.setVisibility(View.VISIBLE);
                vh.tv13.setVisibility(View.VISIBLE);
                vh.tv23.setVisibility(View.VISIBLE);
                vh.parent3.setVisibility(View.VISIBLE);
                vh.tv3.setText(itemList.get(2).getRoomNumber());

                vh.tv13.setText(itemList.get(2).getStatusname());
                switch (itemList.get(2).getStatusname().trim()) {
                    case "自用":
                        vh.tv3.setTextColor(Color.GREEN);
                        vh.tv13.setTextColor(Color.GREEN);
                        vh.tv23.setTextColor(Color.GREEN);
                        break;
                    case "出租":
                        vh.tv3.setTextColor(Color.GRAY);
                        vh.tv13.setTextColor(Color.GRAY);
                        vh.tv23.setTextColor(Color.GRAY);
                        break;
                    case "自住":
                        vh.tv3.setTextColor(Color.LTGRAY);
                        vh.tv13.setTextColor(Color.LTGRAY);
                        vh.tv23.setTextColor(Color.LTGRAY);
                        break;
                    case "":
                        vh.tv13.setText("---");
                        vh.tv3.setTextColor(Color.WHITE);
                        vh.tv13.setTextColor(Color.WHITE);
                        vh.tv23.setTextColor(Color.WHITE);
                        break;
                    default:
                        vh.tv3.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv13.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv23.setTextColor(Color.parseColor("#3abfd1"));

                }

                vh.tv23.setText(itemList.get(2).getUsetypename());
                switch (itemList.get(2).getUsetypename().trim()) {
                    case "公司":
                        vh.parent3.setBackgroundColor(Color.BLUE);
                        break;
                    case "库房":
                        vh.parent3.setBackgroundColor(Color.YELLOW);
                        break;
                    case "宿舍":
                        vh.parent3.setBackgroundColor(Color.CYAN);
                        break;
                    case "":
                        vh.tv23.setText("---");
                        vh.parent3.setBackgroundColor(Color.RED);
                        break;

                }
                vh.tv3.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(2).getRoomNumber(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                vh.tv3.setVisibility(View.INVISIBLE);
                vh.tv13.setVisibility(View.INVISIBLE);
                vh.tv23.setVisibility(View.INVISIBLE);
                vh.parent3.setVisibility(View.INVISIBLE);

            }

            if (itemList.size() > 3) {
                vh.tv4.setVisibility(View.VISIBLE);
                vh.parent4.setVisibility(View.VISIBLE);
                vh.tv14.setVisibility(View.VISIBLE);
                vh.tv24.setVisibility(View.VISIBLE);
                vh.tv4.setText(itemList.get(3).getRoomNumber());

                vh.tv14.setText(itemList.get(3).getStatusname());
                switch (itemList.get(3).getStatusname().trim()) {
                    case "自用":
                        vh.tv4.setTextColor(Color.GREEN);
                        vh.tv14.setTextColor(Color.GREEN);
                        vh.tv24.setTextColor(Color.GREEN);
                        break;
                    case "出租":
                        vh.tv4.setTextColor(Color.GRAY);
                        vh.tv14.setTextColor(Color.GRAY);
                        vh.tv24.setTextColor(Color.GRAY);
                        break;
                    case "自住":
                        vh.tv4.setTextColor(Color.LTGRAY);
                        vh.tv14.setTextColor(Color.LTGRAY);
                        vh.tv24.setTextColor(Color.LTGRAY);
                        break;
                    case "":
                        vh.tv14.setText("---");
                        vh.tv4.setTextColor(Color.WHITE);
                        vh.tv14.setTextColor(Color.WHITE);
                        vh.tv24.setTextColor(Color.WHITE);
                        break;
                    default:
                        vh.tv4.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv14.setTextColor(Color.parseColor("#3abfd1"));
                        vh.tv24.setTextColor(Color.parseColor("#3abfd1"));


                }

                vh.tv24.setText(itemList.get(3).getUsetypename());


                switch (itemList.get(3).getUsetypename().trim()) {

                    case "公司":
                        vh.parent4.setBackgroundColor(Color.BLUE);
                        break;
                    case "库房":
                        vh.parent4.setBackgroundColor(Color.YELLOW);
                        break;
                    case "宿舍":
                        vh.parent4.setBackgroundColor(Color.CYAN);
                        break;
                    case "":
                        vh.tv24.setText("---");
                        vh.parent4.setBackgroundColor(Color.RED);
                        break;

                }

                Log.d("dd","pos  "+position+"   "+vh.tv1.getText());

                vh.tv4.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(3).getRoomNumber(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                vh.tv4.setVisibility(View.INVISIBLE);
                vh.tv14.setVisibility(View.INVISIBLE);
                vh.tv24.setVisibility(View.INVISIBLE);
                vh.parent4.setVisibility(View.INVISIBLE);
            }
        }
        else
        {
Log.d("cc","caxacac");
        }
        }
        return convertView;
    }
    /**
     * 封装ListView中item控件以优化ListView
     * @author tongleer
     *
     */
    public static class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv11;
        TextView tv12;
        TextView tv13;
        TextView tv14;
        TextView tv21;
        TextView tv22;
        TextView tv23;
        TextView tv24;
        RelativeLayout parent;
        RelativeLayout parent2;
        RelativeLayout parent3;
        RelativeLayout parent4;

    }
}
