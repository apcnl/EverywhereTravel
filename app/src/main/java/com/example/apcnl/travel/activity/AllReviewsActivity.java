package com.example.apcnl.travel.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.AllReviewsRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.AllReviewsBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.ReviewsPresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.ReviewsView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllReviewsActivity extends baseactivity<ReviewsView, ReviewsPresenter> implements ReviewsView {


    private static final String TAG = "AllReviewsActivity";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private int mId;
    private AllReviewsRlvAdapter mAdapter;

    @Override
    protected ReviewsPresenter initPresenter() {
        return new ReviewsPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_all_reviews;
    }

    @Override
    protected void iniView() {

        StatusBarUtil.setLightMode(this);

        mId = (int) SpUtil.getParam(Constants.PATH_ID, 0);
        Log.d(TAG, "iniView: " + mId);

        ArrayList<AllReviewsBean.ResultBean.ReviewsBean> list = new ArrayList<>();

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AllReviewsRlvAdapter(this, list);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void iniData() {
        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        mpresenter.getAllReviewsData(mId, token);
    }

    @Override
    public void setData(AllReviewsBean bean) {
        Log.d(TAG, "setData: " + bean.toString());
        mAdapter.addData(bean);
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
