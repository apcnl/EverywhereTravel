package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.bean.VersionNameBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

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

    public void VersionNameData(String token, final ResultCallBack<VersionNameBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getVersionNameData(token)
                .compose(RxUtils.<VersionNameBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VersionNameBean>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(VersionNameBean everyWhereBean) {
                        if (everyWhereBean != null){
                            resultCallBack.onSuccess(everyWhereBean);
                        }
                    }
                });
    }

    public void downloadAppData(final ResultCallBack<ResponseBody> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.sDownLoadAppUrl, EveryWhereApiService.class);
        apiserver.downloadData()
                .compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        resultCallBack.onSuccess(responseBody);
                    }
                });
    }
}
