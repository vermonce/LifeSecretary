package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.guoxiaotian.lifesecretary.Constants;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.pager.tabdetailpager.TabDetailPager;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/21.
 *
 *  新闻页面
 *
 *      使用TabPageIndicator时，要在布局文件里，先把其Visible设置为Gone，
 *      在后来设置完适配器后在显示出来，不然会报错
 *
 */
public class NewsPager extends BasePager {


    private TabPageIndicator tpi_news_menu_detail;
    private ViewPager news_menu_pager;

    private ImageButton ib_tab_next;

    private ArrayList<TabDetailPager> tabDetailPagers;//新闻页面下各个标签的子页面对应于不同的indicator

    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context,R.layout.newspager,null);
        tpi_news_menu_detail= (TabPageIndicator) view.findViewById(R.id.tpi_news_menu_detail);
        news_menu_pager= (ViewPager) view.findViewById(R.id.news_menu_pager);
        ib_tab_next= (ImageButton) view.findViewById(R.id.ib_tab_next);

        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                news_menu_pager.setCurrentItem(news_menu_pager.getCurrentItem()+1);

            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        tpi_news_menu_detail.setVisibility(View.VISIBLE);

        tabDetailPagers=new ArrayList<TabDetailPager>();
        //准备集合数据
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_TOP_URL,"头条",1));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_SHEHUI_URL,"社会",2));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_GUONEI_URL,"国内",3));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_GUOJI_URL,"国际",4));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_YULE_URL,"娱乐",5));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_TIYU_URL,"体育",6));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_JUNSHI_URL,"军事",7));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_KEJI_URL,"科技",8));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_CAIJING_URL,"财经",9));
        tabDetailPagers.add(new TabDetailPager(context, Constants.NEW_SHISHANG_URL,"时尚",10));

        //设置适配器
        news_menu_pager.setAdapter(new MyTabDetailPagerAdapter());


        //关联ViewPagerIndicator和ViewPager
        tpi_news_menu_detail.setViewPager(news_menu_pager);
        //主页以后监听页面变化，TabPageIndicator监听页面的变化,而不用ViewPager来设置监听
        tpi_news_menu_detail.setOnPageChangeListener(new MyOnPageChangerListener());

        tabDetailPagers.get(0).initData();

    }

    class MyOnPageChangerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {

            //选择后跟新数据
            tabDetailPagers.get(i).initData();

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
    class MyTabDetailPagerAdapter extends PagerAdapter {

        //得到标签名字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabDetailPagers.get(position).indicatorName;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //1、得到视图
            View rootview=tabDetailPagers.get(position).rootView;
            //2、加到容器
            container.addView(rootview);
            //3、返回
            return rootview;
        }

        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }
    }
}
