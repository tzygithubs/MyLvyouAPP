package main.dlxy.com.mylvyouapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> fragmentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        initview();
    }

    private void initview() {
        vp =findViewById(R.id.viewpager_main);
        rg = findViewById(R.id.radio_main);
        findViewById(R.id.radiobutton_shouye);
        findViewById(R.id.radiobutton_xingcheng);
        findViewById(R.id.radiobutton_faxian);
        findViewById(R.id.radiobutton_kefu);
        findViewById(R.id.radiobutton_wode);
        fragmentlist = new ArrayList<Fragment>();

    }
}
