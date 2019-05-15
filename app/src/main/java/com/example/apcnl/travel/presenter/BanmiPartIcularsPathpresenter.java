package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.BanmiParticularsPathBean;
import com.example.apcnl.travel.model.BanmiPartPathModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.BanmiPartIcularsPathView;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiPartIcularsPathpresenter extends basePresenter<BanmiPartIcularsPathView> {

    private BanmiPartPathModel mModel;

    @Override
    protected void initModel() {
        mModel = new BanmiPartPathModel();
        mModels.add(mModel);
    }

    public void getBanmiPartPathData(int id, int page, String token) {
        mModel.getBanmiPartPathData(id,page,token, new ResultCallBack<BanmiParticularsPathBean>() {
            @Override
            public void onSuccess(BanmiParticularsPathBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
