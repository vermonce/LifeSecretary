package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/2/21.
 *
 *      ViewPager的基类
 *
 *      1、context ：用来传进来使用的Activity
 *      2、rootView：ViewPager的adapter里instantiateItem（）这个方法，需要返回页面，
 *      3、子类页面各不相同，所以用抽象方法initview（），子类自己具体实现页面布局，返回rootView
 *      4、initData（），在ViewPager的选中监听事件里，调用initData（），实现刷新页面数据
 *
 */
public abstract class BasePager {

        /*
    *  上下文 ————MainActivity，子类能用到
    * */
    public final Context context;

    /*  视图，代表各个不同的页面,4个不同的页面
 * */
    public View rootView;

    //初始化时，就加载视图
    public BasePager(Context context) {
        this.context = context;
        rootView=initView();
    }

    //抽象方法初始视图，由子类来实现
    public abstract View initView();

    //初始化数据，子类具体实现
    public void initData(){

    }
}
