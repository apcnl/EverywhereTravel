package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.model.BanmiParticularsModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.BanmiParticularsView;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiParticularspresenter extends basePresenter<BanmiParticularsView> {

    private BanmiParticularsModel mModel;

    @Override
    public void initModel() {
        mModel = new BanmiParticularsModel();
        mModels.add(mModel);
    }

    public void getBanmiParticularsData(int id, int page, String token) {
        mModel.getBanmiParticularsData(id,page,token
        , new ResultCallBack<BanmiParicularsBean>() {
                    @Override
                    public void onSuccess(BanmiParicularsBean bean) {
                        mMvpView.setData(bean);
                    }

                    @Override
                    public void onFail(String msg) {
                        ToastUtil.showShort(msg);
                    }
                });
    }
}
