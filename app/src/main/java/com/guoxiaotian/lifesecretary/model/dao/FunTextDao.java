package com.guoxiaotian.lifesecretary.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guoxiaotian.lifesecretary.model.bean.LaughTextBean;
import com.guoxiaotian.lifesecretary.model.db.MyTextSqliteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 *
 *              笑话的数据操作类
 */
public class FunTextDao {

    private final MyTextSqliteOpenHelper myTextSqliteOpenHelper;


    public FunTextDao(Context context) {
        myTextSqliteOpenHelper =new MyTextSqliteOpenHelper(context);
    }

    //添加最新笑话到数据库
    public void addFunText(LaughTextBean.FuntextResult.DataEntity dataEntity  ){
        //1、得到连接
        SQLiteDatabase db = myTextSqliteOpenHelper.getReadableDatabase();

        //2、判断数据库里有没有类似hasid的dataEntity数据
       //List<LaughTextBean.Result.DataEntity> AllData = getAll2();

        //如果里面没有相同数据，再插入数据库
       // if (!AllData.contains(dataEntity)){

            //3、执行添加操作
            ContentValues values=new ContentValues();
            values.put(FunTextTable.HASHID, dataEntity.getHashId());
            values.put(FunTextTable.CONTENT,dataEntity.getContent());
            values.put(FunTextTable.UNIXTIME,dataEntity.getUnixtime());
            values.put(FunTextTable.UPDATETIME,dataEntity.getUpdatetime());

            long insertid = db.insert(FunTextTable.TAB_NAME_FUNTEXT, null, values);

            Log.i("TAG","插入的返回id：---"+insertid);

     //   }

    /*    for (LaughTextBean.Result.DataEntity dataEntity2:AllData){

            if (dataEntity2.getHashId()!=dataEntity.getHashId()){


                values.put(FunTextTable.HASHID,dataEntity.getHashId());
                values.put(FunTextTable.CONTENT,dataEntity.getContent());
                values.put(FunTextTable.UNIXTIME,dataEntity.getUnixtime());
                values.put(FunTextTable.UPDATETIME,dataEntity.getUpdatetime());

                db.insert(FunTextTable.TAB_NAME_FUNTEXT, null, values);
            }
        }*/




        db.close();
    }

/*    //1、查询所有的数据库的数据
    public List<FunTextInfo> getAll(){

        List<FunTextInfo> list= new ArrayList<FunTextInfo>();

        //1、得到连接
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        String sql="select _id, hashId, content, unixtime, updatetime from Fun_Text_Tab";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String hashId=cursor.getString(1);
            String content = cursor.getString(2);
            Integer unixtime=cursor.getInt(3);
            String updatetime=cursor.getString(4);

            FunTextInfo funTextInfo=new FunTextInfo(content,hashId,unixtime,updatetime);

            list.add(funTextInfo);

        }

        //关闭cursor
        cursor.close();

        //关闭连接
        db.close();
        return list;
    }*/

    //2、查询所有的数据库的数据
    public List<LaughTextBean.FuntextResult.DataEntity> getAll2(){

        List<LaughTextBean.FuntextResult.DataEntity> list= new ArrayList<LaughTextBean.FuntextResult.DataEntity>();

        //1、得到连接
        SQLiteDatabase db = myTextSqliteOpenHelper.getReadableDatabase();

        String sql="select _id, hashId, content, unixtime, updatetime from Fun_Text_Tab";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String hashId=cursor.getString(1);
            String content = cursor.getString(2);
            Integer unixtime=cursor.getInt(3);
            String updatetime=cursor.getString(4);

            //FunTextInfo funTextInfo=new FunTextInfo(content,hashId,unixtime,updatetime);

            LaughTextBean.FuntextResult.DataEntity dataEntity=new LaughTextBean.FuntextResult.DataEntity(content,hashId,unixtime,updatetime);
            list.add(dataEntity);

        }

        //关闭cursor
        cursor.close();

        //关闭连接
        db.close();
        return list;
    }
}
