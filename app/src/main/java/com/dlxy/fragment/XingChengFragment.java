package com.dlxy.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dlxy.MyAdapter.SQLiteXingCheng;
import com.dlxy.MyAdapter.XingChengAdapter;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.domain.ShopCart;

import java.util.List;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.Activity.MuDe;
import main.dlxy.com.mylvyouapp.R;


/**
 * Created by T on 2017/7/11.
 */

public class XingChengFragment extends Fragment {


    private View view;
    private int[] img ={R.mipmap.ic_launcher};
    private List<ShopCart> list ;
    private ListView listView1,listView2;
    private XingChengAdapter adapter ;
    private SQLiteXingCheng dbadapter;
    private android.widget.RelativeLayout relativeLayout;
    DBHelper dbHelper = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.item_xc_layout,container,false);
        }
        initView(view);
      //  Toast.makeText(XingChengFragment.this.getActivity(),"...........onpause",Toast.LENGTH_SHORT).show();
        return view;

    }

    private void initView(View v) {
        SharedPreferences sp  = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
        boolean b =  sp.getBoolean("boolean",false);
        //Toast.makeText(XingChengFragment.this.getActivity(),"..."+b,Toast.LENGTH_SHORT).show();
        listView1 = v.findViewById(R.id.item_xc_listView);

        relativeLayout = v.findViewById(R.id.relativeLayout1);
        adapter = new XingChengAdapter(v.getContext(),img);

        if(b==true){
            sp = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
            dbHelper = new DBHelper(this.getActivity());

            String DATABASE_BIAOMING = sp.getString("name","123");
            list =  dbHelper.query( DATABASE_BIAOMING);
//             int o = list.get(1).getSum();
//            Toast.makeText(XingChengFragment.this.getActivity(),o,Toast.LENGTH_SHORT).show();
            dbadapter = new SQLiteXingCheng(this.getActivity(),list);
            listView1.setAdapter(dbadapter);
        }if(b==false){
            listView1.setAdapter(adapter);
        }

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
