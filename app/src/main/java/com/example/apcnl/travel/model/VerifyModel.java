package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;
import com.example.apcnl.travel.net.VerifyService;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/6.
 */

public class VerifyModel extends baseModel {
    public void initData(final ResultCallBack<VerifyCodeBean> resultCallBack) {
        VerifyService apiserver = HttpUtils.getInstance().getApiserver(VerifyService.sBaseUrl, VerifyService.class);
        apiserver.getData()
                .compose(RxUtils.<VerifyCodeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VerifyCodeBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(VerifyCodeBean verifyCodeBean) {
                        //if (verifyCodeBean != null){
                            resultCallBack.onSuccess(verifyCodeBean);
                        //}
                    }
                });
    }
}
