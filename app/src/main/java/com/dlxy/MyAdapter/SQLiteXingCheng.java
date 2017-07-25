package com.dlxy.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dlxy.domain.ShopCart;

import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/18.
 */

public class SQLiteXingCheng extends BaseAdapter {
    private Context context;
    private List<ShopCart> list;
    private LayoutInflater inflater ;

    public  SQLiteXingCheng(Context context , List<ShopCart> list){
        this.context = context;
        this.list = list;
        this.inflater =LayoutInflater.from(context);
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
        HodleViewl hodleViewl = null;
        if (view == null){
            hodleViewl = new HodleViewl();
            view = inflater.inflate(R.layout.item_xc_sqladapter,null);
            hodleViewl.name = view.findViewById(R.id.xc_name);
            hodleViewl.sum = view.findViewById(R.id.xc_sum);
            hodleViewl.jieshao = view.findViewById(R.id.xc_jieshao);
            hodleViewl.kaishi = view.findViewById(R.id.xc_kaishi);
            hodleViewl.zhongdian = view.findViewById(R.id.xc_zhongdian);
            view.setTag(hodleViewl);

        }else {
            hodleViewl = (HodleViewl) view.getTag();
        }


        hodleViewl.name.setText(list.get(i).getName());
        hodleViewl.sum.setText(list.get(i).getSum()+"");
        hodleViewl.jieshao.setText(list.get(i).getJieshao());
        hodleViewl.kaishi.setText(list.get(i).getKaishi());
        hodleViewl.zhongdian.setText(list.get(i).getZhongdian());
        return view;
    }



    class HodleViewl {
        TextView name,sum,jieshao,kaishi,zhongdian;
    }
}
