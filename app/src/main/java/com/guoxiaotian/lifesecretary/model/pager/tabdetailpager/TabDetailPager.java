package com.guoxiaotian.lifesecretary.model.pager.tabdetailpager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.controler.activity.NewsDetailActivity;
import com.guoxiaotian.lifesecretary.model.Model;
import com.guoxiaotian.lifesecretary.model.bean.NewsDataBean;
import com.guoxiaotian.lifesecretary.model.pager.BasePager;
import com.guoxiaotian.lifesecretary.model.utils.CacheUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 *
 *      新闻各个标签页面：头条、娱乐。。。。。
 *      ---->由于页面的布局都是一样，所以只用一个TabDetailPager类，装几个不同的对象，因为构造方法传进来的Url不同，所以
 *      显示的页面数据也是不同的。
 *
 *      一、Pager页面的流程：
 *      1、继承基类BasePager，
 *      2、initView()--实现页面布局，和控件监听事件
 *      3、initData（）--缓存加载/联网请求:getDataFromNet()
 *      4、联网请求:getDataFromNet()，得到数据后--->processData（）解析数据
 *      5、processData（）解析数据---->数据存入数据库/(显示数据：设置适配器)
 *
 *      二、上拉和下拉的实现：
 *      1、用PUllToRefresh库
 *      2、下拉刷新：重新请求网络
 *      3、上拉加载更多：
 *          a、把ListView的adapter里的data存进Sqlite数据库里，并且存进去的数据要与数据库里的数据比较，不能存重复数据
 *          b、从数据库里取数据，也得判断：和当前显示的data是不同数据
 *          c、区分不同页面，创造不同的数据库，TabDetailPager的构造方法传一个int类型 Type，根据Type判断不同页面
 */
public class TabDetailPager extends BasePager {

    private String pathUrl;

    public String indicatorName;

    private PullToRefreshListView mPullRefreshListView;//库里面的实现了上下刷新的Listview类

    private ListView listView;//用来接收 PullToRefreshListView 里的listview视图

    private NewsDataBean newsDataBean;

    private ArrayList<NewsDataBean.ResultNews.NewsDataEntity> data;

    private MyListViewAdapter myListViewAdapter;

    private Boolean isLoadMore=false;//上拉加载更多

    int tab_Type=0; //数据库表名的类型：1：头条；2：社会；3：国内。。。。。。。。。。。依次往下

    List<String> uniqueKey=new ArrayList<String>();

