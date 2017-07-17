package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dlxy.Dataorigin.Data;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/17.
 */

public class SheZhi extends Activity implements View.OnClickListener {
    private ListView lv;
    private TextView text;
    SharedPreferences sp = null;
    ArrayAdapter<String> arrayAdapter;
    String[] shezhi = Data.shezhiData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi_layout);
        initView();

    }

    private void initView() {
        lv= findViewById(R.id.shezhi_lv);
        text = findViewById(R.id.shezhi_text);
        text.setOnClickListener(this);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.kefu_shipei_layout,shezhi);
        lv.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick(View view) {
        sp = getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
        sp.edit().putBoolean("boolean",false).commit();
        Intent intent = new Intent(SheZhi.this,MainActivity.class);
        Bundle b = new Bundle();
        b.putString("name","shezhi");

        intent.putExtras(b);
        startActivity(intent);

    }
}
