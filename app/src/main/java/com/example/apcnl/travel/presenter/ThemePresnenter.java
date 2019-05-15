package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.ThemeBean;
import com.example.apcnl.travel.model.ThemeModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.ThemeView;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ThemePresnenter extends basePresenter<ThemeView> {

    private ThemeModel mModel;

    @Override
    protected void initModel() {
        mModel = new ThemeModel();
        mModels.add(mModel);
    }

    public void getThemeData(String param) {
        mModel.getThemeData(param, new ResultCallBack<ThemeBean>() {
            @Override
            public void onSuccess(ThemeBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
