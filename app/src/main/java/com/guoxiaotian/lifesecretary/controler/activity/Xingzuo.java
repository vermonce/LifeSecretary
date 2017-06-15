package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.Model;
import com.guoxiaotian.lifesecretary.model.bean.XingZuoYunShiBean;
import com.guoxiaotian.lifesecretary.model.utils.NetUtils;

import java.io.UnsupportedEncodingException;

/*
*
*       星座的Activity
* */

public class Xingzuo extends Activity {

    private TextView tv_name;
    private TextView tv_score;
    private TextView tv_color;
    private TextView tv_health;
    private TextView tv_love;
    private TextView tv_money;
    private TextView tv_friend;
    private TextView tv_comments;
    private TextView tv_date;
    private EditText et_xingzuo;
    private ImageButton ib_search;

    private NetUtils netUtils;

    private Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                String Json= (String) msg.obj;

                processData(Json);
            }

        }
    };

    private void processData(String json) {

        XingZuoYunShiBean xingZuoYunShiBean=prase(json);

        Log.i("TAG","解析后的数据："+xingZuoYunShiBean.getResult().getName());


        tv_name.setText(xingZuoYunShiBean.getResult().getName());
        tv_date.setText(xingZuoYunShiBean.getResult().getDatetime());
        tv_score.setText(xingZuoYunShiBean.getResult().getAll());
        tv_color.setText(xingZuoYunShiBean.getResult().getColor());
        tv_health.setText(xingZuoYunShiBean.getResult().getHealth());
        tv_love.setText(xingZuoYunShiBean.getResult().getLove());
        tv_money.setText(xingZuoYunShiBean.getResult().getMoney());
        tv_friend.setText(xingZuoYunShiBean.getResult().getQFriend());
        tv_comments.setText(xingZuoYunShiBean.getResult().getSummary());

    }

    private XingZuoYunShiBean prase(String json) {

        XingZuoYunShiBean xingZuoYunShiBean2 = new Gson().fromJson(json,XingZuoYunShiBean.class);
        return xingZuoYunShiBean2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_xingzuo);

        initView();

        initData();
    }

    private void initData() {

    }



    private void initView() {

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_score = (TextView)findViewById(R.id.tv_score);
        tv_color = (TextView)findViewById(R.id.tv_color);
        tv_health = (TextView)findViewById(R.id.tv_health);
        tv_love = (TextView)findViewById(R.id.tv_love);
        tv_money = (TextView)findViewById(R.id.tv_money);
        tv_friend = (TextView)findViewById(R.id.tv_friend);
        tv_comments = (TextView)findViewById(R.id.tv_comments);
        et_xingzuo = (EditText)findViewById(R.id.et_xingzuo);
        ib_search = (ImageButton)findViewById(R.id.ib_search);
        tv_date = (TextView)findViewById(R.id.tv_date);

        ib_search.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

           final String XingZuo1 = et_xingzuo.getText().toString().trim();
           /*final String XingZuo3 = "摩羯座";*/

            netUtils=new NetUtils();

            if (TextUtils.isEmpty(XingZuo1)){
                Toast.makeText(Xingzuo.this, "输入星座呀!TAT", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    final String XingZuo2 = java.net.URLEncoder.encode(XingZuo1, "utf-8");

                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {

                            String XingZuoJsonToday = netUtils.getXingZuoYunShi(XingZuo2, "today");

                            Message message = Message.obtain();
                            message.obj = XingZuoJsonToday;
                            message.what = 1;

                            handler.sendMessage(message);

                            Log.i("TAG", "星座运势+的联网请求数据：" + XingZuoJsonToday);
                            Log.i("TAG", "UTF8：" + XingZuo2);
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
