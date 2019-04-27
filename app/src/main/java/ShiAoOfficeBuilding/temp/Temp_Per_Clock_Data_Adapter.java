package ShiAoOfficeBuilding.temp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;


public class Temp_Per_Clock_Data_Adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Temp_Per_Clock_Data_Info> data;
    public Temp_Per_Clock_Data_Adapter(Context context,  int count,ArrayList<Temp_Per_Clock_Data_Info> data){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        this.count = count;
        if(data==null){
            this.data=new ArrayList<>();
        }else{
            this.data = data;
        }
    }


    @Override
    public int getCount() {
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.temp_per_clock_data_show, null);


        TextView personname = view.findViewById(R.id.personname);
        TextView per_sexname = view.findViewById(R.id.per_sexname);
        TextView personage = view.findViewById(R.id.personage);
        TextView personaddr = view.findViewById(R.id.personaddr);
        TextView personidnum = view.findViewById(R.id.personidnum);
        TextView per_clocktime = view.findViewById(R.id.per_clocktime);
        TextView deptname = view.findViewById(R.id.deptname);



        personname.setText(data.get(position).getPersonname());
        per_sexname.setText(data.get(position).getPersonsexname());
        personage.setText(data.get(position).getPersonage());
        personaddr.setText(data.get(position).getPersonaddr());
        personidnum.setText(data.get(position).getPersonidnum());
        per_clocktime.setText(data.get(position).getClocktime());
        deptname.setText(data.get(position).getDeptname());

        return view;
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
