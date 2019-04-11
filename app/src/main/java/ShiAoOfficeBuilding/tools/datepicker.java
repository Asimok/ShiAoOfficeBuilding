package ShiAoOfficeBuilding.tools;
 
import java.util.Calendar;
 
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.shiaoofficebuilding.R;

public class datepicker extends Activity {
	private EditText mEditText;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datepicker_layout);
 
		mEditText = (EditText) findViewById(R.id.editText1);
		mEditText.setOnTouchListener(new OnTouchListener() {
 
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					showDatePickDlg();
					return true;
				}
				return false;
			}
		});
		mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
 
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					showDatePickDlg();
				}
			}
		});
	}
 
	protected void showDatePickDlg() {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog datePickerDialog = new DatePickerDialog(datepicker.this, new OnDateSetListener() {
 
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				datepicker.this.mEditText.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show();
 
	}
 
}