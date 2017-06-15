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
import com.guoxiaotian.lifesecretary.model.bean.XingShiQiYuanBean;
import com.guoxiaotian.lifesecretary.model.utils.NetUtils;

public class Xingshi extends Activity {

    private EditText et_date;
    private ImageButton ib_search;

    private NetUtils netUtils;

    private TextView tv_xingshi_name;
    private TextView tv_xingshi_orgin;


    private Handler handler=new Handler(){
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

        XingShiQiYuanBean xingShiQiYuanBean=prase(json);

        String Xing=xingShiQiYuanBean.getResult().getXing();

        String intro = xingShiQiYuanBean.getResult().getIntro();

        String intro_taked=intro.replaceAll("<BR>|&nbsp;|<P>|</P>","");

        tv_xingshi_name.setText(Xing);

        tv_xingshi_orgin.setText(intro_taked);
    }

    private XingShiQiYuanBean prase(String json) {

        XingShiQiYuanBean xingShiQiYuanBean2 = new Gson().fromJson(json, XingShiQiYuanBean.class);

        return xingShiQiYuanBean2;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_xingshi);

        initView();

        initData();
    }

    private void initData() {



    }


    private void initView() {

        ib_search = (ImageButton)findViewById(R.id.ib_search);

        et_date = (EditText)findViewById(R.id.et_date);

        tv_xingshi_orgin = (TextView)findViewById(R.id.tv_xingshi_orgin);

        tv_xingshi_name = (TextView)findViewById(R.id.tv_xingshi_name);

        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=et_date.getText().toString().trim();

                /*final String name2="郭";*/

                if (!TextUtils.isEmpty(name)){

                    netUtils=new NetUtils();
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {

                            String baiJiaXingJson = netUtils.getBaiJiaXing(name);
                            Log.i("TAG", "姓氏起源的联网请求数据：" + baiJiaXingJson);

                            Message message=Message.obtain();
                            message.what=1;
                            message.obj=baiJiaXingJson;

                            handler.sendMessage(message);
                        }
                    });
                }else{
                    Toast.makeText(Xingshi.this, "不能没有姓氏吧", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
