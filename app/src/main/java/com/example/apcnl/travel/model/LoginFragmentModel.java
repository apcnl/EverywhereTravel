package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.LoginInfo;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/3.
 */

public class LoginFragmentModel extends baseModel {

    public void loginSina(String s, final ResultCallBack<LoginInfo> resultCallBack) {
        EveryWhereService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereService.BASE_URL, EveryWhereService.class);
        apiserver.postWeiboLogin(s)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        resultCallBack.onSuccess(loginInfo);
                    }
                });

    }
}
