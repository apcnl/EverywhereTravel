package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.MeInfoBean;
import com.example.apcnl.travel.model.MeInfoModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.view.MeInfoView;

/**
 * Created by apcnl on 2019/5/7.
 */

public class MeInfopresenter extends basePresenter<MeInfoView> {

    private MeInfoModel mModel;

    @Override
    protected void initModel() {
        mModel = new MeInfoModel();
        mModels.add(mModel);
    }

    public void getInfoData(String s) {
        mModel.initData(s, new ResultCallBack<MeInfoBean>() {
            @Override
            public void onSuccess(MeInfoBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
