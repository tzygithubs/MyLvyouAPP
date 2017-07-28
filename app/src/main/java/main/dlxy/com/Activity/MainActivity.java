package main.dlxy.com.Activity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dlxy.Sqlite.DBHelper;
import com.dlxy.Sqlite.DBManager;
import com.dlxy.Utils.CustomViewPager;
import com.dlxy.fragment.KeFuFragment;
import com.dlxy.fragment.Shouyefragment;
import com.dlxy.fragment.WoDeFragment;
import com.dlxy.fragment.XingChengFragment;

import java.util.ArrayList;

import main.dlxy.com.mylvyouapp.R;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String TAG ="MainActivity";
    private CustomViewPager vp;
    private RadioGroup rg;
    private RadioButton shouye,xingcheng,kefu, wode;
    private  int n;
    SharedPreferences sp = null;
    private ArrayList<Fragment> fragmentlist;

    DBManager dbManager;
    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        initview();


    }




    private void initview() {
        dbManager = new DBManager(this);
        dbHelper = new DBHelper(this);

        try {
            sp = getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
            String DATABASE_BIAOMING = sp.getString("name",null);
            String sul = "create table if not exists "+DATABASE_BIAOMING+""+"(_id  integer primary key autoincrement,name varchar, jieshao varchar,jiner integer,kaishi varchar , zhongdian varchar )";
            db = dbManager.getWritableDatabase();
            db.execSQL(sul);
        } catch (Exception e) {
            e.printStackTrace();
        }



        vp = findViewById(R.id.viewpager_main);
        vp.setScanScroll(false);
        rg = findViewById(R.id.radio_main);
       shouye=findViewById(R.id.radiobutton_shouye);
        shouye.setOnClickListener(this);
        xingcheng =findViewById(R.id.radiobutton_xingcheng);
        xingcheng.setOnClickListener(this);
        kefu=findViewById(R.id.radiobutton_kefu);
        kefu.setOnClickListener(this);
        wode= findViewById(R.id.radiobutton_wode);
        wode.setOnClickListener(this);
        fragmentlist = new ArrayList<Fragment>();
        fragmentlist.add(new Shouyefragment());
        fragmentlist.add(new XingChengFragment(this));

        fragmentlist.add(new KeFuFragment());
        fragmentlist.add(new WoDeFragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentlist.get(position);
            }

            @Override
            public int getCount() {
                return fragmentlist.size();
            }
        };
        vp.setAdapter(fragmentPagerAdapter);
        vp.setOnPageChangeListener(this);
        rg.check(R.id.radiobutton_shouye);

        judge();

        intent();

    }

    private void judge() {

        if (shouye.isChecked()){
            shouye.setBackgroundResource(R.mipmap.im_rg_shouye_true);
        }else {
            shouye.setBackgroundResource(R.mipmap.im_rg_shouye_false);
        }
        if (xingcheng.isChecked()){
            xingcheng.setBackgroundResource(R.mipmap.im_rg_xingcheng_true);
        }else {
            xingcheng.setBackgroundResource(R.mipmap.im_gr_xingcheng_false);
        }
        if (kefu.isChecked()){
            kefu.setBackgroundResource(R.mipmap.im_rg_kefu_true);
        }else {
            kefu.setBackgroundResource(R.mipmap.im_rg_kefu_false);
        }
        if (wode.isChecked()){
            wode.setBackgroundResource(R.mipmap.im_rg_wode_true);
        }else {
            wode.setBackgroundResource(R.mipmap.im_rg_wode_false);
        }
    }

    public void finish() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        super.finish();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

//onPageSelected onPageScrolled
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onPageSelected(int position) {
    switch (position){
        case 0:
            rg.check(R.id.radiobutton_shouye);
            break;
        case 1:
            rg.check(R.id.radiobutton_xingcheng);

            break;

        case 2:
            rg.check(R.id.radiobutton_kefu);
            break;
        case 3:
            rg.check(R.id.radiobutton_wode);
            break;
    }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radiobutton_shouye:
                vp.setCurrentItem(0,true);
                judge();
                break;
            case R.id.radiobutton_xingcheng:
                vp.setCurrentItem(1,true);
                judge();
                break;

            case R.id.radiobutton_kefu:
                vp.setCurrentItem(2,true);
                judge();
                break;
            case R.id.radiobutton_wode:
                vp.setCurrentItem(3,true);
                judge();
                break;

        }
    }
    private void intent() {

        try {
            Bundle b ;
            b = getIntent().getExtras();
            String n = b.getString("name");

            if (n.equals("shezhi")) {
                rg.check(R.id.radiobutton_wode);
                vp.setCurrentItem(4,true);

            }else if (n.equals("wode_dengru")){
                rg.check(R.id.radiobutton_wode);
                vp.setCurrentItem(4,true);
            }

            else {
                rg.check(R.id.radiobutton_shouye);
                vp.setCurrentItem(0,true);
            }

//           Toast.makeText(this,".............."+n,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}