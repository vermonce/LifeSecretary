package com.guoxiaotian.lifesecretary.model;

import android.content.Context;

import com.guoxiaotian.lifesecretary.model.dao.FunTextDao;
import com.guoxiaotian.lifesecretary.model.dao.NewsDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/2/22.
 */
public class Model {

    private Context mContext;

    //创建一个线程池
    private ExecutorService executors= Executors.newCachedThreadPool();

    //数据库笑话的Dao类
    private FunTextDao funTextDao;

    //数据库新闻的Dao类
    private NewsDao newsDao;



    //初始化的方法
    public void init(Context context) {

        mContext = context;

        funTextDao=new FunTextDao(mContext);

        newsDao=new NewsDao(mContext);


    }


    /*
    *  Model对象要单例化：
    *       1、创建对象；
    *       2、私有化构造方法；
    *       3、获取单例对象；
    * */

    // 1、创建对象；
    private static Model model=new Model();

    //2、私有化构造方法；
    private Model(){

    }

    //3、获取单例对象；
    public static Model getInstance(){
        return model;
    }

    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){

        return executors;
    }

    public FunTextDao getFunTextDao(){

        return funTextDao;
    }

    public NewsDao getNewsDao(){
        return newsDao;
    }
}
