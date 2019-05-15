package com.example.apcnl.travel.view;

import android.app.Activity;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.VerifyCodeBean;

/**
 * Created by apcnl on 2019/5/3.
 */

public interface LoginFragmentView extends baseView {

    String getPhone();
    Activity getAct();

    void go2MainActivity();

    void onSuccess(VerifyCodeBean bean);

    void onFail(String msg);
}
