package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dlxy.MyAdapter.JdxqAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by A on 2017/7/25.
 */
public class  JDIANXQ extends Activity {
    private ListView lv;
    private JdxqAdapter adapter;
    private String names, t ;
    private int d;
    private TextView textView;
    private int[] img = {R.mipmap.j1, R.mipmap.j2, R.mipmap.j3, R.mipmap.j4, R.mipmap.j5};
    private String[] NAME = {"世佳惠选酒店", "盛大酒店", "小老鼠酒店", "世纪酒店", "青龙山酒店"};
    private String[] NAME1 = {"【暑期预售】 标准间", "【暑期预售】 高级间", "【暑期预售】 钟点间", "【暑期预售】 标准间", "【暑期预售】 标准间"};
    private String[] NAME2 = {"【含早】阳光房", "【风趣】夏日房", "【有趣】柴房", "【含早】阳光房", "【含早】阳光房"};
    private String[] NAME3 = {"￥111", "￥211", "￥611", "￥151", "￥411"};
    private String[] NAME4 = {"已售110", "已售111", "已售911", "已售10", "已售70"};
    private ArrayList<HashMap<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jdxq_1layout);

        initView();
    }

    private void initView() {
        initData();
        lv = findViewById(R.id.lvs_id_item);
        textView = findViewById(R.id.sd_id);
        Bundle b = getIntent().getExtras();
        names = b.getString("n");
        t = b.getString("t");
        d=b.getInt("day");
        textView.setText(names);
        adapter = new JdxqAdapter(this, list,names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(JDIANXQ.this,Hotel.class);
                Bundle b = new Bundle();
                b.putString("biaoti",names+NAME[i]);
                b.putString("text",t);
                b.putInt("day",d);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
//        if (t.equals("海外")) {
//            if (names.equals("曼谷")) {
//                String NAME5 = "曼谷";
//                adapter = new JdxqAdapter(this, list,NAME5);
//                lv.setAdapter(adapter);
//            }else if (names.equals("夏威夷")){
//                String NAME5 = "夏威夷";
//                adapter = new JdxqAdapter(this,list, NAME5);
//                lv.setAdapter(adapter);
//            }
//        }else if (t.equals("团购")){
//            if (names.equals("曼谷")) {
//                String NAME5 = "曼谷";
//                adapter = new JdxqAdapter(this, list,NAME5);
//                lv.setAdapter(adapter);
//            }else if (names.equals("夏威夷")){
//                String NAME5 = "夏威夷";
//                adapter = new JdxqAdapter(this,list, NAME5);
//                lv.setAdapter(adapter);
//            }
//        }else if (t.equals("特价酒店")){
//            if (names.equals("曼谷")) {
//                String NAME5 = "曼谷";
//                adapter = new JdxqAdapter(this, list,NAME5);
//                lv.setAdapter(adapter);
//            }else if (names.equals("夏威夷")){
//                String NAME5 = "夏威夷";
//                adapter = new JdxqAdapter(this,list, NAME5);
//                lv.setAdapter(adapter);
//            }
//        }else if (t.equals("民宿·客栈")){
//            if (names.equals("曼谷")) {
//                String NAME5 = "曼谷";
//                adapter = new JdxqAdapter(this, list,NAME5);
//                lv.setAdapter(adapter);
//            }else if (names.equals("夏威夷")){
//                String NAME5 = "夏威夷";
//                adapter = new JdxqAdapter(this,list, NAME5);
//                lv.setAdapter(adapter);
//            }
//        }
    }

    private void initData() {
        list = new ArrayList<HashMap<String, Object>>();
        for (int i=0;i<img.length;i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("IMG",img[i]);
            map.put("NAME",NAME[i]);
            map.put("NAME1",NAME1[i]);
            map.put("NAME2",NAME2[i]);
            map.put("NAME3",NAME3[i]);
            map.put("NAME4",NAME4[i]);
            list.add(map);

        }
    }
}
