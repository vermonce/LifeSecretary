package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoxiaotian.lifesecretary.R;

public class SplashActivity extends Activity {

    private LinearLayout ll_Spl_bg;
    private ImageView iv_Spl_logo;
    private TextView tv_Spl_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_splash);

        initView();

        initAnimation();
    }

    //设置动画
    private void initAnimation() {

        //添加渐变动画
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);//从0--看不见，到1--全现
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        //添加缩放动画
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        ll_Spl_bg.startAnimation(alphaAnimation);
        tv_Spl_name.startAnimation(scaleAnimation);

        //设置动画的监听，当动画结束，进入主页面
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent=new Intent(SplashActivity.this,MyActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView() {
        ll_Spl_bg = (LinearLayout)findViewById(R.id.ll_Spl_bg);
        iv_Spl_logo = (ImageView)findViewById(R.id.iv_Spl_logo);
        tv_Spl_name = (TextView)findViewById(R.id.tv_Spl_name);
    }


}
