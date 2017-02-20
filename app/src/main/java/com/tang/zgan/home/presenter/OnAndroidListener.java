package com.tang.zgan.home.presenter;

import com.tang.zgan.home.model.vo.AndroidArticle;

/**
 * Created by tangyc on 2017/2/15.
 */

public interface OnAndroidListener {
    void onSuccess(AndroidArticle Android);
    void onError();
}
