package com.example.apcnl.travel.view;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.VerifyCodeBean;

/**
 * Created by apcnl on 2019/5/3.
 */

public interface VerificationView extends baseView {

    void onSuccess(VerifyCodeBean str);
    void onFail(String str);
}
