package ShiAoOfficeBuilding.test;
import ShiAoOfficeBuilding.Electric_Warning.Elec_Warn_Activity;
import ShiAoOfficeBuilding.TemperaryPersonWarning.Temp_Per_Warn_Activity;
import ShiAoOfficeBuilding.Usual_Per_Clock_Data.Usual_Per_Clock_Data_Activity;
import ShiAoOfficeBuilding.Usual_Per_Warning.Usual_Per_Warn_Activity;
import ShiAoOfficeBuilding.Water_Warning.Water_Warn_Activity;
import ShiAoOfficeBuilding.importancePeople.ImpotantPointActivity;
import ShiAoOfficeBuilding.Temp_Per_Clock_Data.Temp_Per_Clock_Data_Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
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
        Intent intent =new Intent(this, Temp_Per_Clock_Data_Activity.class);
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

    public void longpeople(View view) {

        Intent intent =new Intent(this, Usual_Per_Clock_Data_Activity.class);
        startActivity(intent);
    }
}
