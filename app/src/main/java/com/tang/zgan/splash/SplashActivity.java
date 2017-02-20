package com.tang.zgan.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.tang.zgan.R;
import com.tang.zgan.home.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by tangyc on 2017/2/8.
 */

public class SplashActivity extends AppCompatActivity{
  //  @BindView(R.id.ll_splash)
    LinearLayout mLlSplash;
  //  @BindView(R.id.animation_z)
    LottieAnimationView animZ;
   // @BindView(R.id.animation_g)
    LottieAnimationView animG;
   // @BindView(R.id.animation_a)
    LottieAnimationView animA;
    // @BindView(R.id.animation_n)
    LottieAnimationView animN;
    // @BindView(R.id.animation_k)
    LottieAnimationView animK;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        mLlSplash= (LinearLayout) findViewById(R.id.ll_splash);
        animZ= (LottieAnimationView) findViewById(R.id.animation_z);
        animG= (LottieAnimationView) findViewById(R.id.animation_g);
        animA= (LottieAnimationView) findViewById(R.id.animation_a);
        animN= (LottieAnimationView) findViewById(R.id.animation_n);
        animK= (LottieAnimationView) findViewById(R.id.animation_k);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLlSplash.animate().scaleX(1.33f).scaleY(1.33f).setDuration(2500).start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animZ.playAnimation();
            }
        },100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animG.playAnimation();
            }
        },600);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animA.playAnimation();
            }
        },1100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animN.playAnimation();
            }
        },1600);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animK.playAnimation();
            }
        },2100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setClass(SplashActivity.this,MainActivity.class);
               // startActivity(intent);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this).toBundle());

                finish();
            }
        },3500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
