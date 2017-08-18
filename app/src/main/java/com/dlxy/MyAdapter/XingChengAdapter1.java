package com.dlxy.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Map;


/**
 * Created by T on 2017/7/14.
 */

public class XingChengAdapter1 extends BaseAdapter {
    private List<Map<String,Object>> list;
    private Context context;
    private LayoutInflater inflater;

    public XingChengAdapter1(Context context,List<Map<String,Object>> list){
        this.list=list;
        this.context=context;
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
        HoldeView holdeView =null;
        return null;
    }
    class HoldeView{
        
    }
}
