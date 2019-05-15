package com.example.apcnl.travel.model;

import android.util.Log;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.MeCollectBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeCollectModel extends baseModel {

    private static final String TAG = "MeCollectModel";

    public void initData(int page, String param, final ResultCallBack<MeCollectBean> resultCallBack) {

        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getCollectData(page, param)
        .compose(RxUtils.<MeCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MeCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MeCollectBean homeCollectBean) {
                        Log.d(TAG, "onNext: "+homeCollectBean.toString());
                        resultCallBack.onSuccess(homeCollectBean);
                    }
                });
    }
}
