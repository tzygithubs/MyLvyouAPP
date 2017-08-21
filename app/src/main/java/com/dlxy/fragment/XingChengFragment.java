package com.dlxy.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dlxy.MyAdapter.SQLiteXingCheng;
import com.dlxy.MyAdapter.XingChengAdapter;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.Sqlite.DBManager;
import com.dlxy.Utils.QQListView;
import com.dlxy.domain.ShopCart;

import java.util.ArrayList;
import java.util.List;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.Activity.QQdengru;
import main.dlxy.com.mylvyouapp.R;


/**
 * Created by T on 2017/7/11.
 */

public class XingChengFragment extends Fragment {
    private static final String TAG ="XingChengFragement";
    private SQLiteDatabase db;
    private DBManager dbManager;
    private View view;
    private int[] img ={R.mipmap.ic_launcher};
    private List<ShopCart> list ;
    private ListView listView1,xinfaxian;
    private XingChengAdapter adapter ;
    private SQLiteXingCheng sqLiteXingCheng;
    DBHelper dbHelper ;
    private QQListView qqListView;
    public XingChengFragment (Context context){
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

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
        SharedPreferences sp  = this.getActivity().getSharedPreferences("sp_demo", QQdengru.MODE_PRIVATE);
        boolean b =  sp.getBoolean("boolean",false);
        final String DATABASE_BIAOMING = sp.getString("name",null);
        //Toast.makeText(XingChengFragment.this.getActivity(),"..."+b,Toast.LENGTH_SHORT).show();
        listView1 = v.findViewById(R.id.item_xc_listView);
        Log.i(TAG,"..........00000on  onCreateview");
        xinfaxian = v.findViewById(R.id.item_xc_xinfaxian_listView);
        adapter = new XingChengAdapter(v.getContext(),img);

        if(b==true){


                qqListView = view.findViewById(R.id.id_listview);
                Log.i(TAG,"........00list"+DATABASE_BIAOMING);
                dbHelper = new DBHelper(XingChengFragment.this.getActivity());
                List<ShopCart> list = dbHelper.query(DATABASE_BIAOMING);
                Log.i(TAG,"........00list"+list);
                sqLiteXingCheng = new SQLiteXingCheng(XingChengFragment.this.getActivity(),list);

                qqListView.setAdapter(sqLiteXingCheng);

                qqListView.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
                    @Override
                    public void clickHappend(int position) {
                        String i = position+1+"";
                        dbHelper.delete(i,DATABASE_BIAOMING);
                        List<ShopCart> lists = new ArrayList<ShopCart>();
                        lists=dbHelper.query(DATABASE_BIAOMING);
                        SQLiteXingCheng xincheng = new SQLiteXingCheng(XingChengFragment.this.getActivity(),lists);
                        qqListView.setAdapter(xincheng);
//
                    }
                });
                qqListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });




        }if(b==false){
            listView1.setAdapter(adapter);

        }



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"..........00000on  onResume");
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((ViewGroup)view.getParent()).removeView(view);
    }
}
