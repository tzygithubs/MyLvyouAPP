package com.dlxy.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by A on 2017/7/25.
 */

public class JdxqAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private int[] img;
    private String NAME5;
    private ArrayList<HashMap<String, Object>> list;

    public JdxqAdapter(Context context, ArrayList<HashMap<String, Object>> list, String NAME5) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.NAME5 = NAME5;
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
        if (view == null) {
            view = inflater.inflate(R.layout.jdadapter, null);
            Hoder = new ViewHoder();
            Hoder.imageView = view.findViewById(R.id.img_asdasd);
            Hoder.textView = view.findViewById(R.id.shjd);
            Hoder.textView1 = view.findViewById(R.id.sjys);
            Hoder.textView2 = view.findViewById(R.id.ygf);
            Hoder.textView3 = view.findViewById(R.id.yjj);
            Hoder.textView4 = view.findViewById(R.id.xsaa);
            Hoder.textView5 = view.findViewById(R.id.text1_id);
            view.setTag(Hoder);
        } else {
            Hoder = (ViewHoder) view.getTag();
        }
        Map<String, Object> itemData = list.get(i);
        int im = (int) itemData.get("IMG");
        Hoder.imageView.setImageResource(im);
        String name = (String) itemData.get("NAME");
        Hoder.textView.setText(name);
        String name1 = (String) itemData.get("NAME1");
        Hoder.textView1.setText(name1);
        String name2 = (String) itemData.get("NAME2");
        Hoder.textView2.setText(name2);
        String name3 = (String) itemData.get("NAME3");
        Hoder.textView3.setText(name3);
        String name4 = (String) itemData.get("NAME4");
        Hoder.textView4.setText(name4);
        Hoder.textView5.setText(NAME5);
        return view;
    }

    class ViewHoder {
        ImageView imageView;
        TextView textView, textView1, textView2, textView3, textView4, textView5;
    }
}
