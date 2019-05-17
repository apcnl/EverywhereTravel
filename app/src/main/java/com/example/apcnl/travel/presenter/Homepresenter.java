package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;
import com.example.apcnl.travel.model.HomeModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.HomeView;

/**
 * Created by apcnl on 2019/5/3.
 */

public class Homepresenter extends basePresenter<HomeView> {


    private HomeModel mModel;

    @Override
    protected void initModel() {
        mModel = new HomeModel();
        mModels.add(mModel);
    }

    public void initData(String s) {
        mModel.initData(s, new ResultCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean bean) {
            }

            @Override
            public void onFail(String msg) {
                mMvpView.toastShort(msg);
            }
        });
    }

    public void setFollowData(int id, String param) {
        mModel.setFollowData(id,param, new ResultCallBack<EverywhereFollowBean>() {
            @Override
            public void onSuccess(EverywhereFollowBean bean) {
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    public void setUnFollowData(int id, String param) {
        mModel.setUnFollowData(id,param, new ResultCallBack<EverywhereFollowBean>() {
            @Override
            public void onSuccess(EverywhereFollowBean bean) {
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
