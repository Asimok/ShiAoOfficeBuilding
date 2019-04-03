package com.example.shiaoofficebuilding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        if(list.size()%3>0) {
            return list.size()/3+1;
        } else {
            return list.size()/3;
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
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }
        int distance =  list.size() - position*3;
        int cellCount = distance >= 3? 3:distance;
        final List<roomlistAdapterInfo> itemList = list.subList(position*3,position*3+cellCount);
        if (itemList.size() >0) {
            vh.tv1.setText(itemList.get(0).getRoomNumber());
            vh.tv1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemList.get(0).getRoomNumber(), Toast.LENGTH_SHORT).show();
                }
            });
            if (itemList.size() >1){
                vh.tv2.setVisibility(View.VISIBLE);
                vh.tv2.setText(itemList.get(1).getRoomNumber());
                vh.tv2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(1).getRoomNumber(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                vh.tv2.setVisibility(View.INVISIBLE);
            }

            if (itemList.size() >2){
                vh.tv3.setVisibility(View.VISIBLE);
                vh.tv3.setText(itemList.get(2).getRoomNumber());
                vh.tv3.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(2).getRoomNumber(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                vh.tv3.setVisibility(View.INVISIBLE);
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

    }
}
