package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.MeFollowBean;
import com.example.apcnl.travel.model.MeFollowModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.MeFollowView;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeFollowPresenter extends basePresenter<MeFollowView> {

    private MeFollowModel mModel;

    @Override
    protected void initModel() {

        mModel = new MeFollowModel();
        mModels.add(mModel);
    }

    public void getFollowData( String param) {
        mModel.getFollowData(param, new ResultCallBack<MeFollowBean>() {
            @Override
            public void onSuccess(MeFollowBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
