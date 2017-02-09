package com.tang.zgan;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by tangyc on 2017/2/9.
 */

public class ZGankApplication extends Application {
    private ArrayList<Activity> list;
    private static ZGankApplication app;
    public static ZGankApplication getApplication(){
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        list=new ArrayList<>();

    }
    public void addActivity(Activity activity)
    {
        if(list==null)
        {
            list=new ArrayList<>();
        }
        list.add(activity);
    }
    public void removeActivity(Activity activity)
    {
        list.remove(activity);
    }
    public void exit()
    {
        for(Activity activity:list)
        {
            activity.finish();
        }
        System.exit(0);
    }
}
