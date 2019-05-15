package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/6.
 */

public class EveryWhereModel extends baseModel {
    public void initData(String token, final ResultCallBack<EveryWhereBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getData(token)
                .compose(RxUtils.<EveryWhereBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<EveryWhereBean>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(EveryWhereBean everyWhereBean) {
                        if (everyWhereBean != null){
                            resultCallBack.onSuccess(everyWhereBean);
                        }
                    }
                });
    }
}
