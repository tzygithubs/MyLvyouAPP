package main.dlxy.com.Activity;

/**
 * Created by T on 2017/7/20.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dlxy.MyAdapter.HCAdapter;

import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class PinBing extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RadioGroup radioGroup;
    private ListView listView;
    private List<Fragment> fragmentList;
    private TextView tv;
    private String chushi;
    private String zhongdian;
    private HCAdapter adapter;
    private String[] shuju={"01:01","02:02","03:01","04:02",};
    private String[] shuju1={"16时11分","12时11分","11时15分","10时11分"};
    private String[] shuju2={"Z14","K1512","W9184","I1111"};
    private String[] shuju3={"17:11","18:12","19:30","20:20"};
    private String piao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_item_pin_layout);


        try {
            Bundle  bundle = getIntent().getExtras();
            chushi = bundle.getString("NAME");
            zhongdian= bundle.getString("name");
            piao = bundle.getString("piao");
            SharedPreferences sp = getSharedPreferences("didian",PinBing.MODE_PRIVATE);
            sp.edit().putString("chu",chushi).putString("zhong",zhongdian).putString("piao",piao).commit();
        } catch (Exception e) {
            SharedPreferences sp  =getSharedPreferences("didian",PinBing.MODE_PRIVATE);
            chushi=sp.getString("chu","北京");
            zhongdian=sp.getString("zhong","北京");
            piao = sp.getString("piao","火车票");
        }


        //Toast.makeText(PinBing.this,"....."+chushi+zhongdian,Toast.LENGTH_SHORT).show();
        initView();
    }

    private void initView() {

        radioGroup = findViewById(R.id.home_rg);
        listView = findViewById(R.id.PinBing_lv);
        tv = findViewById(R.id.id_tv_layout_item);
        findViewById(R.id.home_rb_index).setOnClickListener(this);
        findViewById(R.id.home_rb_category).setOnClickListener(this);
        findViewById(R.id.home_rb_cart).setOnClickListener(this);
        findViewById(R.id.home_rb_mine).setOnClickListener(this);
        radioGroup.check(R.id.home_rb_index);
        adapter = new HCAdapter(this,shuju,shuju1,shuju2,shuju3,chushi,zhongdian);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        if(piao.equals("火车票")){
            radioGroup.check( R.id.home_rb_index);
        }else if (piao.equals("飞机票")){
            radioGroup.check(R.id.home_rb_category);
        }else if (piao.equals("汽车票")){
            radioGroup.check(R.id.home_rb_cart);
        }else if (piao.equals("特价机票")){
            radioGroup.check(R.id.home_rb_mine);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_rb_index:
                adapter = new HCAdapter(this,shuju,shuju1,shuju2,shuju3,chushi,zhongdian);
                listView.setAdapter(adapter);
                piao = "火车票";
                return;

             case R.id.home_rb_category:
                adapter = new HCAdapter(this,shuju,shuju1,shuju2,shuju3,chushi,zhongdian);
                listView.setAdapter(adapter);
                 piao = "飞机票";
                return;

             case R.id.home_rb_cart:
                adapter = new HCAdapter(this,shuju,shuju1,shuju2,shuju3,chushi,zhongdian);
                listView.setAdapter(adapter);
                 piao = "汽车票";
                return;

             case R.id.home_rb_mine:
                adapter = new HCAdapter(this,shuju,shuju1,shuju2,shuju3,chushi,zhongdian);
                listView.setAdapter(adapter);
                 piao = "特价机票";
                return;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(PinBing.this,XiangQing.class);
        Bundle b  = new Bundle();
        b.putString("C",chushi);
        b.putString("Z",zhongdian);
        b.putString("P",piao);
        intent.putExtras(b);
        startActivity(intent);
    }


}
