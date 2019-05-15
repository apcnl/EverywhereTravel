package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.MeFollowBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeFollowModel extends baseModel {
    public void getFollowData(String param, final ResultCallBack<MeFollowBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getFollowData(param)
                .compose(RxUtils.<MeFollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MeFollowBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MeFollowBean meFollowBean) {
                        resultCallBack.onSuccess(meFollowBean);
                    }
                });
    }
}
