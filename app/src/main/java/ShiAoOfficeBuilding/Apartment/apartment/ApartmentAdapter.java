package ShiAoOfficeBuilding.Apartment.apartment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;

public class ApartmentAdapter extends BaseAdapter {
        public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<ApartmentlistInfo> data;
    public ApartmentAdapter(Context context,  int count,ArrayList<ApartmentlistInfo> data){
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
            return count;

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

            View view = View.inflate(context, R.layout.show_apartment_people, null);


            TextView idname = view.findViewById(R.id.idname);
            TextView sexname = view.findViewById(R.id.sexname);
            TextView mobile = view.findViewById(R.id.mobile);
            TextView idnum = view.findViewById(R.id.idnum);


            idname.setText(data.get(position).getIdname());
            sexname.setText(data.get(position).getSexname());
            mobile.setText(data.get(position).getMobile());
            idnum.setText(data.get(position).getIdnum());

            return view;
        }

}
