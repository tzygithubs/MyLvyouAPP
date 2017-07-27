package main.dlxy.com.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.dlxy.Dataorigin.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/28.
 */

public class LvYou extends Activity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private GridView gridView;
    private SimpleAdapter simpleAdapter;
    private RadioButton zhoubiam,dongnanya, gangaotai1 ,guonei1,meizhou1,ouzhou1,rihan1;
    private int[] zhoubian = Data.lvyou_zhoubian;
    private int[] dongnaya = Data.lvyou_dongnanya;
    private int[] gangaotai = Data.lvyou_gangaotai;
    private int[] guonei =Data.lvyou_guonei;
    private int[] meizhou =Data.lvyou_meizhou;
    private int[] ouzhou =Data.lvyou_ouzhou;
    private int[] rihan =Data.lvyou_rihan;
    private List<Map<String,Integer>> zhoubian_list,dongnaya_list,gangaotai_list,guonei_list,meizhou_lisy,ouzhou_list,rihan_list;

    public LvYou() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvyou_layout);
        initView();
    }

    private void initView() {
        radioGroup = findViewById(R.id.lvyou_radioGroup);
        gridView = findViewById(R.id.lvyou_gridView);
        zhoubiam =findViewById(R.id.lvyou_zhoubian);
        zhoubiam.setOnClickListener(this);
        dongnanya=findViewById(R.id.lvyou_gangaotai);
        dongnanya.setOnClickListener(this);
        gangaotai1=findViewById(R.id.lvyou_dongnany);
        gangaotai1.setOnClickListener(this);
        guonei1=findViewById(R.id.lvyou_guonei);
        guonei1.setOnClickListener(this);
        meizhou1=findViewById(R.id.lvyou_meizhou);
        meizhou1.setOnClickListener(this);
        ouzhou1=findViewById(R.id.lvyou_ouzhou);
        ouzhou1.setOnClickListener(this);
        rihan1=findViewById(R.id.lvyou_rihan);
        rihan1.setOnClickListener(this);

        zhoubian_list = new ArrayList<>();
        for (int i =0 ;i<zhoubian.length;i++){
            Map map= new HashMap();
            map.put("imag",zhoubian[i]);
            zhoubian_list.add(map);
        }
        dongnaya_list = new ArrayList<>();
        for (int i =0 ;i<dongnaya.length;i++){
            Map map= new HashMap();
            map.put("imag",dongnaya[i]);
            dongnaya_list.add(map);
        }
        gangaotai_list = new ArrayList<>();
        for (int i =0 ;i<gangaotai.length;i++){
            Map map= new HashMap();
            map.put("imag",gangaotai[i]);
            gangaotai_list.add(map);
        }
        guonei_list = new ArrayList<>();
        for (int i =0 ;i<guonei.length;i++){
            Map map= new HashMap();
            map.put("imag",guonei[i]);
            guonei_list.add(map);
        }
        meizhou_lisy = new ArrayList<>();
        for (int i =0 ;i<meizhou.length;i++){
            Map map= new HashMap();
            map.put("imag",meizhou[i]);
            meizhou_lisy.add(map);
        }
        ouzhou_list = new ArrayList<>();
        for (int i =0 ;i<ouzhou.length;i++){
            Map map= new HashMap();
            map.put("imag",ouzhou[i]);
            ouzhou_list.add(map);
        }
        rihan_list = new ArrayList<>();
        for (int i =0 ;i<rihan.length;i++){
            Map map= new HashMap();
            map.put("imag",rihan[i]);
            rihan_list.add(map);
        }
        radioGroup.check(R.id.lvyou_zhoubian);
        simpleAdapter = new SimpleAdapter(this,zhoubian_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
        gridView.setAdapter(simpleAdapter);

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lvyou_zhoubian:
                simpleAdapter = new SimpleAdapter(this,zhoubian_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                
                break;
            case R.id.lvyou_dongnany:
                simpleAdapter = new SimpleAdapter(this,dongnaya_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
            case R.id.lvyou_gangaotai:
                simpleAdapter = new SimpleAdapter(this,gangaotai_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
            case R.id.lvyou_guonei:
                simpleAdapter = new SimpleAdapter(this,guonei_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
            case R.id.lvyou_meizhou:
                simpleAdapter = new SimpleAdapter(this,meizhou_lisy,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
            case R.id.lvyou_ouzhou:
                simpleAdapter = new SimpleAdapter(this,ouzhou_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
            case R.id.lvyou_rihan:
                simpleAdapter = new SimpleAdapter(this,rihan_list,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
                gridView.setAdapter(simpleAdapter);
                break;
        }
        
    }
}
