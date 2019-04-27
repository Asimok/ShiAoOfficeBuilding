package ShiAoOfficeBuilding.Usual_Per_Clock_Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;

public class Usual_Per_Clock_Data_Adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Usual_Per_Clock_Data_Info> data;
    public Usual_Per_Clock_Data_Adapter(Context context,  int count,ArrayList<Usual_Per_Clock_Data_Info> data){
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

        View view = View.inflate(context, R.layout.usual_per_clock_data_show, null);


        TextView usual_personname = view.findViewById(R.id.usual_personname);
        TextView usual_personage = view.findViewById(R.id.usual_personage);
        TextView usual_personidnum = view.findViewById(R.id.usual_personidnum);
        TextView usual_per_clocktime = view.findViewById(R.id.usual_per_clocktime);
        TextView clocktype = view.findViewById(R.id.clocktype);
        TextView personmobile = view.findViewById(R.id.personmobile);


        usual_personname.setText(data.get(position).getPersonname());
        usual_personage.setText(data.get(position).getPersonage());
        usual_personidnum.setText(data.get(position).getPersonidnum());
        usual_per_clocktime.setText(data.get(position).getClocktime());
        clocktype.setText(data.get(position).getClocktime());
        personmobile.setText(data.get(position).getPersonmobile());
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

