package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/6.
 */

public class HomeModel extends baseModel {
    public void initData(String s, final ResultCallBack<HomeBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getHomeData(s)
                .compose(RxUtils.<HomeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HomeBean everyWhereBean) {
                        resultCallBack.onSuccess(everyWhereBean);
                    }
                });
    }

    public void setFollowData(int id, String param, final ResultCallBack<EverywhereFollowBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.setFollowData(id,param)
                .compose(RxUtils.<EverywhereFollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<EverywhereFollowBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(EverywhereFollowBean everyWhereBean) {
                        resultCallBack.onSuccess(everyWhereBean);
                    }
                });
    }

    public void setUnFollowData(int id, String param, final ResultCallBack<EverywhereFollowBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.setUnFollowData(id,param)
                .compose(RxUtils.<EverywhereFollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<EverywhereFollowBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(EverywhereFollowBean everyWhereBean) {
                        resultCallBack.onSuccess(everyWhereBean);
                    }
                });
    }
}
