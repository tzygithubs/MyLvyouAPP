package main.dlxy.com.Activity;

/**
 * Created by T on 2017/7/20.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.dlxy.fragment.FenJiFrament;
import com.dlxy.fragment.HuoCheFragment;
import com.dlxy.fragment.QiCheFragment;
import com.dlxy.fragment.TeJiaFragment;

import java.util.ArrayList;
import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/18.
 */

public class SYXQActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouyexinagqing_layout);

        initView();
    }

    private void initView() {

        radioGroup = findViewById(R.id.syxq_group_layout);
        viewPager = findViewById(R.id.syxq_pager_layout);
        getIntent();
        findViewById(R.id.id_fenji_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_huoche_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_qiche_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_tejia_shouyeXiangQingg_layout).setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        position = b.getInt("name");

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FenJiFrament());
        fragmentList.add(new HuoCheFragment());
        fragmentList.add(new QiCheFragment());
        fragmentList.add(new TeJiaFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewPager.setOnPageChangeListener(this);
        if(position == 0){
            radioGroup.check(R.id.id_fenji_shouyeXiangQingg_layout);
            viewPager.setCurrentItem(0,true);
        }else if(position ==1){
            radioGroup.check(R.id.id_huoche_shouyeXiangQingg_layout);
            viewPager.setCurrentItem(1,true);
        }else if(position ==2){
            radioGroup.check(R.id.id_qiche_shouyeXiangQingg_layout);
            viewPager.setCurrentItem(1,true);
        }else if(position ==3){
            radioGroup.check(R.id.id_tejia_shouyeXiangQingg_layout);
            viewPager.setCurrentItem(1,true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_fenji_shouyeXiangQingg_layout:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.id_huoche_shouyeXiangQingg_layout:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.id_qiche_shouyeXiangQingg_layout:
                viewPager.setCurrentItem(2,true);
                break;
            case R.id.id_tejia_shouyeXiangQingg_layout:
                viewPager.setCurrentItem(3,true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                radioGroup.check(R.id.id_fenji_shouyeXiangQingg_layout);
                break;
            case 1:
                radioGroup.check(R.id.id_huoche_shouyeXiangQingg_layout);
                break;
            case 2:
                radioGroup.check(R.id.id_qiche_shouyeXiangQingg_layout);
                break;
            case 3:
                radioGroup.check(R.id.id_tejia_shouyeXiangQingg_layout);
                break;

        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
