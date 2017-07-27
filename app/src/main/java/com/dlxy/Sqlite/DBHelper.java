package com.dlxy.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dlxy.domain.ShopCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        kaySort(DATABASE_BIAOMING);
    }

    public void delete(String _id ,String DATABASE_BIAOMING ) {

        db.delete(""+DATABASE_BIAOMING+"", "_id=?", new String[] { _id} );
        kaySort(DATABASE_BIAOMING);
    }

    public void update(String _id, ContentValues values,String DATABASE_BIAOMING) {
        db.update(""+DATABASE_BIAOMING+"", values, "_id=?", new String[] { _id });
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
    public void kaySort (String DATABASE_BIAOMING){
        String sqls = "select * from "+DATABASE_BIAOMING+"";
        Cursor c = db.rawQuery(sqls, null);
        List<Map<Integer,Integer>> listid  = new ArrayList<Map<Integer, Integer>>();

        for (int i =0; c.moveToNext();i++){
            int id = c.getInt(c.getColumnIndex("_id"));
            Map<Integer, Integer> map = new HashMap<>();
            map.put(i,id);
            listid.add(map);
        }


        for (int j =1; j<listid.size()+1;j++){
            int i = listid.get(j-1).get(j-1);
            Log.i(TAG,"........._id:"+i);
            ContentValues values = new ContentValues();
            values.put("_id",j);
            update(i+"",values,DATABASE_BIAOMING);
        }

    }

}