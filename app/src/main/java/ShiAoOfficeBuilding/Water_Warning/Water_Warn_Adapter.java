package ShiAoOfficeBuilding.Water_Warning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;

import ShiAoOfficeBuilding.Electric_Warning.Elec_Warn_info;

public class Water_Warn_Adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Water_Warn_info> data;
    public Water_Warn_Adapter(Context context, int count, ArrayList<Water_Warn_info> data){
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

        View view = View.inflate(context, R.layout.electric_warning, null);


        TextView elec_roomnum = view.findViewById(R.id.elec_roomnum);
        TextView elec_year = view.findViewById(R.id.elec_year);
        TextView elec_month = view.findViewById(R.id.elec_month);
        TextView elec_warndate = view.findViewById(R.id.elec_warndate);
        TextView elec_usenum = view.findViewById(R.id.elec_usenum);



        elec_roomnum.setText(data.get(position).getRoomnum());
        elec_year.setText(data.get(position).getYear());
        elec_month.setText(data.get(position).getMonth());
        elec_warndate.setText(data.get(position).getWarndate());
        elec_usenum.setText(data.get(position).getUsenum());

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
