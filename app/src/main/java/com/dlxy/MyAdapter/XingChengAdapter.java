package com.dlxy.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/14.
 */

public class XingChengAdapter extends BaseAdapter {
    private static final String TAG = "XingChengAdapter";
    private Context context;
    private int[] img ;
    private LayoutInflater layoutInflater;
    public XingChengAdapter(Context context,int[] img) {
        this.context=context;
        this.img=img;
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
        HoldeView holdeView = null;
        if (view == null){
            holdeView = new HoldeView();
            view = layoutInflater.inflate(R.layout.item_xc_adapter,null);
            holdeView.imageView = view.findViewById(R.id.item_img_adapter);
            holdeView.button = view.findViewById(R.id.item_btn_adapter);
            view.setTag(holdeView);
        }else{
            holdeView = (HoldeView) view.getTag();
        }
        holdeView.imageView.setImageResource(img[position]);
        holdeView.button.setTag(position);
        holdeView.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XingChengAdapter.this.context, DengRu.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

    class HoldeView{
        ImageView imageView;
        Button button;
    }

}
