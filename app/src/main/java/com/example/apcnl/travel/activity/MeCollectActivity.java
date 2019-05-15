package com.example.apcnl.travel.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.MeCollectRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.MeCollectBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.MeCollectPresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.MeCollectView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeCollectActivity extends baseactivity<MeCollectView, MeCollectPresenter> implements MeCollectView {


    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private MeCollectRlvAdapter mAdapter;

    @Override
    public void setData(MeCollectBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    protected MeCollectPresenter initPresenter() {
        return new MeCollectPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_collect;
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);

        ArrayList<MeCollectBean.ResultBean.CollectedRoutesBean> list = new ArrayList<>();
        mAdapter = new MeCollectRlvAdapter(this, list);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void iniData() {
        mpresenter.getCollectData(1, (String) SpUtil.getParam(Constants.TOKEN, ""));
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
