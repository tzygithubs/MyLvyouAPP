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
 * Created by T on 2017/7/17.
 */

public class PiaowuAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String , Object>> list;
    private LayoutInflater inflater ;

    public PiaowuAdapter(Context context ,List<Map<String , Object>> list){
        this.context=context;
        this.list=list;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        HodleViewl hodleViewl = null;
       if (view == null){
           hodleViewl = new HodleViewl();
           view= inflater.inflate(R.layout.piaowu_shipei_layout,null);
           hodleViewl.zuowei = view.findViewById(R.id.piaowu_shipei_zuowei);
           hodleViewl.jiage = view.findViewById(R.id.piaowu_shipei_jiage);
           hodleViewl.youpiao = view.findViewById(R.id.piaowu_shipei_youpiao);
           hodleViewl.yuding = view.findViewById(R.id.piaowu_shipei_yuding);
           view.setTag(hodleViewl);
       }else {
           hodleViewl= (HodleViewl) view.getTag();
       }
       hodleViewl.zuowei.setText( list.get(position).get("zuowei").toString());
        hodleViewl.jiage.setText(list.get(position).get("jiage").toString());
        hodleViewl.youpiao.setText(list.get(position).get("youpiao").toString());
        hodleViewl.yuding.setText(list.get(position).get("yuding").toString());
        return view;
    }
    class  HodleViewl {
        TextView zuowei ,jiage,youpiao,yuding;

    }
}
