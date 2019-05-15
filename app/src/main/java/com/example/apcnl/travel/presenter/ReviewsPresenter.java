package com.example.apcnl.travel.presenter;

import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.AllReviewsBean;
import com.example.apcnl.travel.model.ALLReviewsModel;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.ReviewsView;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ReviewsPresenter extends basePresenter<ReviewsView> {

    private ALLReviewsModel mModel;

    @Override
    protected void initModel() {
        mModel = new ALLReviewsModel();
        mModels.add(mModel);
    }

    public void getAllReviewsData(int id, String token) {
        mModel.getAllReviewsData(id,token, new ResultCallBack<AllReviewsBean>() {
            @Override
            public void onSuccess(AllReviewsBean bean) {
                mMvpView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
}
