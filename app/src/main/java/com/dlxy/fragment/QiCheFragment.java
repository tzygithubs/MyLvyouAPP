package com.dlxy.fragment;

/**
 * Created by T on 2017/7/20.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.dlxy.com.mylvyouapp.R;


/**
 * Created by Administrator on 2017/7/18.
 */

public class QiCheFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view =inflater.inflate(R.layout.item_qiche_layout,container,false);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)view.getParent()).removeView(view);
    }
}
