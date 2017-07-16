package com.dlxy.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dlxy.Utils.VolleyUtil;
import com.dlxy.interfaces.WodeCallBack;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class WoDeFragment extends Fragment {
    private  static final String TAG="WoDeFragment";
    private TextView tv;
    SharedPreferences sp = null;
    public  View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null){
                    view = inflater.inflate(R.layout.wode_layout,container, false);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv = view.findViewById(R.id.tv1);
        sp = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);

        String name =sp.getString("name",null);
        VolleyUtil.getInstance().wode(name, new WodeCallBack() {

            @Override
            public void success(String json) {
                JSONObject jsonObject = JSON.parseObject(json);
                String name=  jsonObject.get("name").toString();
                tv.setText(name);
            }

            @Override
            public void errr(String error) {
                Toast.makeText(WoDeFragment.this.getActivity(),"..."+error,Toast.LENGTH_SHORT).show();
            }
        });
    }




}
