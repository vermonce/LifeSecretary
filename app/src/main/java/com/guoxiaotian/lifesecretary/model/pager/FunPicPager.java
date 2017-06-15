package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.Constants;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.bean.LaughPicBean;
import com.guoxiaotian.lifesecretary.model.utils.CacheUtils;
import com.guoxiaotian.lifesecretary.model.utils.XUtilsImageUtils;
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
 *              趣图页面
 *
 *              PullToRefresh 的应用步骤
 *              1、改变库里Listview，原本只有下拉刷新，改为下拉刷新，上拉加载更多
 *              2、定义：
 *                  2.1、private PullToRefreshListView mPullRefreshListView;//库里面的实现了上下刷新的Listview类

                    2.2、private ListView listView;//用来接收 PullToRefreshListView 里的listview视图

                    2.3、改变布局文件里的ListView
                3、连接关系： listView=mPullRefreshListView.getRefreshableView();
                4、回调接口：mPullRefreshListView.setOnRefreshListener
 *
 */
public class FunPicPager extends BasePager {

    //private ListView lv_photos;

    private PullToRefreshListView mPullRefreshListView;//库里面的实现了上下刷新的Listview类

    private ListView listView;//用来接收 PullToRefreshListView 里的listview视图

    private GridView gv_photos;
    private ImageButton ib_title;

    //private NetUtils netUtils;

    private Boolean isShowListView=true;//切换按钮，默认为Listview

    private List<LaughPicBean.ResultPic.PicDataEntity> data;

    private MyLaughPicAdapter myLaughPicAdapter;

    private LaughPicBean laughPicBean;

    Boolean isLoadMore=false;//用来判断在解析数据时（procressdata（）），是否是加载更多

/*    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                //1、解析之前缓存数据
                CacheUtils.putString(context,Constants.NEW_LAUGH_IMG_URL, (String) msg.obj);

                //2、解析数据
                processData((String) msg.obj);

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();
            }
        }
    };*/
    //解析数据
    private void processData(String result) {

        //  解析成bean类
         laughPicBean = prase(result);

        if (!isLoadMore){//下拉刷新后处理数据

            //Log.i("TAG","趣图内容:--->"+laughPicBean.getResult().getData().get(1).getContent());
            data=new ArrayList<LaughPicBean.ResultPic.PicDataEntity>();
            data=laughPicBean.getResult().getData();

            myLaughPicAdapter=new MyLaughPicAdapter();

            listView.setAdapter(myLaughPicAdapter);

        }else {//上拉加载更多，处理数据

            //处理好上拉之后，改变默认isLoadMore的类型
            isLoadMore=false;

            List<LaughPicBean.ResultPic.PicDataEntity> newData = laughPicBean.getResult().getData();
            //添加到原来的集合中，一定要是在原来的集合数据里添加，而不是覆盖，一定是addAll（）
            data.addAll(newData);
            //刷新适配器
            myLaughPicAdapter.notifyDataSetChanged();
        }

    }

    class MyLaughPicAdapter extends BaseAdapter{

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
            if(convertView==null){

                convertView=View.inflate(context,R.layout.laugh_pic_list_item,null);
                viewHolder=new ViewHolder();

                //viewHolder.wv_item_pic= (WebView) convertView.findViewById(R.id.wv_item_pic);
                viewHolder.iv_item_pic= (ImageView) convertView.findViewById(R.id.iv_item_pic);
                viewHolder.tv_item_content= (TextView) convertView.findViewById(R.id.tv_item_content);
                viewHolder.tv_item_pictime= (TextView) convertView.findViewById(R.id.tv_item_pictime);

                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_item_pictime.setText(data.get(position).getUpdatetime());
            viewHolder.tv_item_content.setText(data.get(position).getContent());

            //viewHolder.wv_item_pic.loadUrl(data.get(position).getUrl());

            String picurl=data.get(position).getUrl();

/*            //需要显示的是静图
            if (picurl.contains(".jpg")){

                x.image().bind(viewHolder.iv_item_pic,picurl);

            //需要显示的是动图
            }else if (picurl.contains(".gif")){

                XUtilsImageUtils.display(viewHolder.iv_item_pic, picurl);
            }*/

            XUtilsImageUtils.display(viewHolder.iv_item_pic, picurl);
            //x.image().bind(viewHolder.iv_item_pic,picurl);

            return convertView;
        }

