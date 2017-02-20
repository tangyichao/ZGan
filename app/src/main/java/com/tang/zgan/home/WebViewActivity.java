package com.tang.zgan.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tang.zgan.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tangyc on 2017/2/10.
 */

public class WebViewActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
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
        ArrayList<Integer> list = new ArrayList<Integer>();
        Method method = null;
        try {
            method = list.getClass().getMethod("add", Object.class);
            method.invoke(list, "Java反射机制实例。");
            Log.i("TAG","--"+list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0));
      //  HelloAnnotation helloAnnotation=  WebViewActivity.class.getAnnotation(HelloAnnotation.class);
       // Log.i("TAG",helloAnnotation.say());
       // Method[] m=MainActivity.class.getMethods();
//        try {
//            Method m1=MainActivity.class.getMethod("onCreate",new Class[]{Bundle.class});
//            Log.i("TAG",m1.getName());
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//
//        }
//        for(int i=0;i<m.length;i++){
//            Log.i("TAG",m[i].getName()+m[i].getModifiers());
//        }
//        ArrayList<String> list=new ArrayList<>();

    }
//    @Target(ElementType.TYPE)
//    @Retention(RetentionPolicy.RUNTIME)
//    @interface HelloAnnotation{
//        default String say() default "Hi";
//
//    }


}
