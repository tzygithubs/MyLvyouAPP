package main.dlxy.com.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dlxy.Utils.VolleyUtil;
import com.dlxy.contents.UserContents;
import com.dlxy.interfaces.RegistCallBack;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/12.
 */

public class ZhuCe extends Activity implements View.OnClickListener {
    private EditText yh , ma , nl ,xb;
    private Button zhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce_layout);
        initView();
    }

    private void initView() {
        yh =findViewById(R.id.ed_zhuce_yonghuming);
        ma= findViewById(R.id.ed_zhuce_mima);
        nl = findViewById(R.id.ed_zhuce_age);
        xb= findViewById(R.id.ed_zhuce_xingbie);
        zhuce  =findViewById(R.id.bt_zhuce_zhuce);

        zhuce.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_zhuce_zhuce:
                    String name = yh.getText().toString().trim();
                    String password = ma.getText().toString().trim();
                    String age = nl.getText().toString().trim();
                    String gender = xb.getText().toString().trim();


                VolleyUtil.getInstance().regist(name, password, age, gender, new RegistCallBack() {
                    @Override
                    public void registSuccess(String success) {
                        if(success.equals(UserContents.error_user_exit)) {
                            Toast.makeText(ZhuCe.this,"用户名已经存在",Toast.LENGTH_SHORT).show();
                        } else if(success.equals(UserContents.ok)) {
                            Toast.makeText(ZhuCe.this,"注册成功",Toast.LENGTH_SHORT).show();
                            ZhuCe.this.finish();

                        }
                    }

                    @Override
                    public void registError(String error) {
                        Toast.makeText(ZhuCe.this, "注册不成功",Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }
}