        class ViewHolder{

            ImageView iv_item_pic;
            TextView tv_item_content;
            //WebView wv_item_pic;
            TextView tv_item_pictime;
        }
    }


    private LaughPicBean prase(String obj) {
        LaughPicBean laughPicBean1 = new Gson().fromJson(obj, LaughPicBean.class);
        return laughPicBean1;
    }


    public FunPicPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.funpicpager,null);

        mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_list);

        listView=mPullRefreshListView.getRefreshableView();

        gv_photos= (GridView) view.findViewById(R.id.gv_photos);
        ib_title= (ImageButton) view.findViewById(R.id.ib_title);

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
                //getMoreDataFromCache();
                //刷新后，界面恢复
                mPullRefreshListView.onRefreshComplete();
            }
        });
        return view;

    }

    //从缓存中取得数据，并且加载到后面，显示更多
        /*
    *
    *   从缓存里拿出不重复的数据，根据时间顺序，
    * */
    private void getMoreDataFromCache() {

        mPullRefreshListView.onRefreshComplete();

        String MoreCacheData=CacheUtils.getString(context,Constants.NEW_LAUGH_TEXT_URL);

        isLoadMore=true;
        //处理数据
        processData(MoreCacheData);

        Toast.makeText(context, "没有跟多数据咯", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void initData() {
        super.initData();

        if (isShowListView){
            //title bar上默认改为ListView
            ib_title.setImageResource(R.drawable.icon_pic_grid_type);
        }else {
            ib_title.setImageResource(R.drawable.icon_pic_list_type);
        }

        //listview---gridview切换按钮监听
        ib_title.setOnClickListener(new MyTitleBarOnClickListener());


        //先取缓存里的数据
        String CacheJson= CacheUtils.getString(context, Constants.NEW_LAUGH_IMG_URL);
        //缓存里有数据，先显示缓存里的数据
        if (!TextUtils.isEmpty(CacheJson)){
            processData(CacheJson);

        }
        //联网请求数据
        getDataFromNet();
    }

    class MyTitleBarOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //切换listView和GridView
            switchListAndGrid(v);
        }
    }

    //切换listView和GridView
    private void switchListAndGrid(View v) {

        if (isShowListView){  //正在显示的是ListView，点击后显示成Gridview

            //1、把状态改变
            isShowListView=false;
            //2、把ListView改为GridView
            listView.setVisibility(View.GONE);
            gv_photos.setVisibility(View.VISIBLE);
            myLaughPicAdapter=new MyLaughPicAdapter();
            gv_photos.setAdapter(myLaughPicAdapter);
            //3、把按钮改成ListView
            ib_title.setImageResource(R.drawable.icon_pic_list_type);

        }else{  // 正在显示的是GistView，点击后显示成ListView

            //1、把状态改变
            isShowListView=true;
            //2、把ListView改为GridView
            gv_photos.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            myLaughPicAdapter=new MyLaughPicAdapter();
            listView.setAdapter(myLaughPicAdapter);
            //3、把按钮改成ListView
            ib_title.setImageResource(R.drawable.icon_pic_grid_type);
        }

    }

    //联网请求数据
    private void getDataFromNet() {

        //http://japi.juhe.cn/joke/img/text.from?key=您申请的KEY&page=1&pagesize=10
        //String url=Constants.NEW_LAUGH_IMG_URL+"?key="+Constants.LAUGH_APPKEY+"&page=1&pagesize=20";

        //String url="http://japi.juhe.cn/joke/img/text.from"+"?key="+Constants.LAUGH_APPKEY+"&page=1&pagesize=15";
        RequestParams entity=new RequestParams(Constants.NEW_LAUGH_IMG_URL);
        entity.setConnectTimeout(4000);
        x.http().get(entity,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i("TAG", "xutils联网请求成功:" + result);
                //1、解析之前缓存数据
                CacheUtils.putString(context,Constants.NEW_LAUGH_IMG_URL, result);

                //2、解析数据
                processData(result);

                //3、请求的数据后隐藏加载的圆圈的旋转
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("TAG","联网失败"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
  /*      netUtils=new NetUtils();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {

                //联网请求得到趣图的Json
                String picDataJson = netUtils.getPicFromNet();

                Message message= Message.obtain();
                message.obj=picDataJson;
                message.what=1;

                handler.sendMessage(message);
            }
        });*/

    }
}
