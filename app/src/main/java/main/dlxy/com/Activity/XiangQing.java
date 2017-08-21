package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dlxy.Dataorigin.Data;
import com.dlxy.MyAdapter.PiaowuAdapter;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.domain.ShopCart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/21.
 */

public class XiangQing extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView lv;

    private String[] zuowei = Data.Zuowei;
    private int[] jiage = Data.JiaGe;
    private List<Map<String, Object>> list;
    private PiaowuAdapter piaowuAdapter = null;
    DBHelper dbHelper =null;
    private String chushi;
    private String zhongdian;
    private String piao ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangping_piaowu_layout);

        Bundle b = getIntent().getExtras();
        chushi = b.getString("C");
        zhongdian = b.getString("Z");
        piao = b.getString("P");
        initView();

    }

    private void initView() {
        lv= findViewById(R.id.piaowu_lv);
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        Date curDate  = new Date(System.currentTimeMillis());
        String str = sdf.format(curDate);
        findViewById(R.id.piaowu_fanhui).setOnClickListener(this);
        list = new ArrayList<Map<String , Object>>();

        for(int i =0;i<zuowei.length;i++) {
            Map map = new HashMap<String, Object>();
            map.put("zuowei",zuowei[i]);
            map.put("jiage",jiage[i]);
            map.put("youpiao","有票");
            map.put("yuding","预订");
            list.add(map);
        }
        piaowuAdapter = new PiaowuAdapter(this,list);
        lv.setAdapter(piaowuAdapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        try {
            SharedPreferences sp = getSharedPreferences("sp_demo", QQdengru.MODE_PRIVATE);
            String DATABASE_BIAOMING = sp.getString("name","123");
            Boolean b = sp.getBoolean("boolean",false);
            if (b ==true) {
                dbHelper = new DBHelper(this);
                ShopCart shopCart = new ShopCart();
                shopCart.setName(piao);
                shopCart.setSum(jiage[i]);
                shopCart.setJieshao(list.get(i).get("zuowei").toString());
                shopCart.setKaishi(chushi);
                shopCart.setZhongdian(zhongdian);

                dbHelper.add(shopCart, DATABASE_BIAOMING);
                Toast.makeText(XiangQing.this, "添加成功", Toast.LENGTH_SHORT).show();
            }else if (b==false){
                Toast.makeText(XiangQing.this, "请先登入", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.piaowu_fanhui:
                Intent intent = new Intent(XiangQing.this,PinBing.class);
                startActivity(intent);

                XiangQing.this.finish();


                break;
        }
    }

}
