package com.guoxiaotian.lifesecretary.model.dao;

/**
 * Created by Administrator on 2017/3/9.
 */
public class NewsTable {

    public static final String TAB_NAME_NEWS_TAB_TOP="News_Tab_top";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_SHEHUI="News_Tab_shehui";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_GUONEI="News_Tab_guonei";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_GUOJI="News_Tab_guoji";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_YULE="News_Tab_yule";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_TIYU="News_Tab_tiyu";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_JUNSHI="News_Tab_junshi";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_KEJI="News_Tab_keji";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_CAIJING="News_Tab_caijing";//数据库表名
    public static final String TAB_NAME_NEWS_TAB_SHISHANG="News_Tab_SHISHANG";//数据库表名

    public static final String ID="_id";
    public static final String UNIQUEKEY="uniquekey";//
    public static final String TITLE="title";//
    public static final String DATE="date";//
    public static final String CATEGORY="category";//
    public static final String AUTHOR_NAME="author_name";//
    public static final String URL="url";
    public static final String THUMBNAIL_PIC_S="thumbnail_pic_s";
    public static final String THUMBNAIL_PIC_S02="thumbnail_pic_s02";
    public static final String THUMBNAIL_PIC_S03="thumbnail_pic_s03";


        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
        public static final String CREATE_TAB_TOP=" create table "
                + TAB_NAME_NEWS_TAB_TOP + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
        public static final String CREATE_TAB_SHEHUI=" create table "
                + TAB_NAME_NEWS_TAB_SHEHUI + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_GUONEI=" create table "
                + TAB_NAME_NEWS_TAB_GUONEI + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_GUOJI=" create table "
                + TAB_NAME_NEWS_TAB_GUOJI + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_YULE=" create table "
                + TAB_NAME_NEWS_TAB_YULE + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_TIYU=" create table "
                + TAB_NAME_NEWS_TAB_TIYU + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_JUNSHI=" create table "
                + TAB_NAME_NEWS_TAB_JUNSHI + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_KEJI=" create table "
                + TAB_NAME_NEWS_TAB_KEJI + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";


        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_CAIJING=" create table "
                + TAB_NAME_NEWS_TAB_CAIJING + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";

        //创建数据库表的String语句；注意空格
        // ---> create table tab_account ( hxid text primary key, name text, nich text, photo text);
         public static final String CREATE_TAB_SHISHANG=" create table "
                + TAB_NAME_NEWS_TAB_SHISHANG + " ("
                + ID + " integer primary key autoincrement,"
                + UNIQUEKEY + " text,"
                + TITLE + " text,"
                + DATE + " text,"
                + CATEGORY + " text,"
                + URL + " text,"
                + AUTHOR_NAME + " text,"
                + THUMBNAIL_PIC_S + " text,"
                + THUMBNAIL_PIC_S02 + " text,"
                + THUMBNAIL_PIC_S03 + " text);";


    }




