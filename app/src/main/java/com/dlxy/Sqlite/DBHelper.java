package com.dlxy.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dlxy.domain.ShopCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T on 2017/7/14.
 */

public class DBHelper {
    public static final String TAG = "DBHelper";
    private DBManager dbManager;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();

    }

    public void add(ShopCart shopcarts) {
        String sql = "insert into "+DBcl.DATABASE_BIAOMING+" values (null,?,?,?)";

        db.execSQL(sql,new Object[] { shopcarts.getName()});

    }

    public void delete(String name) {
        db.delete(""+DBcl.DATABASE_BIAOMING+"", "name=?", new String[] { name });

    }

    public void update(String name, ContentValues values) {
        db.update(""+DBcl.DATABASE_BIAOMING+"", values, "name=?", new String[] { name });
    }
    public boolean chaxunbiao(){
        boolean b = false;
        Cursor c=db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='shca'", null);
        if(c.moveToNext()){
            int count = c.getInt(0);
            if(count==0){
                b= true;
            }
        }

        c.close();
        db.close();
        Log.i(TAG,"..............."+b);
        return  b;
    }

    public List<ShopCart> query() {
        List<ShopCart> list = new ArrayList<ShopCart>();
        String sqls = "select * from "+DBcl.DATABASE_BIAOMING+"";
        Cursor c = db.rawQuery(sqls, null);
        while (c.moveToNext()) {
            ShopCart shopCart = new ShopCart();
            int id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));

            shopCart.setName(name);

            list.add(shopCart);
        }
        return list;
    }
}
