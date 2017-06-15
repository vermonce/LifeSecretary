package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.Constants;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.bean.TodayHistoryItemBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class HistoryDetail extends Activity {

    private String _id;

    private TextView tv_title;
    private TextView tv_content;

    private ImageView iv_1;
    private ImageView iv_2;
    private ImageView iv_3;
    private ImageView iv_4;
    private ImageView iv_5;
    private ImageView iv_6;

    private TodayHistoryItemBean todayHistoryItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_history_detail);

        _id=getIntent().getStringExtra("id");

        initView();

        initData();
    }

    private void initData() {


        getDataFromNet();
    }

    private void getDataFromNet() {

        String url = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key="+ Constants.TODAY_IN_HISTORY_APPKEY+"&e_id="+_id;

        RequestParams entity=new RequestParams(url);
        x.http().get(entity,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //Log.i("TAG","历史今天item联网请求数据"+result);

                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {

        todayHistoryItemBean=prase(result);
        //Log.i("TAG","History解析成功："+todayHistoryItemBean.getResult().get(0).getTitle());

        tv_title.setText(todayHistoryItemBean.getResult().get(0).getTitle());
        tv_content.setText(todayHistoryItemBean.getResult().get(0).getContent());

        String PicNum=todayHistoryItemBean.getResult().get(0).getPicNo();
        //显示图片的方法
        showPic(PicNum);
    }

    private void showPic(String picNum) {

        int PicNum=Integer.parseInt(picNum);
        String picurl_1;
        String picurl_2;
        String picurl_3;
        String picurl_4;
        String picurl_5;
        String picurl_6;

        switch (PicNum) {
            case 0 :
                iv_1.setVisibility(View.GONE);
                iv_2.setVisibility(View.GONE);
                iv_3.setVisibility(View.GONE);
                iv_4.setVisibility(View.GONE);
                iv_5.setVisibility(View.GONE);
                iv_6.setVisibility(View.GONE);
                break;
            case 1 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                iv_2.setVisibility(View.GONE);
                iv_3.setVisibility(View.GONE);
                iv_4.setVisibility(View.GONE);
                iv_5.setVisibility(View.GONE);
                iv_6.setVisibility(View.GONE);
                break;
            case 2 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                picurl_2=todayHistoryItemBean.getResult().get(0).getPicUrl().get(1).getUrl();
                x.image().bind(iv_2,picurl_2);
                iv_3.setVisibility(View.GONE);
                iv_4.setVisibility(View.GONE);
                iv_5.setVisibility(View.GONE);
                iv_6.setVisibility(View.GONE);
                break;
            case 3 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                picurl_2=todayHistoryItemBean.getResult().get(0).getPicUrl().get(1).getUrl();
                x.image().bind(iv_2,picurl_2);
                picurl_3=todayHistoryItemBean.getResult().get(0).getPicUrl().get(2).getUrl();
                x.image().bind(iv_3,picurl_3);
                iv_4.setVisibility(View.GONE);
                iv_5.setVisibility(View.GONE);
                iv_6.setVisibility(View.GONE);
                break;
            case 4 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                picurl_2=todayHistoryItemBean.getResult().get(0).getPicUrl().get(1).getUrl();
                x.image().bind(iv_2,picurl_2);
                picurl_3=todayHistoryItemBean.getResult().get(0).getPicUrl().get(2).getUrl();
                x.image().bind(iv_3,picurl_3);
                picurl_4=todayHistoryItemBean.getResult().get(0).getPicUrl().get(3).getUrl();
                x.image().bind(iv_4,picurl_4);
                iv_5.setVisibility(View.GONE);
                iv_6.setVisibility(View.GONE);
                break;
            case 5 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                picurl_2=todayHistoryItemBean.getResult().get(0).getPicUrl().get(1).getUrl();
                x.image().bind(iv_2,picurl_2);
                picurl_3=todayHistoryItemBean.getResult().get(0).getPicUrl().get(2).getUrl();
                x.image().bind(iv_3,picurl_3);
                picurl_4=todayHistoryItemBean.getResult().get(0).getPicUrl().get(3).getUrl();
                x.image().bind(iv_4,picurl_4);
                picurl_5=todayHistoryItemBean.getResult().get(0).getPicUrl().get(4).getUrl();
                x.image().bind(iv_5,picurl_5);
                iv_6.setVisibility(View.GONE);
                break;
            case 6 :
                picurl_1=todayHistoryItemBean.getResult().get(0).getPicUrl().get(0).getUrl();
                x.image().bind(iv_1,picurl_1);
                picurl_2=todayHistoryItemBean.getResult().get(0).getPicUrl().get(1).getUrl();
                x.image().bind(iv_2,picurl_2);
                picurl_3=todayHistoryItemBean.getResult().get(0).getPicUrl().get(2).getUrl();
                x.image().bind(iv_3,picurl_3);
                picurl_4=todayHistoryItemBean.getResult().get(0).getPicUrl().get(3).getUrl();
                x.image().bind(iv_4,picurl_4);
                picurl_5=todayHistoryItemBean.getResult().get(0).getPicUrl().get(4).getUrl();
                x.image().bind(iv_5,picurl_5);
                picurl_6=todayHistoryItemBean.getResult().get(0).getPicUrl().get(5).getUrl();
                x.image().bind(iv_6,picurl_6);
                break;

        }

    }

    private TodayHistoryItemBean prase(String result) {

        TodayHistoryItemBean historyDetail2=new Gson().fromJson(result,TodayHistoryItemBean.class);
        return historyDetail2;
    }

    private void initView() {

        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_content = (TextView)findViewById(R.id.tv_content);
        iv_1 = (ImageView)findViewById(R.id.iv_1);
        iv_2 = (ImageView)findViewById(R.id.iv_2);
        iv_3 = (ImageView)findViewById(R.id.iv_3);
        iv_4 = (ImageView)findViewById(R.id.iv_4);
        iv_5 = (ImageView)findViewById(R.id.iv_5);
        iv_6 = (ImageView)findViewById(R.id.iv_6);
    }


}
