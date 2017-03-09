package com.tang.zgan.home.model.impl;

import com.tang.zgan.base.HttpMethods;
import com.tang.zgan.home.MeiziService;
import com.tang.zgan.home.model.MeiziModel;
import com.tang.zgan.home.model.vo.Meizi;
import com.tang.zgan.home.presenter.OnMeiziListener;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tangyc on 2017/2/15.
 */

public class MeiziModelImpl implements MeiziModel {

    @Override
    public void loadingMeizi(int count, int number, final OnMeiziListener listener) {
        Retrofit retrofit= HttpMethods.getHttpMethods().getRetrofit();
        MeiziService service= retrofit.create(MeiziService.class);
        Observable<Meizi> observable= service.getMeizi(String.valueOf(count),String.valueOf(number));
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Meizi>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(Meizi meizi) {
                        if(meizi==null){
                            listener.onError();
                        }else{
                            listener.onSuccess(meizi);
                        }
                    }
                });

    }
}
