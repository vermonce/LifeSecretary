package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
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
import com.guoxiaotian.lifesecretary.Constants;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.Model;
import com.guoxiaotian.lifesecretary.model.bean.LaughTextBean;
import com.guoxiaotian.lifesecretary.model.utils.CacheUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 *
 */
public class FunTextPager extends BasePager {

    //private NetUtils netUtils;
    private LaughTextBean laughTextBean;

    //private ListView pull_refresh_list;

    private PullToRefreshListView mPullRefreshListView;//库里面的实现了上下刷新的Listview类

    private ListView listView;//用来接收 PullToRefreshListView 里的listview视图

    //笑话集合数据
    private List<LaughTextBean.FuntextResult.DataEntity> data;

    Boolean isLoadMore=false;//用来判断在解析数据时（procressdata（）），是否是加载更多

    private MyFunTextAdapter myFunTextAdapter;

    private Boolean upPressed=false;

    private Boolean downPressed=false;

/*
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                //1、缓存数据
                CacheUtils.putString(context, Constants.NEW_LAUGH_TEXT_URL, (String) msg.obj);
                //2、联网请求后处理数据,因为后面需要ListView改变页面，所以在ui线程里
                processData((String) msg.obj);

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();
            }


        }
    };*/


    public FunTextPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.funtextpager,null);

        mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_list);

        listView=mPullRefreshListView.getRefreshableView();
        //pull_refresh_list= (ListView) view.findViewById(R.id.pull_refresh_list);

        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //再次联网请求
                getDataFromNet();

            }
            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                //从缓存中取得数据，加载更多是显示缓存里的数据
                /*
                * --------------->要实现已经加载过得，但是不在这个ListView页面里的在加载出来，即上面试新数据，下面是缓存里的数据
                *       出现两个BUg：1、不断出现重复已经缓存的数据显示在页面里
                *                   2、上拉加载更多的旋转，不会马上消失，
                *
                *
                * */

                //刷新后，界面恢复
               mPullRefreshListView.onRefreshComplete();

               Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show();
               //getMoreDataFromCache();



            }
        });

        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final ImageView imageView1= (ImageView) view.findViewById(R.id.iv_top);
                final ImageView imageView2= (ImageView) view.findViewById(R.id.iv_down);
                final ImageView imageView3= (ImageView) view.findViewById(R.id.iv_share);

                        imageView3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "转发成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                        imageView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                //upPressed=!upPressed;
                                if (!upPressed&&!downPressed){//两者都没按下
                                    imageView1.setImageResource(R.drawable.ic_action_up_blue);
                                    upPressed=!upPressed;
                                }else if(!upPressed&&downPressed){//前者没按，后者按了
                                    imageView1.setImageResource(R.drawable.ic_action_up_blue);
                                    imageView2.setImageResource(R.drawable.ic_action_down_black);
                                    upPressed=!upPressed;
                                    downPressed=!downPressed;
                                }else if(upPressed){//前者按了，后者一定没按
                                    imageView1.setImageResource(R.drawable.ic_action_up_black);
                                    upPressed=!upPressed;
                                }
                            }
                        });


                        imageView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //downPressed=!downPressed;
                                if (!downPressed&&!upPressed){//两者都没按下
                                    downPressed=!downPressed;
                                    imageView2.setImageResource(R.drawable.ic_action_down_blue);
                                }else if(!downPressed&&upPressed){//后者没按，前者按了
                                    imageView1.setImageResource(R.drawable.ic_action_up_black);
                                    imageView2.setImageResource(R.drawable.ic_action_down_blue);
                                    upPressed=!upPressed;
                                    downPressed=!downPressed;
                                }else if(downPressed){//后者按下了，前者一定没按下
                                    imageView2.setImageResource(R.drawable.ic_action_down_black);
                                    downPressed=!downPressed;
                                }
                            }
                        });
            }
        });

        return view;

    }

    //从缓存中取得数据，并且加载到后面，显示更多
        /*
    *
    *   从缓存里拿出不重复的数据，，
    * */
    private void getMoreDataFromCache() {

        mPullRefreshListView.onRefreshComplete();

        isLoadMore=true;

        //处理数据
        String MoreCacheData=CacheUtils.getString(context,Constants.NEW_LAUGH_TEXT_URL);

    /*    if(TextUtils.isEmpty(MoreCacheData)){

            Toast.makeText(context, "没有跟多数据咯1", Toast.LENGTH_SHORT).show();
        }*/

        mPullRefreshListView.onRefreshComplete();

        processData(MoreCacheData);

    }

    @Override
    public void initData() {
        super.initData();

        //联网请求数据之前，先把数据拿出来
        String CacheJson=CacheUtils.getString(context,Constants.NEW_LAUGH_TEXT_URL);

        //缓存有数据，显示缓存数据，缓存没有数据，显示新数据
        if (!TextUtils.isEmpty(CacheJson)){
            processData(CacheJson);
        }
            //联网请求数据
            getDataFromNet();

    }

    //请求的笑话大全的数据
    private void getDataFromNet() {

        /*netUtils=new NetUtils();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String LaughJson = netUtils.getNewLaughText();
                //Log.i("TAG",LaughJson);

                //发给主线程，用handler发消息给主线程，处理数据
                Message message=Message.obtain();
                message.obj=LaughJson;
                message.what=1;
                handler.sendMessage(message);
            }
        });

        }*/


        RequestParams entity = new RequestParams(Constants.NEW_LAUGH_TEXT_URL);
        entity.setConnectTimeout(4000);

        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Log.i("TAG", "最新笑话：-----" + result);

                //1、缓存数据
                CacheUtils.putString(context, Constants.NEW_LAUGH_TEXT_URL, result);


                //2、联网请求后处理数据,因为后面需要ListView改变页面，所以在ui线程里
                processData(result);

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("TAG", "联网请求出错" + ex.getMessage());

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onCancelled(CancelledException cex) {

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();

            }

            @Override
            public void onFinished() {

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }

    private void processData(String LaughJson) {


       // Log.i("TAG","FunTextPager的Json语句:"+LaughJson);
        //1、解析json数据
        laughTextBean=prase(LaughJson);

        if (!isLoadMore){//下拉刷新后处理数据
           // Log.i("TAG",laughTextBean.getResult().getData().get(0).getContent());

            //2、显示列表数据
            data=new ArrayList<LaughTextBean.FuntextResult.DataEntity>();
            data=laughTextBean.getResult().getData();

            //加入数据库里的数据不重复
            addToDb(data);

            myFunTextAdapter=new MyFunTextAdapter();
            listView.setAdapter(myFunTextAdapter);

        }else {//上拉加载更多，处理数据

            //处理好上拉之后，改变默认isLoadMore的类型
            isLoadMore=false;

            //要显示的加载更多的数据的空容器
            List<LaughTextBean.FuntextResult.DataEntity> LoadMoreCacheData=new ArrayList<LaughTextBean.FuntextResult.DataEntity>();

            //拿出数据库的所有数据
            List<LaughTextBean.FuntextResult.DataEntity> DbAllData = Model.getInstance().getFunTextDao().getAll2();
            /*
            *       给LoadMoreCacheData赋值
            * */

            //给dataEntities赋值，把缓存数据重复的去掉
            List<LaughTextBean.FuntextResult.DataEntity> dataEntities = writeDatatoLoadMoreCacheData(DbAllData, data, LoadMoreCacheData);



            //添加到原来的集合中，一定要是在原来的集合数据里添加，而不是覆盖，一定是addAll（）
            data.addAll(dataEntities);
            //刷新适配器
            //3、请求的数据后隐藏加载的圆圈的旋转
            mPullRefreshListView.onRefreshComplete();

            myFunTextAdapter.notifyDataSetChanged();

        }

    }

    //往数据库添加数据（加入数据库里的数据不会重复）
    public void addToDb(List<LaughTextBean.FuntextResult.DataEntity> data) {

        List<LaughTextBean.FuntextResult.DataEntity> AllData = Model.getInstance().getFunTextDao().getAll2();

        //拿出数据库的所有hasId和data里的hasId作比较
        List<String> AllDataHasId = new ArrayList<String>();//所有缓存数据的hasid集合
        List<String> dataHasId = new ArrayList<String>();//显示数据的hasid集合
        List<Integer> positions = new ArrayList<Integer>();//用来保存不重复数据的下标集合

            for (int i = 0; i < AllData.size(); i++) {
                //得到所有的HasId集合
                AllDataHasId.add(AllData.get(i).getHashId());
            }

            for (int k = 0; k < data.size(); k++) {
                //得到所有的HasId集合
                dataHasId.add(data.get(k).getHashId());
            }

        //把所有不重复的下标都存在positions里--,待会加入数据库
        for (int j = 0; j < data.size(); j++) {

            if (!AllDataHasId.contains(dataHasId.get(j))) {
                positions.add(j);
            }
        }

        //把不重复的hasid的数据加入数据库
        for (Integer m:positions){

            Model.getInstance().getFunTextDao().addFunText(data.get(m));
        }

    }
    //拿出数据库中和显示数据（data）不重复的allCacheData数据
    private List<LaughTextBean.FuntextResult.DataEntity> writeDatatoLoadMoreCacheData(List<LaughTextBean.FuntextResult.DataEntity> DbAllData, List<LaughTextBean.FuntextResult.DataEntity> data, List<LaughTextBean.FuntextResult.DataEntity> loadMoreCacheData) {

        List<String> DbAllDataHasId=new ArrayList<String>();//所有缓存数据的hasid集合
        List<String> dataHasId=new ArrayList<String>();//显示数据的hasid集合
        List<Integer> positions=new ArrayList<Integer>();//用来保存不重复数据的下标集合

            for (int i = 0; i < DbAllData.size(); i++) {
                //得到所有的HasId集合
                DbAllDataHasId.add(DbAllData.get(i).getHashId());
            }

            for (int k =0; k<data.size();k++){
                //得到所有的HasId集合
                dataHasId.add(data.get(k).getHashId());
            }

            //把所有不重复的下标都存在positions里-------    有问题 0返回
            for (int j = 0; j < DbAllData.size(); j++) {

                if (!dataHasId.contains(DbAllDataHasId.get(j))) {
                    positions.add(j);
                }
            }

            //  b把所有不重复的数据都添加到集合中
            for (Integer k : positions) {

                loadMoreCacheData.add(DbAllData.get(k));
            }

        if (loadMoreCacheData.isEmpty()){
            mPullRefreshListView.onRefreshComplete();
            Toast.makeText(context, "没有更多数据，休息会", Toast.LENGTH_SHORT).show();
        }

        return loadMoreCacheData;
    }


    class MyFunTextAdapter extends BaseAdapter{

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

            ViewHolder viewHolder=null;
            if (convertView==null){

                convertView=View.inflate(context,R.layout.fun_text_item,null);

                viewHolder=new ViewHolder();

                viewHolder.tv_item_funtext= (TextView) convertView.findViewById(R.id.tv_item_funtext);
                viewHolder.tv_item_funtime= (TextView) convertView.findViewById(R.id.tv_item_funtime);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_item_funtext.setText(data.get(position).getContent());

            viewHolder.tv_item_funtime.setText(data.get(position).getUpdatetime());

            return convertView;
        }

        class ViewHolder{
            TextView tv_item_funtext;
            TextView tv_item_funtime;
        }
    }

    //用Gson来解析json数据
    private LaughTextBean prase(String LaughJson) {
        LaughTextBean laughTextBean1 = new Gson().fromJson(LaughJson, LaughTextBean.class);
        return laughTextBean1;
    }
}

