package com.tang.zgan.home.model.impl;

import com.tang.zgan.base.HttpMethods;
import com.tang.zgan.home.AndroidService;
import com.tang.zgan.home.MeiziService;
import com.tang.zgan.home.model.AndroidModel;
import com.tang.zgan.home.model.MeiziModel;
import com.tang.zgan.home.model.vo.AndroidArticle;
import com.tang.zgan.home.model.vo.Meizi;
import com.tang.zgan.home.presenter.OnAndroidListener;
import com.tang.zgan.home.presenter.OnMeiziListener;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tangyc on 2017/2/15.
 */

public class AndroidModelImpl implements AndroidModel {

    @Override
    public void loadingAndroid(int count, int number, final OnAndroidListener listener) {
        Retrofit retrofit= HttpMethods.getHttpMethods().getRetrofit();
        AndroidService service= retrofit.create(AndroidService.class);
        Observable<AndroidArticle> observable= service.getAndroid(String.valueOf(count),String.valueOf(number));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AndroidArticle>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(AndroidArticle androidArticle) {
                        if(androidArticle==null){
                            listener.onError();
                        }else{
                            listener.onSuccess(androidArticle);
                        }
                    }
                });

    }
}
