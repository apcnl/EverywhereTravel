package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.VersionNameBean;
import com.example.apcnl.travel.model.EveryWhereModel;
import com.example.apcnl.travel.model.Mainmodel;
import com.example.apcnl.travel.net.EveryWhereService;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.view.MainView;

import okhttp3.ResponseBody;

/**
 * Created by apcnl on 2019/5/3.
 */

public class Mainpresenter extends basePresenter<MainView> {

    private EveryWhereModel mModel;
    private Mainmodel mMainmodel;

    @Override
    protected void initModel() {
        mModel = new EveryWhereModel();
        mModels.add(mModel);
        mMainmodel = new Mainmodel();
        mModels.add(mMainmodel);
    }

    public void VersionNameData(String token) {
        mModel.VersionNameData(token, new ResultCallBack<VersionNameBean>() {
            @Override
            public void onSuccess(VersionNameBean bean) {
                if (bean.getCode() == EveryWhereService.SUCCESS_CODE){
                    mMvpView.onSuccessVersionName(bean);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public void downloadAppData() {
        mMainmodel.downloadAppData(new ResultCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
