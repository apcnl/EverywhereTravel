package com.example.apcnl.travel.fragment.banmiFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.BanmiPartPathRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.BanmiParticularsPathBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.BanmiPartIcularsPathpresenter;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.BanmiPartIcularsPathView;
import com.example.apcnl.travel.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apcnl on 2019/5/14.
 */

public class ParticularsFragment extends BaseFragment<BanmiPartIcularsPathView, BanmiPartIcularsPathpresenter> 
        implements BanmiPartIcularsPathView {
    private static final String TAG = "ParticularsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private BanmiPartPathRlvAdapter mAdapter;

    @Override
    protected BanmiPartIcularsPathpresenter initPresenter() {
        return new BanmiPartIcularsPathpresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_particulars;

    }

    @Override
    protected void initView() {

        ArrayList<BanmiParticularsPathBean.ResultBean.RoutesBean> list = new ArrayList<>();

        mAdapter = new BanmiPartPathRlvAdapter(getContext(), list);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRlv.setAdapter(mAdapter);

        //mRlv.setNestedScrollingEnabled(false);


    }

    @Override
    protected void initData() {
        mPresenter.getBanmiPartPathData((int) SpUtil.getParam(Constants.BANMI_ID, 0),
                1, (String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    public void setData(BanmiParticularsPathBean bean) {
        Log.d(TAG, "setData: "+bean);
        mAdapter.addData(bean);
    }
}
