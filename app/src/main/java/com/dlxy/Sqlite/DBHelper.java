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

    public void add(ShopCart shopcarts,String DATABASE_BIAOMING) {
        String sql = "insert into "+DATABASE_BIAOMING+" values (null,?,?,?,?,?)";

        db.execSQL(sql,new Object[] { shopcarts.getName(),shopcarts.getJieshao(),shopcarts.getSum(),shopcarts.getKaishi(),shopcarts.getZhongdian()});

    }

    public void delete(String name) {
        db.delete(""+DBcl.DATABASE_BIAOMING+"", "name=?", new String[] { name });

    }

    public void update(String name, ContentValues values) {
        db.update(""+DBcl.DATABASE_BIAOMING+"", values, "name=?", new String[] { name });
    }


    public List<ShopCart> query(String DATABASE_BIAOMING) {
        List<ShopCart> list = new ArrayList<ShopCart>();
        String sqls = "select * from "+DATABASE_BIAOMING+"";
        Cursor c = db.rawQuery(sqls, null);
        while (c.moveToNext()) {
            ShopCart shopCart = new ShopCart();
            int id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int jiage = c.getInt(c.getColumnIndex("jiner"));
            String jieshao  =c.getString(c.getColumnIndex("jieshao"));
            String kaishi = c.getString(c.getColumnIndex("kaishi"));
            String zhongdian = c.getString(c.getColumnIndex("zhongdian"));
            shopCart.setName(name);
            shopCart.setSum(jiage);
            shopCart.setJieshao(jieshao);
            shopCart.setKaishi(kaishi);
            shopCart.setZhongdian(zhongdian);
            list.add(shopCart);
        }
        return list;
    }

    public boolean chaxunbiao(String DATABASE_BIAOMING){
        boolean b = true;
        Cursor c=db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='"+ DATABASE_BIAOMING+"'", null);
        if(c.moveToNext()){
            int count = c.getInt(0);
            if(count==0){
                b= false;
            }
        }

        c.close();
        db.close();
        Log.i(TAG,"..............."+b);
        return  b;
    }

}