package com.dlxy.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by T on 2017/7/14.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String TAG ="DBManager";

    /**
     *
     * @param context 上下文
     * @DBcl.DATABASE_NAME  数据库表名 （定义在常量类里  ）DBcl
     * 第三个参数写null  游标，暂时用不到
     * @DBcl.DATABASE_VERSON 版本号
     *
     */
    public DBManager(Context context){
        super(context,DBcl.DATABASE_NAME,null,DBcl.DATABASE_VERSON);

    }

    /**
     *   数据库被创建的时候执行的方法，而且只执行一次
     *
     */
    @Override
    public void onCreate(SQLiteDatabase DB  ) {
        /**
         *
         *
         */
        String sul = "create table "+DBcl.DATABASE_BIAOMING+"(_id  integer primary key autoincrement,name varchar, text varchar,jiner integer)";
        DB.execSQL(sul);

    }

    /**
     *
     * @param DB
     * @param i
     * @param i1
     *
     *   数据库版本更新的时候执行的方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }
}
