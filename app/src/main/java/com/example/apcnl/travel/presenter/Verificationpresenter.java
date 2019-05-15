package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.model.VerifyModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.view.VerificationView;

/**
 * Created by apcnl on 2019/5/3.
 */

public class Verificationpresenter extends basePresenter<VerificationView> {

    private VerifyModel mModel;

    @Override
    protected void initModel() {
        mModel = new VerifyModel();
        mModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                mMvpView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
