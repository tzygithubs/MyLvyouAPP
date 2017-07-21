package com.dlxy.MyAdapter;

/**
 * Created by T on 2017/7/20.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/14.
 */

public class ShoyeAdapter extends BaseAdapter {
    private int[] img1 ;
    private String name[];
    private Context context;
    private LayoutInflater layoutInflater;
    public ShoyeAdapter(Context context,int[] img1,String[] name4) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.img1 =img1;
        this.name = name4;
    }


    @Override
    public int getCount() {
        return img1.length;
    }

    @Override
    public Object getItem(int position) {
        return img1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        HoledView21 holedView21 = null;
        if (view ==null){
            holedView21 = new HoledView21();
            view = layoutInflater.inflate(R.layout.item_adapter_layout_jiu,null);
            holedView21.textView = view.findViewById(R.id.id_item_text_layout);
            holedView21.imageView = view.findViewById(R.id.id_item_img_layout);
            view.setTag(holedView21);
        }else {
            holedView21 = (HoledView21) view.getTag();
        }
        holedView21.imageView.setImageResource(img1[position]);
        holedView21.textView.setText(name[position]);
        return view;
    }

    final  class HoledView21{
        TextView textView;
        ImageView imageView;
    }
}