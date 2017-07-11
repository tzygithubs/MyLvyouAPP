package main.dlxy.com.mylvyouapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.dlxy.fragment.FaXianFragment;
import com.dlxy.fragment.KeFuFragment;
import com.dlxy.fragment.Shouyefragment;
import com.dlxy.fragment.WoDeFragment;
import com.dlxy.fragment.XingChengFragment;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager vp;
    private RadioGroup rg;
    private ArrayList<Fragment> fragmentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        initview();
    }

    private void initview() {
        vp = findViewById(R.id.viewpager_main);
        rg = findViewById(R.id.radio_main);
        findViewById(R.id.radiobutton_shouye).setOnClickListener(this);
        findViewById(R.id.radiobutton_xingcheng).setOnClickListener(this);
        findViewById(R.id.radiobutton_faxian).setOnClickListener(this);
        findViewById(R.id.radiobutton_kefu).setOnClickListener(this);
        findViewById(R.id.radiobutton_wode).setOnClickListener(this);
        fragmentlist = new ArrayList<Fragment>();
        fragmentlist.add(new Shouyefragment());
        fragmentlist.add(new XingChengFragment());
        fragmentlist.add(new FaXianFragment());
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
        vp.addOnPageChangeListener(this);
        rg.check(R.id.radiobutton_shouye);

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
            rg.check(R.id.radiobutton_faxian);
            break;
        case 3:
            rg.check(R.id.radiobutton_kefu);
            break;
        case 4:
            rg.check(R.id.radiobutton_wode);
            break;
    }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radiobutton_shouye:
                vp.setCurrentItem(0,true);
                break;
            case R.id.radiobutton_xingcheng:
                vp.setCurrentItem(1,true);
                break;
            case R.id.radiobutton_faxian:
                vp.setCurrentItem(2,true);
                break;
            case R.id.radiobutton_kefu:
                vp.setCurrentItem(3,true);
                break;
            case R.id.radiobutton_wode:
                vp.setCurrentItem(4,true);
        }
    }
}