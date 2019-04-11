package ShiAoOfficeBuilding.RoomList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;
import java.util.List;

import ShiAoOfficeBuilding.Chart.electricityUse;
import ShiAoOfficeBuilding.viewPager.three_ViewPager;

/**
 * 2列ListView的适配器
 * @author tongleer.com
 *
 */
public class adapterForRoomList extends BaseAdapter{
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<roomlistAdapterInfo> list;
    public adapterForRoomList(Context context, int resource, ArrayList<roomlistAdapterInfo> list,String floor){
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
        if (itemList.size() >0) {
            vh.tv1.setText(itemList.get(0).getRoomNumber());
            vh.tv11.setText(itemList.get(0).getStatusname());

            switch (itemList.get(0).getStatusname()) {
                case "自用":
                    vh.tv1.setBackgroundColor(Color.GREEN);
                    vh.tv1.setBackgroundColor(Color.parseColor("#eb7878"));
                    break;
                case "出租":
                    vh.tv1.setBackgroundColor(Color.GRAY);
                    vh.tv1.setBackgroundColor(Color.parseColor("#436e4f"));
                    break;
                case "":
                    vh.tv11.setText("---");


                    break;
                case "自住":
                    vh.tv1.setBackgroundColor(Color.LTGRAY);
                    vh.tv1.setBackgroundColor(Color.parseColor("#dd7c1b"));
                    break;
                default:
                    vh.tv1.setBackgroundColor(Color.parseColor("#3abfd1"));

            }
            vh.tv21.setText(itemList.get(0).getUsetypename());
            switch (itemList.get(0).getUsetypename()) {

                case "公司":
                    Resources resources = context.getResources();
                    Drawable btnDrawable = resources.getDrawable(R.drawable.room_bg_blue);
                    vh.parent.setBackgroundDrawable(btnDrawable);
                    break;
                case "库房":
                     resources = context.getResources();
                     btnDrawable = resources.getDrawable(R.drawable.room_bg_purple);
                    vh.parent.setBackgroundDrawable(btnDrawable);
                    break;
                case "宿舍":
                    resources = context.getResources();
                    btnDrawable = resources.getDrawable(R.drawable.room_bg_orange);
                    vh.parent.setBackgroundDrawable(btnDrawable);
                    break;
                case "":
                    vh.tv21.setText("---");
                    resources = context.getResources();
                    btnDrawable = resources.getDrawable(R.drawable.room_bg_black);
                    vh.parent.setBackgroundDrawable(btnDrawable);
                    break;
                    default:
                        vh.parent.setBackgroundColor(Color.parseColor("#3abfd1"));


            }

            vh.parent.setOnLongClickListener(
                    new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Intent intent;
                            intent = new Intent(context, three_ViewPager.class);
                            intent.setClass(context, three_ViewPager.class);
                            intent.putExtra("roomnum"    ,itemList.get(0).getRoomNumber());
                            intent.putExtra("statusname" ,itemList.get(0).getStatusname());
                            intent.putExtra("usetypename",itemList.get(0).getUsetypename());
                            intent.putExtra("companyname",itemList.get(0).getCompanyname());
                            intent.putExtra("companytype",itemList.get(0).getCompanytype());
                            intent.putExtra("useobjguid" ,itemList.get(0).getUseobjguid());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            return false;
                        }
                    }
            );
            if (itemList.size() > 1) {
                vh.tv2.setVisibility(View.VISIBLE);
                vh.tv12.setVisibility(View.VISIBLE);
                vh.tv22.setVisibility(View.VISIBLE);
                vh.parent2.setVisibility(View.VISIBLE);
                vh.tv2.setText(itemList.get(1).getRoomNumber());

                vh.tv12.setText(itemList.get(1).getStatusname());

                switch (itemList.get(1).getStatusname().trim()) {
                    case "自用":
                        vh.tv2.setBackgroundColor(Color.GREEN);
                        vh.tv2.setBackgroundColor(Color.parseColor("#eb7878"));
                        break;
                    case "出租":
                        vh.tv2.setBackgroundColor(Color.GRAY);
                        vh.tv2.setBackgroundColor(Color.parseColor("#436e4f"));

                        break;
                    case "自住":
                        vh.tv2.setBackgroundColor(Color.LTGRAY);
                        vh.tv2.setBackgroundColor(Color.parseColor("#dd7c1b"));
                        break;
                    case "":
                        vh.tv12.setText("---");
                        vh.tv2.setBackgroundColor(Color.parseColor("#b34d4d"));

                        break;
                    default:
                        vh.tv2.setBackgroundColor(Color.parseColor("#3abfd1"));


                }

                vh.tv22.setText(itemList.get(1).getUsetypename());


                switch (itemList.get(1).getUsetypename().trim()) {
                    case "公司":
                        Resources resources = context.getResources();
                        Drawable btnDrawable = resources.getDrawable(R.drawable.room_bg_blue);
                        vh.parent2.setBackgroundDrawable(btnDrawable);
                        break;
                    case "库房":
                         resources = context.getResources();
                         btnDrawable = resources.getDrawable(R.drawable.room_bg_purple);
                        vh.parent2.setBackgroundDrawable(btnDrawable);
                        break;
                    case "宿舍":
                        resources = context.getResources();
                        btnDrawable = resources.getDrawable(R.drawable.room_bg_orange);
                        vh.parent2.setBackgroundDrawable(btnDrawable);
                        break;
                    case "":
                        vh.tv22.setText("---");
                        resources = context.getResources();
                        btnDrawable = resources.getDrawable(R.drawable.room_bg_black);
                        vh.parent2.setBackgroundDrawable(btnDrawable);
                        break;
                    default:
                        vh.parent2.setBackgroundColor(Color.parseColor("#3abfd1"));

                }
                vh.parent2.setOnLongClickListener(
                        new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Intent intent;
                                intent = new Intent(context, three_ViewPager.class);
                                intent.setClass(context, three_ViewPager.class);
                                intent.putExtra("roomnum"    ,itemList.get(1).getRoomNumber());
                                intent.putExtra("statusname" ,itemList.get(1).getStatusname());
                                intent.putExtra("usetypename",itemList.get(1).getUsetypename());
                                intent.putExtra("companyname",itemList.get(1).getCompanyname());
                                intent.putExtra("companytype",itemList.get(1).getCompanytype());
                                intent.putExtra("useobjguid" ,itemList.get(1).getUseobjguid());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                Toast.makeText(context, itemList.get(1).getRoomNumber()+"长按了", Toast.LENGTH_SHORT).show();
                                return false;

                            }
                        }
                );
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
                        vh.tv3.setBackgroundColor(Color.GREEN);
                        vh.tv3.setBackgroundColor(Color.parseColor("#eb7878"));
                        break;
                    case "出租":
                        vh.tv3.setBackgroundColor(Color.GRAY);
                        vh.tv3.setBackgroundColor(Color.parseColor("#436e4f"));
                        break;
                    case "自住":
                        vh.tv3.setBackgroundColor(Color.LTGRAY);
                        vh.tv3.setBackgroundColor(Color.parseColor("#dd7c1b"));
                        break;
                    case "":
                        vh.tv13.setText("---");
                        vh.tv3.setBackgroundColor(Color.parseColor("#b34d4d"));

                        break;
                    default:
                        vh.tv3.setBackgroundColor(Color.parseColor("#3abfd1"));

                }

                vh.tv23.setText(itemList.get(2).getUsetypename());
                switch (itemList.get(2).getUsetypename().trim()) {
                    case "公司":
                        Resources resources = context.getResources();
                        Drawable btnDrawable = resources.getDrawable(R.drawable.room_bg_blue);
                        vh.parent3.setBackgroundDrawable(btnDrawable);
                        break;
                    case "库房":
                       resources = context.getResources();
                       btnDrawable = resources.getDrawable(R.drawable.room_bg_blue);
                        vh.parent3.setBackgroundDrawable(btnDrawable);
                        break;
                    case "宿舍":
                        resources = context.getResources();
                        btnDrawable = resources.getDrawable(R.drawable.room_bg_orange);
                        vh.parent3.setBackgroundDrawable(btnDrawable);
                        break;
                    case "":
                        vh.tv23.setText("---");
                        resources = context.getResources();
                        btnDrawable = resources.getDrawable(R.drawable.room_bg_black);
                        vh.parent3.setBackgroundDrawable(btnDrawable);
                        break;
                        default:
                            vh.parent3.setBackgroundColor(Color.parseColor("#3abfd1"));

                }
                vh.parent3.setOnLongClickListener(
                        new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Intent intent;
                                intent = new Intent(context, three_ViewPager.class);
                                intent.setClass(context, three_ViewPager.class);
                                intent.putExtra("roomnum"    ,itemList.get(2).getRoomNumber());
                                intent.putExtra("statusname" ,itemList.get(2).getStatusname());
                                intent.putExtra("usetypename",itemList.get(2).getUsetypename());
                                intent.putExtra("companyname",itemList.get(2).getCompanyname());
                                intent.putExtra("companytype",itemList.get(2).getCompanytype());
                                intent.putExtra("useobjguid" ,itemList.get(2).getUseobjguid());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return false;
                            }
                        }
                );
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
                        vh.tv4.setBackgroundColor(Color.GREEN);
                        vh.tv4.setBackgroundColor(Color.parseColor("#eb7878"));
                        break;
                    case "出租":
                        vh.tv4.setBackgroundColor(Color.GRAY);
                        vh.tv4.setBackgroundColor(Color.parseColor("#436e4f"));
                        break;
                    case "自住":
                        vh.tv4.setBackgroundColor(Color.LTGRAY);
                        vh.tv4.setBackgroundColor(Color.parseColor("#dd7c1b"));
                        break;
                    case "":
                        vh.tv14.setText("---");
                        vh.tv4.setBackgroundColor(Color.parseColor("#b34d4d"));

                        break;
                    default:
                        vh.tv4.setBackgroundColor(Color.parseColor("#3abfd1"));



                }

                vh.tv24.setText(itemList.get(3).getUsetypename());


                switch (itemList.get(3).getUsetypename().trim()) {

                    case "公司":
                        Resources resources = context.getResources();
                        Drawable btnDrawable = resources.getDrawable(R.drawable.room_bg_blue);
                        vh.parent4.setBackgroundDrawable(btnDrawable);
                        break;
                    case "库房":
                         resources = context.getResources();
                         btnDrawable = resources.getDrawable(R.drawable.room_bg_purple);
                        vh.parent4.setBackgroundDrawable(btnDrawable);
                        break;
                    case "宿舍":
                        resources = context.getResources();
                       btnDrawable = resources.getDrawable(R.drawable.room_bg_orange);
                        vh.parent4.setBackgroundDrawable(btnDrawable);
                        break;
                    case "":
                        vh.tv24.setText("---");
                         resources = context.getResources();
                         btnDrawable = resources.getDrawable(R.drawable.room_bg_black);
                        vh.parent4.setBackgroundDrawable(btnDrawable);
                        break;
                        default:
                            vh.parent4.setBackgroundColor(Color.parseColor("#3abfd1"));

                }

                vh.parent4.setOnLongClickListener(
                        new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Intent intent;
                                intent = new Intent(context, three_ViewPager.class);
                                intent.setClass(context, three_ViewPager.class);
                                intent.putExtra("roomnum"    ,itemList.get(3).getRoomNumber());
                                intent.putExtra("statusname" ,itemList.get(3).getStatusname());
                                intent.putExtra("usetypename",itemList.get(3).getUsetypename());
                                intent.putExtra("companyname",itemList.get(3).getCompanyname());
                                intent.putExtra("companytype",itemList.get(3).getCompanytype());
                                intent.putExtra("useobjguid" ,itemList.get(3).getUseobjguid());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return false;
                            }
                        }
                );
            } else {
                vh.tv4.setVisibility(View.INVISIBLE);
                vh.tv14.setVisibility(View.INVISIBLE);
                vh.tv24.setVisibility(View.INVISIBLE);
                vh.parent4.setVisibility(View.INVISIBLE);
            }
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}
