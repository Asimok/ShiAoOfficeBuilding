package ShiAoOfficeBuilding.test;
import ShiAoOfficeBuilding.Apartment.apartment.apartmentActivity;
import ShiAoOfficeBuilding.Apartment.apartment.staffActivity;
import ShiAoOfficeBuilding.Chart.waterUse;
import ShiAoOfficeBuilding.Chart.electricityUse;
import ShiAoOfficeBuilding.Electric_Warning.Elec_Warn_Activity;
import  ShiAoOfficeBuilding.RoomList.getRoomList;
import ShiAoOfficeBuilding.TemperaryPersonWarning.Temp_Per_Warn_Activity;
import ShiAoOfficeBuilding.Usual_Per_Warning.Usual_Per_Warn_Activity;
import ShiAoOfficeBuilding.Water_Warning.Water_Warn_Activity;
import ShiAoOfficeBuilding.importancePeople.ImpotantPointActivity;
import ShiAoOfficeBuilding.tools.datepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.shiaoofficebuilding.R;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void roomlist(View view) {
        Intent intent =new Intent(this,getRoomList.class);
        startActivity(intent);
    }

    public void waterUse(View view) {
        Intent intent =new Intent(this, Water_Warn_Activity.class);
        startActivity(intent);
    }

    public void electricityUse(View view) {
        Intent intent =new Intent(this, Elec_Warn_Activity.class);
        startActivity(intent);
    }

    public void apartment(View view) {
        Intent intent =new Intent(this, Temp_Per_Warn_Activity.class);
        startActivity(intent);
    }
    public void staff(View view) {
        Intent intent =new Intent(this, Usual_Per_Warn_Activity.class);
        startActivity(intent);
    }


    public void viewpager(View view) {
        Intent intent =new Intent(this, ImpotantPointActivity.class);
        startActivity(intent);
    }
}
