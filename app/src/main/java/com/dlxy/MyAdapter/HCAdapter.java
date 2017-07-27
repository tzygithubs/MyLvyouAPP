package com.dlxy.MyAdapter;

/**
 * Created by T on 2017/7/20.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import main.dlxy.com.mylvyouapp.R;


/**
 * Created by Administrator on 2017/7/19.
 */

public class HCAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private String[] shuju,shuju1,shuju2,shuju3;
    private String chushi ,zhongdian;

    public HCAdapter( Context context, String[] shuju, String[] shuju1, String[] shuju2, String[] shuju3,String chushi,String zhongdian) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.shuju = shuju;
        this.shuju1 = shuju1;
        this.shuju2 = shuju2;
        this.shuju3 = shuju3;
        this.chushi=chushi;
        this.zhongdian=zhongdian;
    }

    @Override
    public int getCount() {
        return shuju.length;
    }

    @Override
    public Object getItem(int position) {
        return shuju[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder3 holder3 =null;
        if (view == null){
            view = inflater.inflate(R.layout.cslb_item_id,null);
            holder3 = new ViewHolder3();
            holder3.textView1 = view.findViewById(R.id.tv_11111);
            holder3.textView2 = view.findViewById(R.id.shijian_id);
            holder3.textView3 = view.findViewById(R.id.ke_id);
            holder3.textView4 = view.findViewById(R.id.chufashi_id);
            holder3.textchu= view.findViewById(R.id.xian_item_id);
            holder3.textzhong = view.findViewById(R.id.xianxi_id);
            view.setTag(holder3);
        }else {
            holder3 = (ViewHolder3) view.getTag();
        }
        holder3.textView1.setText(shuju[position]);
        holder3.textView2.setText(shuju1[position]);
        holder3.textView3.setText(shuju2[position]);
        holder3.textView4.setText(shuju3[position]);
        holder3.textchu.setText(chushi);
        holder3.textzhong.setText(zhongdian);
        return view;
    }

    class ViewHolder3{
        TextView textView1,textView2,textView3,textView4,textchu,textzhong;
    }
}
