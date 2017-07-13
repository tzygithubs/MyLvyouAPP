package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxy.Utils.VolleyUtil;
import com.dlxy.domain.Yanzheng;
import com.dlxy.interfaces.LoginCallBack;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/12.
 */

public class DengRu extends Activity implements View.OnClickListener {
    private Button dr;
    private EditText zh , ma ;
    private TextView zc;
    Yanzheng yanzheng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengru_layout);
        initView();
    }


    private void initView() {
        dr =(Button) findViewById(R.id.bt_dengru);
        zh = findViewById(R.id.et_name);
        ma = findViewById(R.id.et_password);
        zc =(TextView) findViewById(R.id.text_zhuce);
        dr.setOnClickListener(this);
        zc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_dengru:
                String names = zh.getText().toString().trim();
                String password = ma.getText().toString().trim();
                Boolean yz = true;

                VolleyUtil.getInstance().login(names, password, new LoginCallBack() {
                    @Override
                    public void success(String info) {
                        yanzheng =new Yanzheng();
                        yanzheng.setYanzheng(true);
                        Intent intent = new Intent(DengRu.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void errr(String error) {
                        Toast.makeText(DengRu.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.text_zhuce:
                Intent intent = new Intent(DengRu.this,ZhuCe.class);
                startActivity(intent);
                break;
        }
    }
}
