package main.dlxy.com.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.dlxy.MyAdapter.HotelAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/27.
 */

public class Hotel extends Activity{
    private TextView text;
    private ListView lv ;
    private String Text ,jiudian ,day;
    private List<Map<String,String>> list ;
    private HotelAdapter hotelAdapter;
    private String[] room = {"总统套房","公寓套房","商务套房","标准房","钟点房"};
    private int[] expense = {20000,10000,6000,3000,1000};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_layout);
        Bundle b = getIntent().getExtras();
        Text = b.getString("text");
        jiudian =b.getString("biaoti");
        day = b.getString("day");
        initView();

    }

    private void initView() {
        text= findViewById(R.id.hotel_biaoti);
        lv = findViewById(R.id.hotel_lvq);
        text.setText(jiudian);
        String k = day+"天";
        list = new ArrayList<Map<String, String>>();

        for (int i =0;i<room.length;i++){
            Map map = new HashMap();
            map.put("room",room[i]);
            map.put("expense",expense[i]+"");
            map.put("day",k);
            list.add(map);

        }

        hotelAdapter = new HotelAdapter(Hotel.this,list);
        lv.setAdapter(hotelAdapter);


    }
}
