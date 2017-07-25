package main.dlxy.com.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dlxy.MyAdapter.SQLiteXingCheng;
import com.dlxy.Sqlite.DBHelper;
import com.dlxy.domain.ShopCart;

import java.util.List;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/24.
 */

public class CheShi extends Activity{
    private QQListView qqListView;
    private List<ShopCart> list;
    private DBHelper dbHelper;
    private SQLiteXingCheng sqLiteXingCheng;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheshi);

        qqListView = findViewById(R.id.id_listview);

        SharedPreferences sp = getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
        String DATABASE_BIAOMING = sp.getString("name","123");
        list = dbHelper.query(DATABASE_BIAOMING);
        sqLiteXingCheng = new SQLiteXingCheng(this,list);
        qqListView.setAdapter(sqLiteXingCheng);

        qqListView.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
            @Override
            public void clickHappend(int position) {

            }
        });
    }
}
