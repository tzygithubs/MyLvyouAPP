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
import com.dlxy.domain.Customer;
import com.dlxy.interfaces.LoginCallBack;
import com.dlxy.interfaces.WodeCallBack;

import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/12.
 */

public class DengRu extends Activity implements View.OnClickListener {
    public static final  String TAG = "DengRu";
    private Button dr;
    private EditText zh , ma ;
    private TextView zc;



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
        String name ="lucas";
        VolleyUtil.getInstance().wode(name, new WodeCallBack() {

            @Override
            public void success(List<Customer> json) {
                for (Customer customer :json){
                    String name = customer.getName();
                    Toast.makeText(DengRu.this,"....name"+name,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void errr(String error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_dengru:
                String names = zh.getText().toString().trim();
                String password = ma.getText().toString().trim();


                VolleyUtil.getInstance().login(names, password, new LoginCallBack() {
                    @Override
                    public void success(String info) {

                        if(info.equals("ok")) {
                            Intent intent = new Intent(DengRu.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DengRu.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
                        }
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
