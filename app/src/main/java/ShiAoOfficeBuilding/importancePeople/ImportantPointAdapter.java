package ShiAoOfficeBuilding.importancePeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;



public class ImportantPointAdapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<ImportantPointInfo> data;
    public ImportantPointAdapter(Context context,  int count,ArrayList<ImportantPointInfo> data){
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

        View view = View.inflate(context, R.layout.showimportantpoint, null);


        TextView NAME = view.findViewById(R.id.NAME);
        TextView personaltype = view.findViewById(R.id.peasonaltype);
        TextView clocktime = view.findViewById(R.id.clocktime);
        TextView checktime = view.findViewById(R.id.checktime);
        TextView idnum = view.findViewById(R.id.id);


        NAME.setText(data.get(position).getName());
        personaltype.setText(data.get(position).getPersonalType());
        idnum.setText(data.get(position).getIdnum());
        checktime.setText(data.get(position).getChecktime());
        clocktime.setText(data.get(position).getClocktime());
        return view;
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
