package main.dlxy.com.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by A on 2017/7/25.
 */

public class JDIAN extends Activity implements View.OnClickListener {
    private Spinner spinn;
    private EditText ed;

    private RadioGroup rg;
    private String names;
    private ArrayAdapter adapter;
    private Button button;
    private TextView text;
    private String n;
    private String t;
    List<String> list_all ,list_overseas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiudian_layout);

        initView();
    }

    private void initView() {
        spinn = findViewById(R.id.cs_id_spinner1_layout);
        rg = findViewById(R.id.syxq_group1_id_layout);
        text = findViewById(R.id.id_text1_shouyexiangqing);
        ed =findViewById(R.id.cs_id_ed);
        findViewById(R.id.id_btn1_layout_item).setOnClickListener(this);
        findViewById(R.id.id_hw).setOnClickListener(this);
        findViewById(R.id.id_tg).setOnClickListener(this);
        findViewById(R.id.id_tjjd).setOnClickListener(this);
        findViewById(R.id.id_mskz).setOnClickListener(this);

        rg.check(R.id.id_hw);

        list_overseas= new ArrayList<String>();
        list_overseas.add("曼谷");
        list_overseas.add("夏威夷");
        list_overseas.add("巴厘岛");
        list_overseas.add("爱琴海");
        list_overseas.add("巴黎");
        list_overseas.add("莫斯科");

        list_all = new ArrayList<String>();
        list_all.add("青岛");
        list_all.add("成都");
        list_all.add("西安");
        list_all.add("上海");
        list_all.add("三亚");
        list_all.add("北京");
        list_all.add("曼谷");
        list_all.add("巴厘岛");
        list_all.add("夏威夷");
        list_all.add("爱琴海");
        list_all.add("巴黎");
        list_all.add("莫斯科");

        Bundle b = getIntent().getExtras();
        names = b.getString("name");
        text.setText(names);
        if (names.equals("海外")) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_overseas);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinn.setAdapter(adapter);
        }else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_all);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinn.setAdapter(adapter);
        }


        if (names.equals("海外")){
            rg.check(R.id.id_hw);
        }else if(names.equals("团购")){
            rg.check(R.id.id_tg);
        }else if(names.equals("周边")){
            rg.check(R.id.id_tjjd);
        }else if(names.equals("民宿·客栈")){
            rg.check(R.id.id_mskz);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_hw:
                text.setText("海外");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_overseas);
                spinn.setAdapter(adapter);
                break;
            case R.id.id_tg:
                text.setText("团购");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_all);
                spinn.setAdapter(adapter);
                break;
            case R.id.id_tjjd:
                text.setText("周边");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_all);
                spinn.setAdapter(adapter);
                break;
            case R.id.id_mskz:
                text.setText("民宿·客栈");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_all);
                spinn.setAdapter(adapter);
                break;
            case R.id.id_btn1_layout_item:
                int k = Integer.parseInt(ed.getText().toString());
                Intent intent = new Intent(this,JDIANXQ.class);
                n = spinn.getSelectedItem().toString();
                t = text.getText().toString();
                Bundle b = new Bundle();
                b.putString("n",n);
                b.putString("t",t);
                b.putInt("day",k);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
}

