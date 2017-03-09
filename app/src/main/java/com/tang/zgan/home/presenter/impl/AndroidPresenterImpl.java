package com.tang.zgan.home.presenter.impl;

import com.tang.zgan.home.model.impl.AndroidModelImpl;
import com.tang.zgan.home.model.vo.AndroidArticle;
import com.tang.zgan.home.presenter.AndroidPresenter;
import com.tang.zgan.home.presenter.OnAndroidListener;
import com.tang.zgan.home.view.AndroidView;

/**
 * Created by tangyc on 2017/2/15.
 */

public class AndroidPresenterImpl implements AndroidPresenter,OnAndroidListener {
    private AndroidView androidView;
    private AndroidModelImpl androidModel;
    public AndroidPresenterImpl(AndroidView androidView)
    {
        this.androidView=androidView;
        androidModel=new AndroidModelImpl();
    }
    @Override
    public void getAndroid(String course,int count,int number) {
        androidView.showLoading();
        androidModel.loadingAndroid(course,count,number,this);
    }

    @Override
    public void onSuccess(AndroidArticle android) {
        androidView.closeLoading();
        androidView.showAndroid(android);
    }

    @Override
    public void onError() {
        androidView.closeLoading();
        androidView.showError();
    }
}
