package com.dlxy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */
//
public class Shouyefragment extends Fragment {
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.shouye_layout,container,false);
        }
        return view;
    }
}
