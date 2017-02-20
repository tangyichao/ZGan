package com.tang.zgan.home.presenter;

import com.tang.zgan.home.model.vo.Meizi;

/**
 * Created by tangyc on 2017/2/15.
 */

public interface OnMeiziListener {
    void onSuccess(Meizi meizi);
    void onError();
}
