package com.dlxy.fragment;

/**
 * Created by T on 2017/7/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import main.dlxy.com.Activity.PinBing;
import main.dlxy.com.mylvyouapp.R;


/**
 * Created by Administrator on 2017/7/18.
 */

public class FenJiFrament extends Fragment {

    private View view;
    private Spinner spinner,spinner1;
    private ListView listView;
    private Button button;
    private  ArrayAdapter adpter;
    String n ;
    String m;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view =inflater.inflate(R.layout.item_fenji_layout,container,false);
        }
        initView();
        return view;
    }

    private void initView() {
        spinner = view.findViewById(R.id.id_spinner_layout);
        spinner1 = view.findViewById(R.id.id_spinner1_layout);
        button = view.findViewById(R.id.id_btn_layout_item);
        final List<String> dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("济南");
        dataList.add("青岛");

        adpter = new ArrayAdapter<String>(FenJiFrament.this.getActivity(),android.R.layout.simple_spinner_item,dataList);
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adpter);
        spinner1.setAdapter(adpter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FenJiFrament.this.getContext(), PinBing.class);
                n = spinner.getSelectedItem().toString();
                m=spinner1.getSelectedItem().toString();
//                if (dataList.equals("济南")){
//
                    Bundle bundle = new Bundle();
                    bundle.putString("NAME",n);
                    bundle.putString("name",m);
                    intent.putExtras(bundle);
//                    Log.i("TAG","...ASDS:"+bundle);
//
//                }
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)view.getParent()).removeView(view);
    }


}
