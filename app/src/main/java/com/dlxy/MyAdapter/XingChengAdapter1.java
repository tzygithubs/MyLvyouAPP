package com.dlxy.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import main.dlxy.com.mylvyouapp.R;


/**
 * Created by T on 2017/7/14.
 */

public class XingChengAdapter1 extends BaseAdapter {
    private String[] name,name1,name2;
    private int[] img ;
    private Context context;
    private LayoutInflater layoutInflater;

    public XingChengAdapter1(String[] name, String[] name1, String[] name2, int[] img, Context context) {
        this.name = name;
        this.name1 = name1;
        this.name2 = name2;
        this.img = img;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        HodleView1 hodleView1 = null;
        if (view == null){
            hodleView1 = new HodleView1();
            view = layoutInflater.inflate(R.layout.item_mulu_layout,null);
            hodleView1.textView = view.findViewById(R.id.tv_adapter_layout_mu);
            hodleView1.textView1 = view.findViewById(R.id.tv_adapter_layout_mu1);
            hodleView1.textView2 = view.findViewById(R.id.tv_adapter_layout_mu2);
            hodleView1.imageView = view.findViewById(R.id.img_adapter_layout_mu);
            view.setTag(hodleView1);
        }else{
            hodleView1 = (HodleView1) view.getTag();
        }
        hodleView1.textView.setText(name[position]);
        hodleView1.textView1.setText(name1[position]);
        hodleView1.textView2.setText(name2[position]);
        hodleView1.imageView.setImageResource(img[position]);
        return view;
    }

    class HodleView1{
        TextView textView,textView1,textView2;
        ImageView imageView;
    }
}
