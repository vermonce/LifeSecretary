package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.view.View;

import com.guoxiaotian.lifesecretary.R;

/**
 * Created by Administrator on 2017/3/6.
 */
public class AssistantPager extends BasePager {
    public AssistantPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view=View.inflate(context, R.layout.assistantpager,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
