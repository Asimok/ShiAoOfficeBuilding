package ShiAoOfficeBuilding.Chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.shiaoofficebuilding.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ShiAoOfficeBuilding.tools.dateUtils;

public class electricityUse extends AppCompatActivity {

    private List<String> mList;
    private List<String> mList2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart mLineChart = (LineChart) findViewById(R.id.lineChart);
        //显示边界
        mLineChart.setDrawBorders(true);
        String thisYearMonth=dateUtils.getYearAndMonth();
        mList=new ArrayList<>();
        mList2=new ArrayList<>();
        for(int i=0;i<12;i++)
        {
            try {
                mList2.add(dateUtils.subMonth(thisYearMonth,-1*i));

                Log.d("cc",dateUtils.subMonth(thisYearMonth,-1*i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<12;i++)
        {
                mList.add(mList2.get(11-i));


        }
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++)
        {
            entries.add(new Entry(i, (float) (Math.random()) * 5));
        }

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setLabelCount(12, true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(90);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
//Y轴
        YAxis leftYAxis = mLineChart.getAxisLeft();
        YAxis rightYAxis = mLineChart.getAxisRight();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(5f);

        rightYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMaximum(5f);
        rightYAxis.setEnabled(false); //右侧Y轴不显示


//描述
//隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);

//        Description description = new Description();
//        description.setText("X轴描述");
//        description.setTextColor(Color.RED);
//        mLineChart.setDescription(description);

//图例
        Legend legend = mLineChart.getLegend();
        legend.setFormSize(15f);
        legend.setTextColor(Color.CYAN); //设置Legend 文本颜色
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setWordWrapEnabled(true);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "近年电量变化图");
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        //设置显示值的字体大小
        lineDataSet.setValueTextSize(9f);
        LineData data = new LineData(lineDataSet);
        mLineChart.setData(data);
    }
}
