package com.guoxiaotian.lifesecretary.model.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.guoxiaotian.lifesecretary.model.bean.NewsDataBean;
import com.guoxiaotian.lifesecretary.model.db.MyNewsSqliteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 *
 *      新闻的数据库操作类
 */
public class NewsDao {

    private final MyNewsSqliteOpenHelper myNewsSqliteOpenHelper;


    public NewsDao(Context context) {

        myNewsSqliteOpenHelper=new MyNewsSqliteOpenHelper(context);

    }

    //添加最新笑话到数据库
    public void addNews(NewsDataBean.ResultNews.NewsDataEntity dataEntity,int tab_Type  ){
        //1、得到连接
        SQLiteDatabase db = myNewsSqliteOpenHelper.getReadableDatabase();

        //2、执行添加操作
        ContentValues values=new ContentValues();

        values.put(NewsTable.UNIQUEKEY,dataEntity.getUniquekey());
        values.put(NewsTable.TITLE,dataEntity.getTitle());
        values.put(NewsTable.DATE,dataEntity.getDate());
        values.put(NewsTable.CATEGORY,dataEntity.getCategory());
        values.put(NewsTable.AUTHOR_NAME,dataEntity.getAuthor_name());
        values.put(NewsTable.URL,dataEntity.getUrl());
        values.put(NewsTable.THUMBNAIL_PIC_S,dataEntity.getThumbnail_pic_s());
        values.put(NewsTable.THUMBNAIL_PIC_S02,dataEntity.getThumbnail_pic_s02());
        values.put(NewsTable.THUMBNAIL_PIC_S03,dataEntity.getThumbnail_pic_s03());

        //添加到哪一张表里
        switch (tab_Type) {
            case 1 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_TOP,null,values);
                break;
            case 2 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_SHEHUI,null,values);
                break;
            case 3 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_GUONEI,null,values);
                break;
            case 4 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_GUOJI,null,values);
                break;
            case 5 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_YULE,null,values);
                break;
            case 6 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_TIYU,null,values);
                break;
            case 7 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_JUNSHI,null,values);
                break;
            case 8 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_KEJI,null,values);
                break;
            case 9 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_CAIJING,null,values);
                break;
            case 10 :
                db.insert(NewsTable.TAB_NAME_NEWS_TAB_SHISHANG,null,values);
                break;
        }

        db.close();
    }

    //2、查询所有的数据库的数据
    public List<NewsDataBean.ResultNews.NewsDataEntity> getAll2(int tab_Type){

        List<NewsDataBean.ResultNews.NewsDataEntity> list= new ArrayList<NewsDataBean.ResultNews.NewsDataEntity>();

        //1、得到连接
        SQLiteDatabase db = myNewsSqliteOpenHelper.getReadableDatabase();

        String sql_1="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_top";
        String sql_2="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_shehui";
        String sql_3="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_guonei";
        String sql_4="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_guoji";
        String sql_5="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_yule";
        String sql_6="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_tiyu";
        String sql_7="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_junshi";
        String sql_8="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_keji";
        String sql_9="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_caijing";
        String sql_10="select _id, uniquekey, title, date, category, author_name, url, thumbnail_pic_s, thumbnail_pic_s02, thumbnail_pic_s03 from News_Tab_SHISHANG";

        Cursor cursor=new Cursor() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getPosition() {
                return 0;
            }

            @Override
            public boolean move(int offset) {
                return false;
            }

            @Override
            public boolean moveToPosition(int position) {
                return false;
            }

            @Override
            public boolean moveToFirst() {
                return false;
            }

            @Override
            public boolean moveToLast() {
                return false;
            }

            @Override
            public boolean moveToNext() {
                return false;
            }

            @Override
            public boolean moveToPrevious() {
                return false;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean isBeforeFirst() {
                return false;
            }

            @Override
            public boolean isAfterLast() {
                return false;
            }

            @Override
            public int getColumnIndex(String columnName) {
                return 0;
            }

            @Override
            public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
                return 0;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return null;
            }

            @Override
            public String[] getColumnNames() {
                return new String[0];
            }

            @Override
            public int getColumnCount() {
                return 0;
            }

            @Override
            public byte[] getBlob(int columnIndex) {
                return new byte[0];
            }

            @Override
            public String getString(int columnIndex) {
                return null;
            }

            @Override
            public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {

            }

            @Override
            public short getShort(int columnIndex) {
                return 0;
            }

            @Override
            public int getInt(int columnIndex) {
                return 0;
            }

            @Override
            public long getLong(int columnIndex) {
                return 0;
            }

            @Override
            public float getFloat(int columnIndex) {
                return 0;
            }

            @Override
            public double getDouble(int columnIndex) {
                return 0;
            }

            @Override
            public int getType(int columnIndex) {
                return 0;
            }

            @Override
            public boolean isNull(int columnIndex) {
                return false;
            }

            @Override
            public void deactivate() {

            }

            @Override
            public boolean requery() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void registerContentObserver(ContentObserver observer) {

            }

            @Override
            public void unregisterContentObserver(ContentObserver observer) {

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void setNotificationUri(ContentResolver cr, Uri uri) {

            }

            @Override
            public Uri getNotificationUri() {
                return null;
            }

            @Override
            public boolean getWantsAllOnMoveCalls() {
                return false;
            }

            @Override
            public Bundle getExtras() {
                return null;
            }

            @Override
            public Bundle respond(Bundle extras) {
                return null;
            }
        };


        //从哪一张表里查
        switch (tab_Type) {
            case 1 :
                 cursor = db.rawQuery(sql_1, null);
                break;
            case 2 :
                 cursor = db.rawQuery(sql_2, null);
                break;
            case 3 :
                 cursor = db.rawQuery(sql_3, null);
                break;
            case 4 :
                 cursor = db.rawQuery(sql_4, null);
                break;
            case 5 :
                 cursor = db.rawQuery(sql_5, null);
                break;
            case 6 :
                 cursor = db.rawQuery(sql_6, null);
                break;
            case 7 :
                 cursor = db.rawQuery(sql_7, null);
                break;
            case 8 :
                 cursor = db.rawQuery(sql_8, null);
                break;
            case 9 :
                 cursor = db.rawQuery(sql_9, null);
                break;
            case 10 :
                 cursor = db.rawQuery(sql_10, null);
                break;
        }

        while (cursor.moveToNext()){

         /*   int id = cursor.getInt(0);
            String hashId=cursor.getString(1);
            String content = cursor.getString(2);
            Integer unixtime=cursor.getInt(3);
            String updatetime=cursor.getString(4);

            LaughTextBean.Result.DataEntity dataEntity=new LaughTextBean.Result.DataEntity(content,hashId,unixtime,updatetime);
            list.add(dataEntity);*/

            int id=cursor.getInt(0);
            String uniquekey=cursor.getString(1);
            String title=cursor.getString(2);
            String date=cursor.getString(3);
            String category=cursor.getString(4);
            String author_name=cursor.getString(5);
            String url=cursor.getString(6);
            String thumbnail_pic_s=cursor.getString(7);
            String thumbnail_pic_s02=cursor.getString(8);
            String thumbnail_pic_s03=cursor.getString(9);

            NewsDataBean.ResultNews.NewsDataEntity newsDataEntity =new NewsDataBean.ResultNews.NewsDataEntity(uniquekey,title,date,category,author_name,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03);

            list.add(newsDataEntity);
        }

        //关闭cursor
        cursor.close();

        //关闭连接
        db.close();
        return list;
    }
}
