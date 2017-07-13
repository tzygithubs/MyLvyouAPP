package com.dlxy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dlxy.MyAdapter.XingChengAdapter;

import XcActivity.MuDe;
import main.dlxy.com.mylvyouapp.R;


/**
 * Created by T on 2017/7/11.
 */

public class XingChengFragment extends Fragment {


    private View view;
    private int[] img ={R.mipmap.ic_launcher};

    private ListView listView1,listView2;
    private XingChengAdapter adapter;
    private android.widget.RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.item_xc_layout,container,false);
        }
        initView(view);
        return view;

    }

    private void initView(View v) {
        listView1 = v.findViewById(R.id.item_xc_listView);
        listView2 = v.findViewById(R.id.item_xc_listView1);
        relativeLayout = v.findViewById(R.id.relativeLayout1);
        adapter = new XingChengAdapter(v.getContext(),img);
        listView2.setAdapter(adapter);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XingChengFragment.this.getActivity(), MuDe.class);
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
