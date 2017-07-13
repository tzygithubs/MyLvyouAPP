package com.dlxy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxy.Utils.VolleyUtil;
import com.dlxy.domain.Customer;
import com.dlxy.interfaces.WodeCallBack;

import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */

public class WoDeFragment extends Fragment {
    private  static final String TAG="WoDeFragment";
    private TextView tv;

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
        String name ="lucas";
        VolleyUtil.getInstance().wode(name, new WodeCallBack() {

            @Override
            public void success(List<Customer> json) {

            }

            @Override
            public void errr(String error) {
                Toast.makeText(WoDeFragment.this.getActivity(),"..."+error,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
