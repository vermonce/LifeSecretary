package com.guoxiaotian.lifesecretary.model.dao;

/**
 * Created by Administrator on 2017/3/4.
 *
 *      笑话的数据库各子项的名称
 */
public class FunTextTable {

    public static final String TAB_NAME_FUNTEXT="Fun_Text_Tab";//数据库表名
    public static final String HASHID="hashId";//id列
    public static final String CONTENT="content";//内容列
    public static final String UNIXTIME="unixtime";//
    public static final String UPDATETIME="updatetime";//更新时间列
    public static final String ID="_id";

    //创建数据库表的String语句；注意空格
    // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
    public static final String CREATE_TAB=" create table "
            + TAB_NAME_FUNTEXT + " ("
            + ID + " integer primary key autoincrement,"
            + HASHID + " text,"
            + CONTENT + " text,"
            + UNIXTIME + " integer,"
            + UPDATETIME + " text);";
}
