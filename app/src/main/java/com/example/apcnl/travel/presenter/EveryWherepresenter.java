package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.model.EveryWhereModel;
import com.example.apcnl.travel.net.EveryWhereService;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.view.EveryWhereView;
import com.example.apcnl.travel.view.InfoView;

/**
 * Created by apcnl on 2019/5/3.
 */

public class EveryWherepresenter extends basePresenter<EveryWhereView> {


    private EveryWhereModel mModel;

    @Override
    protected void initModel() {
        mModel = new EveryWhereModel();
        mModels.add(mModel);
    }

    public void initData(String token) {
        mModel.initData(token, new ResultCallBack<EveryWhereBean>() {
            @Override
            public void onSuccess(EveryWhereBean bean) {
                if (bean.getCode() == EveryWhereService.SUCCESS_CODE){
                    mMvpView.onSuccess(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
