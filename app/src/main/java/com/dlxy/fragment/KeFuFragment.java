package com.dlxy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dlxy.Dataorigin.Data;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class KeFuFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View view;
    private ListView lv ;
    private RadioGroup radioGroup;
    private RadioButton shouqian ,shouhou;
    Data data = new Data();
    String[] kefu1 =data.shouqiankf;
    String[] kefu2 = data.shouhoukf;
    String[] kefu1_huida = Data.shouqiankf_huida;
    String[] kefu2_huida = Data.shouhoukf_huida;
    private  ArrayAdapter<String> arrayAdapter;
    private  ArrayAdapter<String> arrayAdapters;
    private String pd = "1";
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
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radiobutton_shoutiankefu:
                lv.setAdapter(arrayAdapter);
                pd = "1";
                break;
            case R.id.radiobutton_shouhoukefu:
                lv.setAdapter(arrayAdapters);
                pd="2";
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        showpopwindow(i);
    }

    private void showpopwindow(int i){
        View popview = View.inflate(KeFuFragment.this.getActivity(),R.layout.kefu_popwindow,null);
        ImageView closeimag =popview.findViewById(R.id.kefu_window_imag);
        TextView huidatext = popview.findViewById(R.id.kefu_window_text);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 4/6;

        final PopupWindow popupWindow = new PopupWindow(popview,width,height);
        popupWindow.setFocusable(true);
        popupWindow.setSplitTouchEnabled(true);
        WindowManager.LayoutParams lp =  getActivity().getWindow().getAttributes();
        lp.alpha=0.2f;
        getActivity().getWindow().setAttributes(lp);

        popupWindow.showAtLocation(popview, Gravity.BOTTOM,0,50);

        closeimag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        if (pd.equals("1")){
            huidatext.setText(kefu1_huida[i]);
        }else {
            huidatext.setText(kefu2_huida[i]);
        }

        huidatext.setMovementMethod(ScrollingMovementMethod.getInstance());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }
}
