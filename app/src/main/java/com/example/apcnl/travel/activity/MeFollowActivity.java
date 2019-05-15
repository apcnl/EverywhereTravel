package com.example.apcnl.travel.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.MeFollowRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.MeFollowBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.MeFollowPresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.MeFollowView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeFollowActivity extends baseactivity<MeFollowView, MeFollowPresenter> implements MeFollowView {


    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private MeFollowRlvAdapter mAdapter;

    @Override
    protected MeFollowPresenter initPresenter() {
        return new MeFollowPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_me_follow;
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);

        ArrayList<MeFollowBean.ResultBean.BanmiBean> list = new ArrayList<>();
        mAdapter = new MeFollowRlvAdapter(this, list);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void iniData() {
        mpresenter.getFollowData((String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    public void setData(MeFollowBean bean) {
        mAdapter.addData(bean);
    }



    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
