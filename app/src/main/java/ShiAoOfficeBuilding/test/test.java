package ShiAoOfficeBuilding.test;
import ShiAoOfficeBuilding.Chart.Chart;
import  ShiAoOfficeBuilding.RoomList.getRoomList;
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

    public void waterwarning(View view) {
        Intent intent =new Intent(this, Chart.class);
        startActivity(intent);
    }
}
