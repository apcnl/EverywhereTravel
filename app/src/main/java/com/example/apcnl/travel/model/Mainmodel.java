package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by apcnl on 2019/5/3.
 */

public class Mainmodel extends baseModel {

    public void downloadAppData(final ResultCallBack<ResponseBody> resultCallBack){
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
