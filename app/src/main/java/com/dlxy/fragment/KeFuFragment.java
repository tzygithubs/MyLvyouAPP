package com.dlxy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dlxy.Dataorigin.Data;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class KeFuFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ListView lv ;
    private RadioGroup radioGroup;
    private RadioButton shouqian ,shouhou;
    Data data = new Data();
    String[] kefu1 =data.shouqiankf;
    String[] kefu2 = data.shouhoukf;

    private  ArrayAdapter<String> arrayAdapter;
    private  ArrayAdapter<String> arrayAdapters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null){
            view=inflater.inflate(R.layout.kefu_layout_fragment,container,false);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        lv= view.findViewById(R.id.kefu_lv);
        radioGroup=view.findViewById(R.id.radio_kefu);
        view.findViewById(R.id.radiobutton_shoutiankefu).setOnClickListener(this);
        view.findViewById(R.id.radiobutton_shouhoukefu).setOnClickListener(this);
        radioGroup.check(R.id.radiobutton_shoutiankefu);

        arrayAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.kefu_shipei_layout,kefu1);
        arrayAdapters = new ArrayAdapter<String>(this.getActivity(),R.layout.kefu_shipei_layout,kefu2);
        lv.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radiobutton_shoutiankefu:

                shouqian();
                break;
            case R.id.radiobutton_shouhoukefu:
                lv.setAdapter(arrayAdapters);
                shouhou();
                break;
        }
    }

    private void shouhou() {

    }

    private void shouqian() {
        lv.setAdapter(arrayAdapter);
    }
}
