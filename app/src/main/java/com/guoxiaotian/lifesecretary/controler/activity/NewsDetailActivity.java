package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.guoxiaotian.lifesecretary.R;

/*
*           新闻的详情页面，用来显示html页面的
*
* */
public class NewsDetailActivity extends Activity implements View.OnClickListener {

    private String url;

    private ImageButton ib_back;
    private ImageButton ib_textsize;
    private ImageButton ib_share;
    private ImageButton ib_send;
    private ImageButton ib_like;
    private WebView webView;
    private ProgressBar pbLoading;
    private WebSettings webSettings;
    private EditText et_comment;

    private boolean isBlued=false;//用来判断喜欢按钮现在的图片是不是蓝色的


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_news_detail);

        initView();

        //得到数据
        initData();
    }

    private void initData() {

        url=getIntent().getStringExtra("URL");
        /*
        *   如果不设置webview，它不支持点击什么的事件，必须让webView支持javaScript
        * */
        webSettings = webView.getSettings();
        //设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        //设置WebView中文字大小
        //webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setTextZoom(100);

        webSettings.setBuiltInZoomControls(false);//禁止缩放


        //不让从当前网页跳转到系统的浏览器中
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                pbLoading.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(url);

    }

    //初始化页面
    private void initView() {

        ib_back = (ImageButton)findViewById(R.id.ib_back);
        ib_textsize = (ImageButton)findViewById(R.id.ib_textsize);
        ib_share = (ImageButton)findViewById(R.id.ib_share);
        ib_like = (ImageButton)findViewById(R.id.ib_like);
        ib_send = (ImageButton)findViewById(R.id.ib_send);
        webView = (WebView)findViewById(R.id.webview);
        pbLoading = (ProgressBar)findViewById(R.id.pbLoading);
        et_comment = (EditText)findViewById(R.id.et_comment);

        ib_back.setOnClickListener(this);
        ib_textsize.setOnClickListener(this);
        ib_like.setOnClickListener(this);
        ib_share.setOnClickListener(this);
        ib_send.setOnClickListener(this);
    }


    //点击事件的监听
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ib_back ://回退按钮
                finish();
                break;
            case R.id.ib_share ://分享按钮
                Toast.makeText(NewsDetailActivity.this, "该功能还未实现哟", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_like ://喜欢按钮
                switchStar();   //按下喜欢按钮，改变图片
                break;
            case R.id.ib_send:
                Toast.makeText(NewsDetailActivity.this, "已经成功发送", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_textsize:
                showChangeTextSizeDialog();//改变字体的对话框
                break;
        }
    }

    private int tempSize=2;//选中的字体，但是还没有点击确定的,默认选中“正常字体”
    private int realSize=tempSize;//点击确定的字体

    //改变字体的对话框
    private void showChangeTextSizeDialog() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("改变字体大小");

        String[] items={"超大字体","大字体","正常字体","小字体","超小字体"};
        builder.setSingleChoiceItems(items,realSize,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击选择要改变成哪一个字型大小，但是还没有点确定，就不会改，在下次进去修改页面的时候，会默认选择上次选择的那个位置
                tempSize=which;//选择在那个选项上，但是还没有提交
            }
        });

        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //把缓存值给真实值，这样下次进来就会默认选中
                realSize=tempSize;
                //改变字体大小
                changeTextSize(realSize);
            }
        });
        builder.show();
    }

    //改变字体大小
    private void changeTextSize(int realSize) {
        switch (realSize) {
            case 0 :
                webSettings.setTextZoom(200);
                break;
            case 1 :
                webSettings.setTextZoom(150);
                break;
            case 2 :
                webSettings.setTextZoom(100);
                break;
            case 3 :
                webSettings.setTextZoom(75);
                break;
            case 4 :
                webSettings.setTextZoom(50);
                break;
        }

    }

    //按下喜欢按钮，改变图片
    private void switchStar() {

        if (isBlued){
            isBlued=false;
            ib_like.setImageResource(R.drawable.ic_action_star_10);
        }else {
            isBlued=true;
            ib_like.setImageResource(R.drawable.ic_action_star_10_blue);
        }
    }
}
