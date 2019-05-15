package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.ThemeBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ThemeModel extends baseModel {
    public void getThemeData(String param, final ResultCallBack<ThemeBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getThemeData(param)
                .compose(RxUtils.<ThemeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ThemeBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ThemeBean themeBean) {
                        resultCallBack.onSuccess(themeBean);
                    }
                });
    }
}
