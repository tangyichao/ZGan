package com.tang.zgan.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tang.zgan.R;

/**
 * Created by tangyc on 2017/2/10.
 */

public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView mWv= (WebView) findViewById(R.id.wv);
        String url=getIntent().getStringExtra("weburl");
        if(!TextUtils.isEmpty(url)&&mWv!=null){
            Log.i("TAG",url+"------");
            mWv.loadUrl(url);
        }
        mWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
