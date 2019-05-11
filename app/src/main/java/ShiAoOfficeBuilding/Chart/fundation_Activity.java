package ShiAoOfficeBuilding.Chart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import ShiAoOfficeBuilding.Apartment.apartment.staffActivity;


public class fundation_Activity  extends AppCompatActivity {
    TextView companytype1,companyname1,staff1;
     String statusname ;
     String usetypename;
     String companyname;
     String companytype;
     String useobjguid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundation_data);
        companytype1=(TextView) findViewById(R.id.companytype);
        companyname1=(TextView) findViewById(R.id.companyname);
        staff1=(TextView) findViewById(R.id.staff);
        initdata();


    }
    private void initdata() {
        Intent intent=getIntent();
        String room = intent.getStringExtra("roomnum");
         statusname  =intent.getStringExtra("statusname"   );
         usetypename  =intent.getStringExtra("usetypename" );
         companyname  =intent.getStringExtra("companyname" );
         companytype  =intent.getStringExtra("companytype" );
         useobjguid   =intent.getStringExtra("useobjguid"   );
        companyname1.setText(companyname);
        companytype1.setText(companytype);
       // staff1.setText("我是图片");

        Log.d("ff", room + statusname + usetypename + companyname + companytype + useobjguid);


        Log.d("ff", room + statusname + usetypename + companyname + companytype + useobjguid);
    }

    public void people(View view) {
        Intent intent =new Intent(this, staffActivity.class);
        intent.putExtra("useobjguid",useobjguid);
        startActivity(intent);
    }
}
