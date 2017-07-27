package com.dlxy.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/27.
 */

public class HotelAdapter extends BaseAdapter {
    private List<Map<String,String>> list ;
    private Context context;
    private LayoutInflater inflater;

    public HotelAdapter (Context context ,List<Map<String,String>> list){
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
            view.setTag(Hoder);
        }else {
            Hoder = (ViewHoder) view.getTag();
        }
        Hoder.room.setText(list.get(i).get("room"));
        Hoder.jiege.setText(list.get(i).get("expense"));
        Hoder.day.setText(list.get(i).get("day"));

        return view;
    }
    class  ViewHoder {
        TextView room,jiege,day;

    }
}
