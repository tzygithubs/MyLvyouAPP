package main.dlxy.com.Activity;

/**
 * Created by T on 2017/7/20.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/18.
 */

public class SYXQActivity extends Activity implements View.OnClickListener {
    private Spinner spinner, spinner1;
    private RadioGroup radioGroup;
    private TextView text;
    String names ;
    private ArrayAdapter adpter;
    private Button button;
    private String n , m,k;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouyexinagqing_layout);

        initView();
    }

    private void initView() {
        spinner = findViewById(R.id.id_spinner_layout);
        spinner1 = findViewById(R.id.id_spinner1_layout);
        radioGroup = findViewById(R.id.syxq_group_layout);
        text = findViewById(R.id.id_text_shouyexiangqing);
        findViewById(R.id.id_btn_layout_item).setOnClickListener(this);
        findViewById(R.id.id_feiji_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_huoche_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_qiche_shouyeXiangQingg_layout).setOnClickListener(this);
        findViewById(R.id.id_tejia_shouyeXiangQingg_layout).setOnClickListener(this);
        final List<String> dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("济南");
        dataList.add("青岛");
        dataList.add("天津");
        dataList.add("江苏");
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adpter);
        spinner1.setAdapter(adpter);


        Bundle b = getIntent().getExtras();
        names = b.getString("name");
        text.setText(names);

        if (names.equals("火车票")){
            radioGroup.check(R.id.id_huoche_shouyeXiangQingg_layout);
        }else if( names.equals("机票")) {
            radioGroup.check(R.id.id_feiji_shouyeXiangQingg_layout);
        }else if(names.equals("汽车票") || names.equals("专车.租车")){
            radioGroup.check(R.id.id_qiche_shouyeXiangQingg_layout);
            text.setText("汽车票");
        }else if (names.equals("特价机票") ){
            radioGroup.check(R.id.id_tejia_shouyeXiangQingg_layout);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_huoche_shouyeXiangQingg_layout:
                text.setText("火车票");
                break;
            case R.id.id_feiji_shouyeXiangQingg_layout:
                text.setText("飞机票");
                break;
            case R.id.id_qiche_shouyeXiangQingg_layout:
                text.setText("汽车票");
                break;
            case R.id.id_tejia_shouyeXiangQingg_layout:
                text.setText("特价机票");
                break;
            case R.id.id_btn_layout_item:
                Intent intent = new Intent(SYXQActivity.this,PinBing.class);
                n =spinner.getSelectedItem().toString();
                m=spinner1.getSelectedItem().toString();
                k= text.getText().toString();
                Bundle b = new Bundle();
                b.putString("NAME",n);
                b.putString("name",m);
                b.putString("piao",k);
               // Toast.makeText(SYXQActivity.this,".....+++"+k,Toast.LENGTH_SHORT).show();
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
}