package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxy.Sqlite.DBHelper;
import com.dlxy.Sqlite.DBManager;
import com.dlxy.Sqlite.DBcl;
import com.dlxy.Utils.VolleyUtil;
import com.dlxy.interfaces.LoginCallBack;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/12.
 */

public class DengRu extends Activity implements View.OnClickListener {
    public static final  String TAG = "DengRu";
    private Button dr;
    private EditText zh , ma ;
    private TextView zc;
    boolean b;
    private DBHelper dbHelper;
    private DBManager dbManager;
    private SQLiteDatabase db;
    SharedPreferences sp = null;



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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_dengru:
                final String names = zh.getText().toString().trim();
                String password = ma.getText().toString().trim();


                VolleyUtil.getInstance().login(names, password, new LoginCallBack() {
                    @Override
                    public void success(String info) {

                        if(info.equals("ok")) {
                            sp = getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
                            sp.edit().putString("name",names).putBoolean("boolean",true).commit();
                            creation();

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

    private void creation() {
        dbHelper = new DBHelper(this);

        b = true;
        Cursor c=db.rawQuery("SELECT count * FROM sqlite_master WHERE type='table' AND name='"+DBcl.DATABASE_BIAOMING+"'", null);
        if(c.getInt(0)==0){
            b=false;
        }
        if(b==false){
            String sul = "create table "+ DBcl.DATABASE_BIAOMING+"(_id  integer primary key autoincrement,name varchar, position varchar,jiner integer)";
            db.execSQL(sul);
        }

    }
}
