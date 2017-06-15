package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;

import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.pager.BasePager;
import com.guoxiaotian.lifesecretary.model.pager.FunTextPager;
import com.guoxiaotian.lifesecretary.model.pager.NewsPager;
import com.guoxiaotian.lifesecretary.model.pager.SettingPager;
import com.guoxiaotian.lifesecretary.model.pager.TodayInHistory;
import com.guoxiaotian.lifesecretary.view.NoScrollViewPager;

import java.util.ArrayList;


public class MyActivity extends Activity {

    private NoScrollViewPager viewpager;
    private RadioGroup main_rg;

    private ArrayList<BasePager> basePagers;//给viewPager准备数据集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_my);

        initView();

        initData();
        //初始化监听
        initListen();
    }


    private void initListen() {

        //一、RadioGroup设置改变的监听，绑定里面RadioButton和ViewPager的关系
        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_funtext :
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.main_rb_news :
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.main_rb_today:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.main_rb_setting :
                        viewpager.setCurrentItem(3);
                        break;
                }
            }
        });

        //二、ViewPager设置改变页面的监听
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                //2.1、滑动不同的页面，RadioButton按钮变色
                switch (i) {
                    case 0 :
                        main_rg.check(R.id.main_rb_funtext);
                        break;
                    case 1 :
                        main_rg.check(R.id.main_rb_news);
                        break;
                    case 2 :
                        main_rg.check(R.id.main_rb_today);
                        break;
                    case 3 :
                        main_rg.check(R.id.main_rb_setting);
                        break;
                }

                //2.2、滑到不同的页面，初始化不同页面的数据
                basePagers.get(i).initData();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initData() {

        basePagers=new ArrayList<BasePager>();
        //1、添加集合数据
        basePagers.add(new FunTextPager(MyActivity.this));
        basePagers.add(new NewsPager(MyActivity.this));
        basePagers.add(new TodayInHistory(MyActivity.this));
        basePagers.add(new SettingPager(MyActivity.this));
        //2、准备适配器
        viewpager.setAdapter(new MyViewPagerAdapter());

        //默认选择消息ViewPager
        main_rg.check(R.id.main_rb_funtext);

        //默认加载第一页数据
        basePagers.get(0).initData();

    }

    private void initView() {

        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        main_rg = (RadioGroup)findViewById(R.id.main_rg);

    }

    class MyViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //1、取出集合Pager里的页面
            View view=basePagers.get(position).rootView;
            //2、添加页面到容器里
            container.addView(view);
            //3、返回页面
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
