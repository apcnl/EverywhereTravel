package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.BanmiParticularsPathBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiPartPathModel extends baseModel {
    public void getBanmiPartPathData(int id, int page, String token, final ResultCallBack<BanmiParticularsPathBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getBanmiParticularsPathData(id,page,token)
                .compose(RxUtils.<BanmiParticularsPathBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanmiParticularsPathBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BanmiParticularsPathBean banmiParticularsPathBean) {
                        resultCallBack.onSuccess(banmiParticularsPathBean);
                    }
                });
    }
}
