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
        zhoubian_list = panduan(zhoubian_list,zhoubian);
        dongnaya_list = panduan(dongnaya_list,dongnaya);
        gangaotai_list =panduan(gangaotai_list,gangaotai);
        guonei_list = panduan(guonei_list,guonei);
        meizhou_lisy = panduan(meizhou_lisy,meizhou);
        ouzhou_list = panduan(ouzhou_list,ouzhou);
        rihan_list = panduan(rihan_list,rihan);

        radioGroup.check(R.id.lvyou_zhoubian);
        shipei(zhoubian_list);

    }
    public List<Map<String,Integer>> panduan(List<Map<String,Integer>> lists,int[] img){
        lists = new ArrayList<>();
        for (int i=0;i<img.length;i++) {
            Map map = new HashMap();
            map.put("imag",img[i]);
            lists.add(map);
        }
        return lists;
    }

    public void shipei(List<Map<String,Integer>> lists){
        simpleAdapter = new SimpleAdapter(this,lists,R.layout.lvyou_adapter,new String[]{"imag"},new int[]{R.id.lvyou_adapter});
        gridView.setAdapter(simpleAdapter);

}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lvyou_zhoubian:
                shipei(zhoubian_list);
                break;
            case R.id.lvyou_dongnany:
                shipei(dongnaya_list);
                break;
            case R.id.lvyou_gangaotai:
                shipei(gangaotai_list);
                break;
            case R.id.lvyou_guonei:
                shipei(guonei_list);
                break;
            case R.id.lvyou_meizhou:
                shipei(meizhou_lisy);
                break;
            case R.id.lvyou_ouzhou:
                shipei(ouzhou_list);
                break;
            case R.id.lvyou_rihan:
                shipei(rihan_list);
                break;
        }
        
    }
}
