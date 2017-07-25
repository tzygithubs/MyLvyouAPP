package com.dlxy.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dlxy.MyAdapter.SQLiteXingCheng;
import com.dlxy.MyAdapter.XingChengAdapter;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.Sqlite.DBManager;
import com.dlxy.domain.ShopCart;

import java.util.List;

import main.dlxy.com.Activity.DengRu;
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
    private List<ShopCart> list,listid ;
    private ListView listView1,listView2;
    private XingChengAdapter adapter ;
    private SQLiteXingCheng dbadapter;
    DBHelper dbHelper ;
    private int x ;

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
        SharedPreferences sp  = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
        boolean b =  sp.getBoolean("boolean",false);
        final String DATABASE_BIAOMING = sp.getString("name","123");
        //Toast.makeText(XingChengFragment.this.getActivity(),"..."+b,Toast.LENGTH_SHORT).show();
        listView1 = v.findViewById(R.id.item_xc_listView);
        v.findViewById(R.id.item_btn_shanchu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String n = x+"";
//                String n = "汽车";
//                dbHelper.delete(n,DATABASE_BIAOMING);
//                listid = dbHelper.query(DATABASE_BIAOMING);
//                int[] gg ;
//                String sqls = "select * from "+DATABASE_BIAOMING+"";
//                Cursor c = db.rawQuery(sqls, null);
//                while (c.moveToNext()){
//                    int id = c.getInt(c.getColumnIndex("_id"));
//                    Log.i(TAG,"........._id:"+id);
//
//                }

            }
        });

        adapter = new XingChengAdapter(v.getContext(),img);

        if(b==true){
            sp = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
            dbHelper = new DBHelper(this.getActivity());

           // String DATABASE_BIAOMING = sp.getString("name","123");
            list =  dbHelper.query( DATABASE_BIAOMING);

//            Toast.makeText(XingChengFragment.this.getActivity(),o,Toast.LENGTH_SHORT).show();

            dbadapter = new SQLiteXingCheng(this.getActivity(),list);
            listView1.setAdapter(dbadapter);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    x=i;
                    Toast.makeText(XingChengFragment.this.getActivity(),"....选择了："+x+"项",Toast.LENGTH_SHORT).show();
                }
            });
        }if(b==false){
            listView1.setAdapter(adapter);
        }



    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            finalize();
//            Toast.makeText(XingChengFragment.this.getActivity(),"000000",Toast.LENGTH_SHORT).show();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((ViewGroup)view.getParent()).removeView(view);
    }
}