    public TabDetailPager(Context context,String pathUrl,String indicatorName,int tab_Type) {
        super(context);
        this.pathUrl=pathUrl;
        this.indicatorName=indicatorName;
        this.tab_Type=tab_Type;
    }

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.tabdetailpager,null);
        //listView= (ListView) view.findViewById(R.id.tb_listview);
        mPullRefreshListView= (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
        listView=mPullRefreshListView.getRefreshableView();

        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                getDataFromNet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                mPullRefreshListView.onRefreshComplete();

                Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show();
                //获得上拉加载更多缓存里的数据
                //getMoreData();

            }
        });

        mPullRefreshListView.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            int realposition=position-1;//不然点击后会加载下一个

            //设置点击某一条后变灰色
                //1、得到缓存的所有点击过的新闻item的key
           String Array_uniquekey=CacheUtils.getString(context,"array_uniquekey");

            Log.i("TAG","Array_uniquekey：----"+Array_uniquekey);

            //如果当前点击的item的key不存在数组中
            if (!uniqueKey.contains(data.get(realposition).getUniquekey())){

                //2、先保存到缓存中
                CacheUtils.putString(context,"array_uniquekey",position+",");
                uniqueKey.add(data.get(realposition).getUniquekey());
                //3、刷新适配器
                myListViewAdapter.notifyDataSetChanged();
            }

            //把页面的url传递给新的页面
            Intent intent=new Intent(context,NewsDetailActivity.class);
            String url = data.get(realposition).getUrl();
            intent.putExtra("URL",url);
            context.startActivity(intent);
        }
    }

    //获得上拉加载更多的数据
    /*
    *
    *   从缓存里拿出不重复的数据，根据时间顺序，
    * */
    private void getMoreData() {

        mPullRefreshListView.onRefreshComplete();

        isLoadMore=true;

        String CacheJson2=CacheUtils.getString(context,pathUrl);

        processData(CacheJson2);
    }

    @Override
    public void initData() {
        super.initData();

        String CatheJson=CacheUtils.getString(context,pathUrl);
        if (!TextUtils.isEmpty(CatheJson)){
            processData(CatheJson);
        }
        //联网请求数据
        getDataFromNet();
    }

    private void getDataFromNet() {


        RequestParams entity=new RequestParams(pathUrl);
        x.http().get(entity,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //Log.i("TAG","TabDEtail请求数据成功:"+result);n
                //1、加入缓存
                CacheUtils.putString(context,pathUrl,result);
                //2、解析数据
                processData(result);
                //3、下拉菜单的隐藏
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i("TAG","TabDetail请求数据失败;"+ex.getMessage());

                //3、下拉菜单的隐藏
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onCancelled(CancelledException cex) {

                //3、下拉菜单的隐藏
                mPullRefreshListView.onRefreshComplete();

            }

            @Override
            public void onFinished() {

                //3、下拉菜单的隐藏
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }

    //解析联网请求的数据，并显示
    private void processData(String result) {

        newsDataBean = prase(result);

        if (!isLoadMore){//下拉刷新

            data=new ArrayList<NewsDataBean.ResultNews.NewsDataEntity>();

            data= (ArrayList<NewsDataBean.ResultNews.NewsDataEntity>) newsDataBean.getResult().getData();

            addToDb(data,pathUrl);

            myListViewAdapter=new MyListViewAdapter();

            listView.setAdapter(myListViewAdapter);
        }else {

            //处理好上拉之后，改变默认isLoadMore的类型
            isLoadMore=false;

            //空容器，准备添加的数据容器
            List<NewsDataBean.ResultNews.NewsDataEntity> moreData=new ArrayList<NewsDataBean.ResultNews.NewsDataEntity>();

            /*
            *   给moreData赋值
            * */
            //根据type，拿出对应表里的数据
             List<NewsDataBean.ResultNews.NewsDataEntity> dbAllData=Model.getInstance().getNewsDao().getAll2(tab_Type);

            //给dataEntities赋值，把缓存数据重复的去掉
            moreData=writeToMoreData(dbAllData,data);

            data.addAll(moreData);

            //3、下拉菜单的隐藏
            mPullRefreshListView.onRefreshComplete();

            myListViewAdapter.notifyDataSetChanged();
        }

    }

    private List<NewsDataBean.ResultNews.NewsDataEntity> writeToMoreData(List<NewsDataBean.ResultNews.NewsDataEntity> dbAllData, ArrayList<NewsDataBean.ResultNews.NewsDataEntity> data) {

        //拿出数据库的所有Uniquekey和data里的Uniquekey作比较
        List<String> AllDataUniquekey = new ArrayList<String>();//所有缓存数据的Uniquekey集合
        List<String> dataUniquekey = new ArrayList<String>();//显示数据的Uniquekey集合
        List<Integer> positions = new ArrayList<Integer>();//用来保存不重复数据的下标集合

        for (int i = 0; i < dbAllData.size(); i++) {
            //得到所有的HasId集合
            AllDataUniquekey.add(dbAllData.get(i).getUniquekey());
        }

        for (int k = 0; k < data.size(); k++) {
            //得到所有的HasId集合
            dataUniquekey.add(data.get(k).getUniquekey());
        }


        //把所有不重复的下标都存在positions里-------    有问题 0返回
        for (int j = 0; j < dbAllData.size(); j++) {

            if (!dataUniquekey.contains(AllDataUniquekey.get(j))) {
                positions.add(j);
            }
        }


        //  b把所有不重复的数据都添加到集合中
        List<NewsDataBean.ResultNews.NewsDataEntity> loadMoreCacheData=new ArrayList<NewsDataBean.ResultNews.NewsDataEntity>();
        for (Integer k : positions) {

            loadMoreCacheData.add(dbAllData.get(k));
        }

        if (loadMoreCacheData.isEmpty()){
            mPullRefreshListView.onRefreshComplete();
            Toast.makeText(context, "没有更多数据，休息会", Toast.LENGTH_SHORT).show();
        }

        return loadMoreCacheData;
    }


    private void addToDb(ArrayList<NewsDataBean.ResultNews.NewsDataEntity> data, String pathUrl) {

/*
        switch (tab_Type) {
            case 1 :

                break;
            case 2 :

                break;
            case 3 :

                break;
            case 4 :

                break;
            case 5 :

                break;
            case 6 :

                break;
            case 7 :

                break;
            case 8 :

                break;
            case 9 :

                break;
            case 10 :

                break;
        }*/

        List<NewsDataBean.ResultNews.NewsDataEntity> AllData = Model.getInstance().getNewsDao().getAll2(tab_Type);

        //拿出数据库的所有Uniquekey和data里的Uniquekey作比较
        List<String> AllDataUniquekey = new ArrayList<String>();//所有缓存数据的Uniquekey集合
        List<String> dataUniquekey = new ArrayList<String>();//显示数据的Uniquekey集合
        List<Integer> positions = new ArrayList<Integer>();//用来保存不重复数据的下标集合

        for (int i = 0; i < AllData.size(); i++) {
            //得到所有的HasId集合
            AllDataUniquekey.add(AllData.get(i).getUniquekey());
        }

        for (int k = 0; k < data.size(); k++) {
            //得到所有的HasId集合
            dataUniquekey.add(data.get(k).getUniquekey());
        }

        //把所有不重复的下标都存在positions里--,待会加入数据库
        for (int j = 0; j < data.size(); j++) {

            if (!AllDataUniquekey.contains(dataUniquekey.get(j))) {
                positions.add(j);
            }
        }

        //把不重复的hasid的数据加入数据库
        for (Integer m:positions){

            Model.getInstance().getNewsDao().addNews(data.get(m),tab_Type);
        }

    }


    //这个adapter里包含了不同的item布局，通过不同类型数据加载不同类型的布局
    class MyListViewAdapter extends BaseAdapter {

        final int TypeNum=3;
        final int Type_1=0;
        final int Type_2=1;
        final int Type_3=2;


        @Override
        public int getViewTypeCount() {
            return TypeNum;
        }

        @Override
        public int getItemViewType(int position) {

            String thumbnail_pic_s=data.get(position).getThumbnail_pic_s();
            String thumbnail_pic_s02=data.get(position).getThumbnail_pic_s02();
            String thumbnail_pic_s03=data.get(position).getThumbnail_pic_s03();

            if (thumbnail_pic_s03!=null){//有第三张图的url，那前面两张肯定也有，返回三张图的布局
                return Type_3;
            }else if (thumbnail_pic_s02!=null){//有第二张图的url，那前面一张肯定也有，返回两张图的布局
                return Type_2;
            }else {//返回只有一张图的布局
                return Type_1;
            }

        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder1 viewHolder1=null;
            ViewHolder2 viewHolder2=null;
            ViewHolder3 viewHolder3=null;

            //得到缓存里被点击过item的Key
            //String uniquekey_array= CacheUtils.getString(context,"array_uniquekey");

            //得到不同item的布局类型
            int Type=getItemViewType(position);

            //一、绑定视图
            if (convertView==null){

                switch (Type) {
                    case Type_1 :
                        convertView=View.inflate(context,R.layout.news_item_pic_num1,null);
                        viewHolder1=new ViewHolder1();
                        viewHolder1.ivnews_item_pic_1= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_1);
                        viewHolder1.tvnews_item_title= (TextView) convertView.findViewById(R.id.tvnews_item_title);
                        viewHolder1.tvnews_item_author= (TextView) convertView.findViewById(R.id.tvnews_item_author);
                        viewHolder1.tvnews_item_time= (TextView) convertView.findViewById(R.id.tvnews_item_time);
                        convertView.setTag(viewHolder1);
                        break;
                    case Type_2 :
                        convertView=View.inflate(context,R.layout.news_item_pic_num2,null);
                        viewHolder2=new ViewHolder2();
                        viewHolder2.ivnews_item_pic_1= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_1);
                        viewHolder2.ivnews_item_pic_2= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_2);
                        viewHolder2.tvnews_item_title= (TextView) convertView.findViewById(R.id.tvnews_item_title);
                        viewHolder2.tvnews_item_author= (TextView) convertView.findViewById(R.id.tvnews_item_author);
                        viewHolder2.tvnews_item_time= (TextView) convertView.findViewById(R.id.tvnews_item_time);
                        convertView.setTag(viewHolder2);
                        break;
                    case Type_3 :
                        convertView=View.inflate(context,R.layout.news_item_pic_num3,null);
                        viewHolder3=new ViewHolder3();
                        viewHolder3.ivnews_item_pic_1= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_1);
                        viewHolder3.ivnews_item_pic_2= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_2);
                        viewHolder3.ivnews_item_pic_3= (ImageView) convertView.findViewById(R.id.ivnews_item_pic_3);
                        viewHolder3.tvnews_item_title= (TextView) convertView.findViewById(R.id.tvnews_item_title);
                        viewHolder3.tvnews_item_author= (TextView) convertView.findViewById(R.id.tvnews_item_author);
                        viewHolder3.tvnews_item_time= (TextView) convertView.findViewById(R.id.tvnews_item_time);
                        convertView.setTag(viewHolder3);
                        break;
                }
            }else {
                switch (Type) {
                    case Type_1 :
                        viewHolder1= (ViewHolder1) convertView.getTag();
                        break;
                    case Type_2 :
                        viewHolder2= (ViewHolder2) convertView.getTag();
                        break;
                    case Type_3 :
                        viewHolder3= (ViewHolder3) convertView.getTag();
                        break;
                }
            }


            //二、设置数据到视图里
            switch (Type) {
                case Type_1 :

                    x.image().bind(viewHolder1.ivnews_item_pic_1,data.get(position).getThumbnail_pic_s());
                    viewHolder1.tvnews_item_title.setText(data.get(position).getTitle());
                    viewHolder1.tvnews_item_author.setText(data.get(position).getAuthor_name());
                    viewHolder1.tvnews_item_time.setText(data.get(position).getDate());

                    if (uniqueKey.contains(data.get(position).getUniquekey())){
                        //设置标题为灰色
                        viewHolder1.tvnews_item_title.setTextColor(Color.GRAY);
                    }else {
                        //设置标题为黑色
                        viewHolder1.tvnews_item_title.setTextColor(Color.BLACK);
                    }

                    break;
                case Type_2 :

                    x.image().bind(viewHolder2.ivnews_item_pic_1,data.get(position).getThumbnail_pic_s());
                    x.image().bind(viewHolder2.ivnews_item_pic_2,data.get(position).getThumbnail_pic_s02());
                    viewHolder2.tvnews_item_title.setText(data.get(position).getTitle());
                    viewHolder2.tvnews_item_author.setText(data.get(position).getAuthor_name());
                    viewHolder2.tvnews_item_time.setText(data.get(position).getDate());


                    if (uniqueKey.contains(data.get(position).getUniquekey())){
                        //设置标题为灰色
                        viewHolder2.tvnews_item_title.setTextColor(Color.GRAY);
                    }else {
                        //设置标题为黑色
                        viewHolder2.tvnews_item_title.setTextColor(Color.BLACK);
                    }


                    break;
                case Type_3 :

                    x.image().bind(viewHolder3.ivnews_item_pic_1,data.get(position).getThumbnail_pic_s());
                    x.image().bind(viewHolder3.ivnews_item_pic_2,data.get(position).getThumbnail_pic_s02());
                    x.image().bind(viewHolder3.ivnews_item_pic_3,data.get(position).getThumbnail_pic_s03());
                    viewHolder3.tvnews_item_title.setText(data.get(position).getTitle());
                    viewHolder3.tvnews_item_author.setText(data.get(position).getAuthor_name());
                    viewHolder3.tvnews_item_time.setText(data.get(position).getDate());

                    if (uniqueKey.contains(data.get(position).getUniquekey())){
                        //设置标题为灰色
                        viewHolder3.tvnews_item_title.setTextColor(Color.GRAY);
                    }else {
                        //设置标题为黑色
                        viewHolder3.tvnews_item_title.setTextColor(Color.BLACK);
                    }

                    break;
            }

            return convertView;
        }

        class ViewHolder1{
            TextView tvnews_item_title;
            TextView tvnews_item_author;
            TextView tvnews_item_time;
            ImageView ivnews_item_pic_1;
        }

        class ViewHolder2{
            TextView tvnews_item_title;
            TextView tvnews_item_author;
            TextView tvnews_item_time;
            ImageView ivnews_item_pic_1;
            ImageView ivnews_item_pic_2;
        }

        class ViewHolder3{
            TextView tvnews_item_title;
            TextView tvnews_item_author;
            TextView tvnews_item_time;
            ImageView ivnews_item_pic_1;
            ImageView ivnews_item_pic_2;
            ImageView ivnews_item_pic_3;
        }
    }

    private NewsDataBean prase(String result) {
        NewsDataBean newsDataBean1 = new Gson().fromJson(result, NewsDataBean.class);
        return newsDataBean1;
    }
}
