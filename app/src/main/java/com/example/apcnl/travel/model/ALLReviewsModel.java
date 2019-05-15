package com.example.apcnl.travel.model;

import com.example.apcnl.travel.base.baseModel;
import com.example.apcnl.travel.bean.AllReviewsBean;
import com.example.apcnl.travel.bean.MeFollowBean;
import com.example.apcnl.travel.net.BaseObserver;
import com.example.apcnl.travel.net.EveryWhereApiService;
import com.example.apcnl.travel.net.HttpUtils;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ALLReviewsModel extends baseModel {
    public void getAllReviewsData(int id, String token, final ResultCallBack<AllReviewsBean> resultCallBack) {

        EveryWhereApiService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereApiService.mUrl, EveryWhereApiService.class);
        apiserver.getAllReviewsData(id,token)
                .compose(RxUtils.<AllReviewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllReviewsBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(AllReviewsBean meFollowBean) {
                        resultCallBack.onSuccess(meFollowBean);
                    }
                });
    }
}
