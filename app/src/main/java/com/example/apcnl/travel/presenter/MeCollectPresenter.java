package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.MeCollectBean;
import com.example.apcnl.travel.model.MeCollectModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.view.MeCollectView;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeCollectPresenter extends basePresenter<MeCollectView> {

    private MeCollectModel mModel;

    @Override
    protected void initModel() {
        mModel = new MeCollectModel();
        mModels.add(mModel);
    }

    public void getCollectData(int page, String param) {
        mModel.initData(page,param, new ResultCallBack<MeCollectBean>() {
            @Override
            public void onSuccess(MeCollectBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.toastShort(msg);
            }
        });
    }
}
