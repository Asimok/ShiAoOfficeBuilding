package ShiAoOfficeBuilding.TemperaryPersonWarning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;


public class Temp_Per_Warn_Adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Temp_Per_Warning_Info> data;
    public Temp_Per_Warn_Adapter(Context context,  int count,ArrayList<Temp_Per_Warning_Info> data){
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

        View view = View.inflate(context, R.layout.temp_custom_warning, null);


        TextView rulename = view.findViewById(R.id.rulename);
        TextView roomnumber = view.findViewById(R.id.roomnumber);
        TextView clocknumber = view.findViewById(R.id.clocknumber);
        TextView warningtime = view.findViewById(R.id.warningtime);



        rulename.setText(data.get(position).getRulename());
        roomnumber.setText(data.get(position).getRoomnum());
        clocknumber.setText(data.get(position).getClocknum());
        warningtime.setText(data.get(position).getWarndate());

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
