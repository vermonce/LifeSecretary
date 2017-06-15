package com.guoxiaotian.lifesecretary.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guoxiaotian.lifesecretary.model.dao.FunTextTable;

/**
 * Created by Administrator on 2017/3/4.
 */
public class MyTextSqliteOpenHelper extends SQLiteOpenHelper {


    public MyTextSqliteOpenHelper(Context context) {
        super(context, "funtext.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FunTextTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
