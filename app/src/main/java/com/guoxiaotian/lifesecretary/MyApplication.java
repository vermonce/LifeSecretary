package com.guoxiaotian.lifesecretary;

import android.app.Application;

import com.guoxiaotian.lifesecretary.model.Model;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/21.
 *
 *              全局资源
 */
public class MyApplication extends Application {

            /*
        * 所有组件被创建之前执行
        * */
    @Override
    public void onCreate() {
        super.onCreate();

        //xutils框架的初始化
        x.Ext.setDebug(true);
        x.Ext.init(this);

        //初始化Model类：即数据模型层全局类
        Model.getInstance().init(this);
    }
}
