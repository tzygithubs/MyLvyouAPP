package com.dlxy.MyAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxy.Sqlite.DBHelper;
import com.dlxy.domain.ShopCart;

import java.util.List;
import java.util.Map;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/27.
 */

public class HotelAdapter extends BaseAdapter {
    private List<Map<String,Object>> list ;
    private Context context;
    private LayoutInflater inflater;
    DBHelper dbHelper;

    public HotelAdapter (Context context ,List<Map<String,Object>> list){
        this.context =context;
        this.list=list;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder Hoder = null;
        if (view == null){

            view = inflater.inflate(R.layout.hotel_adapter,null);
            Hoder = new ViewHoder();
            Hoder.room=view.findViewById(R.id.hotel_shipei_room);
            Hoder.jiege=view.findViewById(R.id.hotel_shipei_jiege);
            Hoder.day=view.findViewById(R.id.hotel_shipei_day);
            Hoder.bt = view.findViewById(R.id.hotel_shipei_button);
            view.setTag(Hoder);
        }else {
            Hoder = (ViewHoder) view.getTag();
        }
        Hoder.room.setText(list.get(i).get("room").toString());
        final int j= (int) list.get(i).get("expense");
        final String k = list.get(i).get("room").toString();
        final String d =list.get(i).get("day").toString();
        final String c = "("+list.get(i).get("key").toString()+")";
        final int z = (int) list.get(i).get("days");
        final String n =list.get(i).get("name").toString();
        if(k.equals("钟点房")){
            Hoder.jiege.setText(list.get(i).get("expense")+"");
            Hoder.day.setText("3小时");
        }else {
            Hoder.jiege.setText(j*z+"");
            Hoder.day.setText(list.get(i).get("day").toString());
        }


        Hoder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = context.getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
                String DATABASE_BIAOMING = sp.getString("name","123");

                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
                dbHelper = new DBHelper(context);
                ShopCart shopCart = new ShopCart();
                shopCart.setName(n);
                if(k.equals("钟点房")){
                    shopCart.setSum(j);
                    shopCart.setKaishi("3小时");
                }else {
                    shopCart.setSum(j*z);
                    shopCart.setKaishi(d);
                }

                shopCart.setJieshao(k);

                shopCart.setZhongdian(c);
                dbHelper.add(shopCart,DATABASE_BIAOMING);
            }
        });
        return view;
    }
    class  ViewHoder {
        TextView room,jiege,day;
        Button bt;

    }
}
