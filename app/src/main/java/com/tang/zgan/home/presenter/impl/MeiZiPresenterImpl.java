package com.tang.zgan.home.presenter.impl;

import com.tang.zgan.home.model.impl.MeiziModelImpl;
import com.tang.zgan.home.model.vo.Meizi;
import com.tang.zgan.home.presenter.MeiziPresenter;
import com.tang.zgan.home.presenter.OnMeiziListener;
import com.tang.zgan.home.view.MeiziView;

/**
 * Created by tangyc on 2017/2/15.
 */

public class MeiZiPresenterImpl implements MeiziPresenter,OnMeiziListener {
    private  MeiziView meiziView;
    private MeiziModelImpl meiziModel;
    public MeiZiPresenterImpl(MeiziView meiziView)
    {
        this.meiziView=meiziView;
        meiziModel=new MeiziModelImpl();
    }
    @Override
    public void getMeizi(int count,int number) {
        meiziView.showLoading();
        meiziModel.loadingMeizi(count,number,this);
    }

    @Override
    public void onSuccess(Meizi meizi) {
        meiziView.closeLoading();
        meiziView.showMeizi(meizi);
    }

    @Override
    public void onError() {
        meiziView.closeLoading();
        meiziView.showError();
    }
}
