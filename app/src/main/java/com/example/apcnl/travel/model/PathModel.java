package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.PathPartIcularsBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/10.
 */

public class PathModel extends baseModel {

    public void initData(int id, String token, final ResultCallBack<PathPartIcularsBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getPathData(id,token)
                .compose(RxUtils.<PathPartIcularsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PathPartIcularsBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(PathPartIcularsBean pathPartIcularsBean) {
                        resultCallBack.onSuccess(pathPartIcularsBean);
                    }
                });
    }

    public void SetCollectData(int id, String token, final ResultCallBack<HomeCollectBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.setCollectData(id,token)
                .compose(RxUtils.<HomeCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HomeCollectBean pathPartIcularsBean) {
                        resultCallBack.onSuccess(pathPartIcularsBean);
                    }
                });
    }

    public void SetUnCollectData(int id, String token, final ResultCallBack<HomeCollectBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.setUnCollectData(id,token)
                .compose(RxUtils.<HomeCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HomeCollectBean pathPartIcularsBean) {
                        resultCallBack.onSuccess(pathPartIcularsBean);
                    }
                });
    }
}
