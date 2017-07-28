package com.dlxy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.dlxy.Dataorigin.Data;
import com.dlxy.MyAdapter.ShoyeAdapter;

import main.dlxy.com.Activity.JDIAN;
import main.dlxy.com.Activity.LvYou;
import main.dlxy.com.Activity.SYXQActivity;
import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/11.
 */
//
public class Shouyefragment extends Fragment implements View.OnClickListener {
    private View view;
    private ViewFlipper flipper;
    private float startX;
    private ArrayAdapter<String> arrayAdapter,arrayAdapter1,arrayAdapter2,arrayAdapter3;
    private ShoyeAdapter shoteAdapter;
    private GridView gridView,gridView1,gridView2,gridView3,gridView4;
    private String name[] = Data.shouye_name;
    private String name1[] = Data.shouye_name1;
    private String name2[] = Data.shouye_name2;
    private String name3[] = Data.shouye_name3;
    private String name4[] = Data.shouye_name4;
    private int[] img1 = Data.shouye_img1;
    private int[] img = Data.shouye_img;
    private Button jiudian;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.shouye_layout, container, false);
        }
        itinView(view);
        return view;
    }

    private void itinView(View view) {
        flipper = view.findViewById(R.id.viewFlipper);
        gridView =view.findViewById(R.id.item_gridView_layout);
        gridView1 =view.findViewById(R.id.item_gridView_layout1);
        gridView2 =view.findViewById(R.id.item_gridView_layout2);
        gridView3 =view.findViewById(R.id.item_gridView_layout3);
        gridView4 =view.findViewById(R.id.item_gridView_layout4);
        view.findViewById(R.id.id_jiudian_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view.findViewById(R.id.id_jipiao_button).setOnClickListener(this);
        view.findViewById(R.id.id_lvyou_button).setOnClickListener(this);
        arrayAdapter = new ArrayAdapter<String>(Shouyefragment.this.getActivity(),R.layout.item_textview_layout,R.id.tv_textView_layout,name);
        arrayAdapter1 = new ArrayAdapter<String>(this.getActivity(),R.layout.item_textview_layout1,R.id.tv_textView_layout1,name1);
        arrayAdapter2 = new ArrayAdapter<String>(this.getActivity(),R.layout.item_textview_layout2,R.id.tv_textView_layout2,name2);
        arrayAdapter3 = new ArrayAdapter<String>(this.getActivity(),R.layout.item_textview_layout3,R.id.tv_textView_layout3,name3);
        shoteAdapter = new ShoyeAdapter(Shouyefragment.this.getContext(),img1,name4);
        gridView4.setAdapter(shoteAdapter);
        gridView3.setAdapter(arrayAdapter3);
        gridView2.setAdapter(arrayAdapter2);
        gridView1.setAdapter(arrayAdapter1);
        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(Shouyefragment.this.getContext(), JDIAN.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
//        jiudian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Shouyefragment.this.getContext(), SYXQActivity.class);
//                startActivity(intent);
//            }
//        });
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(Shouyefragment.this.getActivity(), SYXQActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name1[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        //动态导入的方式为ViewFlipper加入View
        for (int i = 0; i < img.length; i++) {
            flipper.addView(getImageView(img[i]));
        }
        //ViewFlipper去添加动画效果
        flipper.setInAnimation(this.getContext(), R.anim.left_in);
        flipper.setOutAnimation(this.getContext(), R.anim.left_out);
        //设定ViewFlipper视图切换的时间间隔
        flipper.setFlipInterval(5000);
        //开始播放
        flipper.startFlipping();
    }

    private ImageView getImageView(int img) {
        ImageView imageView = new ImageView(Shouyefragment.this.getContext());
        imageView.setImageResource(img);
        return imageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) view.getParent()).removeView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_jipiao_button:
                Intent intent = new Intent(Shouyefragment.this.getActivity(),SYXQActivity.class);
                Bundle b = new Bundle();
                b.putString("name","飞机票");
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.id_lvyou_button:
                Intent intent1 = new Intent(Shouyefragment.this.getActivity(),LvYou.class);
              //  Toast.makeText(Shouyefragment.this.getActivity(),"00000000",Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                break;
        }
    }
}
