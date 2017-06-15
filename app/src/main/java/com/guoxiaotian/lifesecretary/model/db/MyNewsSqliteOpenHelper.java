package com.guoxiaotian.lifesecretary.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guoxiaotian.lifesecretary.model.dao.NewsTable;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MyNewsSqliteOpenHelper extends SQLiteOpenHelper {
    public MyNewsSqliteOpenHelper(Context context) {
        super(context, "News.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NewsTable.CREATE_TAB_TOP);
        db.execSQL(NewsTable.CREATE_TAB_SHEHUI);
        db.execSQL(NewsTable.CREATE_TAB_GUONEI);
        db.execSQL(NewsTable.CREATE_TAB_GUOJI);
        db.execSQL(NewsTable.CREATE_TAB_YULE);
        db.execSQL(NewsTable.CREATE_TAB_TIYU);
        db.execSQL(NewsTable.CREATE_TAB_JUNSHI);
        db.execSQL(NewsTable.CREATE_TAB_KEJI);
        db.execSQL(NewsTable.CREATE_TAB_CAIJING);
        db.execSQL(NewsTable.CREATE_TAB_SHISHANG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
