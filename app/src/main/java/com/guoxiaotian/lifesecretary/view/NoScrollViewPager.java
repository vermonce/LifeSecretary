package com.guoxiaotian.lifesecretary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/3/2.
 *
 *          自定义没有ViewPager滑动的ViewPager
 */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //屏蔽左右滑动触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
       // return super.onTouchEvent(ev);

        return true;//对事件不做任何处理
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);

        return false;//解决事件冲突
    }
}
