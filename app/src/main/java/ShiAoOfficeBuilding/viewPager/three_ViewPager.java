package ShiAoOfficeBuilding.viewPager;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shiaoofficebuilding.R;

import java.util.ArrayList;

import ShiAoOfficeBuilding.Chart.electricityUse;
import ShiAoOfficeBuilding.Chart.fundation_Activity;
import ShiAoOfficeBuilding.Chart.waterUse;


public class three_ViewPager extends Activity implements View.OnClickListener {
    private NoScrollViewPager mViewPager = null;
    private ImageView mCursorImg = null;
    private TextView mOneTv = null;
    private TextView mTwoTv = null;
    private TextView mThreeTv = null;
    private String RoomNumber;
    private String statusname;
    private String usetypename;
    private String companyname;
    private String companytype;
    private String useobjguid;
    private ViewPagerAdapter mAdapter = null;
    private ArrayList<View> mPageList = null;
    private LocalActivityManager manager;
    private Intent intent4, intent5, intent1, intent2, intent3;

    NoScrollViewPager noscroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_three_layout);
     initdata();
        mViewPager = findViewById(R.id.view_pager);
        mOneTv = findViewById(R.id.viewpager_tv_one1);
        mTwoTv = findViewById(R.id.viewpager_tv_two1);
        mThreeTv = findViewById(R.id.viewpager_tv_three1);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        intent1 = new Intent(this, fundation_Activity.class);
        intent1.putExtra("companyname",companyname);
        intent1.putExtra("companytype",companytype);
        intent1.putExtra("useobjguid",useobjguid);


        View tab01 = manager.startActivity("viewID1", intent1).getDecorView();
        intent2 = new Intent(this, waterUse.class);
        intent2.putExtra("roomnum",RoomNumber);


        View tab02 = manager.startActivity("viewID1", intent2).getDecorView();
        intent3 = new Intent(this, electricityUse.class);
        intent3.putExtra("roomnum",RoomNumber);


        View tab03 = manager.startActivity("viewID1", intent3).getDecorView();


        mPageList = new ArrayList<>();
        mPageList.add(tab01);
        mPageList.add(tab02);
        mPageList.add(tab03);

        // 设置适配器
        mAdapter = new ViewPagerAdapter(mPageList);
        mViewPager.setAdapter(mAdapter);

        // 文本框点击监听器
        mOneTv.setOnClickListener(this);
        mTwoTv.setOnClickListener(this);
        mThreeTv.setOnClickListener(this);

        // 初始默认第一页
        mViewPager.setCurrentItem(0);
        mOneTv.setTextColor(Color.parseColor("#ffffff"));
        mOneTv.setBackgroundColor(Color.parseColor("#FF8BC34A"));

        mTwoTv.setBackgroundColor(Color.parseColor("#ffffff"));
        mThreeTv.setBackgroundColor(Color.parseColor("#ffffff"));

        mTwoTv.setTextColor(Color.parseColor("#000000"));
        mThreeTv.setTextColor(Color.parseColor("#000000"));
        mViewPager.setNoScroll(true);

    }

    private void initdata() {
        Intent intent=getIntent();
        RoomNumber  =intent.getStringExtra( "roomnum"      );
        statusname  =intent.getStringExtra( "statusname"   );
        usetypename  =intent.getStringExtra("usetypename" );
        companyname  =intent.getStringExtra("companyname" );
        companytype  =intent.getStringExtra("companytype" );
        useobjguid   =intent.getStringExtra("useobjguid"   );

        Log.d("ff", RoomNumber + statusname + usetypename + companyname + companytype + useobjguid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewpager_tv_one1:
                mViewPager.setCurrentItem(0);
                mOneTv.setTextColor(Color.parseColor("#ffffff"));
                mOneTv.setBackgroundColor(Color.parseColor("#FF8BC34A"));

                mTwoTv.setBackgroundColor(Color.parseColor("#ffffff"));
                mThreeTv.setBackgroundColor(Color.parseColor("#ffffff"));

                mTwoTv.setTextColor(Color.parseColor("#000000"));
                mThreeTv.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.viewpager_tv_two1:
                mViewPager.setCurrentItem(1);
                mTwoTv.setBackgroundColor(Color.parseColor("#FF8BC34A"));
                mTwoTv.setTextColor(Color.parseColor("#ffffff"));

                mOneTv.setBackgroundColor(Color.parseColor("#ffffff"));
                mThreeTv.setBackgroundColor(Color.parseColor("#ffffff"));


                mOneTv.setTextColor(Color.parseColor("#000000"));
                mThreeTv.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.viewpager_tv_three1:
                mViewPager.setCurrentItem(2);
                mThreeTv.setBackgroundColor(Color.parseColor("#FF8BC34A"));
                mThreeTv.setTextColor(Color.parseColor("#ffffff"));

                mTwoTv.setBackgroundColor(Color.parseColor("#ffffff"));
                mOneTv.setBackgroundColor(Color.parseColor("#ffffff"));

                mTwoTv.setTextColor(Color.parseColor("#000000"));
                mOneTv.setTextColor(Color.parseColor("#000000"));

                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {//还原字体大小
        Resources res = super.getResources();
        //非默认值
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }





}
