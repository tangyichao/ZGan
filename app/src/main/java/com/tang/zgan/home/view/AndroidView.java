package com.tang.zgan.home.view;

import com.tang.zgan.home.model.vo.AndroidArticle;

/**
 * Created by tangyc on 2017/2/15.
 */

public interface AndroidView {
    void showLoading();
    void closeLoading();
    void showError();
    void showAndroid(AndroidArticle androidArticle);
}
