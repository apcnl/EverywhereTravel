package com.example.apcnl.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.ThemeRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.ThemeBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.ThemePresnenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.ThemeView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThemeActivity extends baseactivity<ThemeView, ThemePresnenter> implements ThemeView {


    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ThemeRlvAdapter mAdapter;
    private ArrayList<ThemeBean.ResultBean.BundlesBean> mList;

    @Override
    protected ThemePresnenter initPresenter() {
        return new ThemePresnenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_theme;
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);

        mList = new ArrayList<>();

        mAdapter = new ThemeRlvAdapter(this, mList);

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void iniData() {
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        mpresenter.getThemeData(param);
    }

    @Override
    public void setData(ThemeBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    protected void iniListener() {
        mAdapter.setonItemClickListener(new ThemeRlvAdapter.onItemClickListener() {
            @Override
            public void clickListener(int position) {
                ThemeBean.ResultBean.BundlesBean bean = mList.get(position);
                Intent intent = new Intent(ThemeActivity.this, HomeBundleWebViewActivity.class);
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("url", bean.getContentURL());
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
