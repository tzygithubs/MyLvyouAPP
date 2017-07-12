package com.dlxy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class FaXianFragment extends Fragment {
    private View view;
    private GridView gv;
    private List<HashMap<String, Object>> list;
    private String[] namesb = {
            "国际机票",
            "海外酒店",
            "出境游",
            "海外用车",
            "景点玩乐",
            "海外接送机",
            "保险·签证",
            "WiFi·电话卡",
            "全球购",
            "外币兑换"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.layout_found, container, false);
        }
        initVisw(view);
        return view;
    }

    private void initVisw(View v) {
        initData();
        gv = v.findViewById(R.id.gv);
        String[] from = {"imgdz", "text"};
        int[] to = {R.id.item_found_img, R.id.item_found_name};
        gv.setAdapter(new SimpleAdapter(view.getContext(), list, R.layout.item_found,from, to));
//        gv.setOnItemClickListener(this);
    }

    private void initData() {
        list = new ArrayList<HashMap<String, Object>>();
        int[] imgsb = {
                R.mipmap.found_feiji,
                R.mipmap.found_jd,
                R.mipmap.found_cjy,
                R.mipmap.found_hwyc,
                R.mipmap.found_jdwl,
                R.mipmap.found_hwyc,
                R.mipmap.found_bxqz,
                R.mipmap.found_wifi,
                R.mipmap.found_qqg,
                R.mipmap.found_wbdh
        };
        for (int i = 0; i < imgsb.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("imgdz", imgsb[i]);
            map.put("text", namesb[i]);
            list.add(map);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)view.getParent()).removeView(view);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
//        Intent intent = new Intent(FaXianFragment.this.getActivity(), MainActivity.class);
//
//        Bundle b = new Bundle();
//        b.putString("name", namesb[position]);
//        b.putInt("position", position);
//        intent.putExtras(b);
//        startActivity(intent);
//    }
}
