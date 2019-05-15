package com.example.apcnl.travel.model;

import android.util.Log;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiParticularsModel extends baseModel {
    private static final String TAG = "BanmiParticularsModel";

    public void getBanmiParticularsData(int id, int page, String token, final ResultCallBack<BanmiParicularsBean> resultCallBack) {
        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getBanmiParticularsData(id,page,token)
                .compose(RxUtils.<BanmiParicularsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanmiParicularsBean>() {
                    @Override
                    public void error(String msg) {
                        Log.d(TAG, "error: "+msg);
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BanmiParicularsBean banmiParicularsBean) {
                        resultCallBack.onSuccess(banmiParicularsBean);
                    }
                });
    }
}
