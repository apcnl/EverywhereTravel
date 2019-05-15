package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.PathPartIcularsBean;
import com.example.apcnl.travel.model.PathModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.PathView;

/**
 * Created by apcnl on 2019/5/10.
 */

public class PathPresenter extends basePresenter<PathView> {

    private PathModel mModel;

    @Override
    protected void initModel() {
        mModel = new PathModel();
        mModels.add(mModel);
    }

    public void getPathData(int id, String token) {
        mModel.initData(id,token, new ResultCallBack<PathPartIcularsBean>() {
            @Override
            public void onSuccess(PathPartIcularsBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    public void setCollectData(int id, String token) {
        mModel.SetCollectData(id,token, new ResultCallBack<HomeCollectBean>() {
            @Override
            public void onSuccess(HomeCollectBean bean) {
                mMvpView.setCollectData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    public void setUnCollectData(int id, String token) {
        mModel.SetUnCollectData(id,token, new ResultCallBack<HomeCollectBean>() {
            @Override
            public void onSuccess(HomeCollectBean bean) {
                mMvpView.setUnCollectData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
