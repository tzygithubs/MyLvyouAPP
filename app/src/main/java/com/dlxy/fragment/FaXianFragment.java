package com.dlxy.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dlxy.Dataorigin.Data;
import com.dlxy.MyAdapter.PiaowuAdapter;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.domain.ShopCart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class FaXianFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG ="FaXianFragment";
    private View view;
    private ListView lv;

    private String[] zuowei = Data.Zuowei;
    private int[] jiage = Data.JiaGe;
    private List<Map<String, Object>> list;
    private PiaowuAdapter piaowuAdapter = null;
    DBHelper dbHelper =null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.shangping_piaowu_layout, container, false);
        }
        init(view);
        return view;
    }

    private void init(View view) {
        lv= view.findViewById(R.id.piaowu_lv);
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        Date curDate  = new Date(System.currentTimeMillis());
        String str = sdf.format(curDate);

        list = new ArrayList<Map<String , Object>>();
        for(int i =0;i<zuowei.length;i++) {
            Map map = new HashMap<String, Object>();
            map.put("zuowei",zuowei[i]);
            map.put("jiage",jiage[i]);
            map.put("youpiao","有票");
            map.put("yuding","预订");
            list.add(map);
        }
        piaowuAdapter = new PiaowuAdapter(this.getActivity(),list);
        lv.setAdapter(piaowuAdapter);
        lv.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        dbHelper = new DBHelper(this.getActivity());
        ShopCart shopCart = new ShopCart();
        shopCart.setName("火车票");
        shopCart.setSum(jiage[i]);
        shopCart.setJieshao(list.get(i).get("zuowei").toString());
        SharedPreferences sp = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
        String DATABASE_BIAOMING = sp.getString("name","123");
        dbHelper.add(shopCart ,DATABASE_BIAOMING);
    }
}
