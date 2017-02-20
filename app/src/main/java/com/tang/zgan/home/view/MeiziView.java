package com.tang.zgan.home.view;

import com.tang.zgan.home.model.vo.Meizi;

/**
 * Created by tangyc on 2017/2/15.
 */

public interface MeiziView {
    void showLoading();
    void closeLoading();
    void showError();
    void showMeizi(Meizi meizi);
}
