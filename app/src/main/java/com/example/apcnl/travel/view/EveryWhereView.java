package com.example.apcnl.travel.view;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.bean.VersionNameBean;

import okhttp3.ResponseBody;

/**
 * Created by apcnl on 2019/5/3.
 */

public interface EveryWhereView extends baseView {
    void onSuccess(EveryWhereBean bean);

    void onFail(String msg);

    void onSuccessVersionName(VersionNameBean bean);

    void setData(ResponseBody bean);
}
