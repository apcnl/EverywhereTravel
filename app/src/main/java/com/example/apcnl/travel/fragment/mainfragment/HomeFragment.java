package com.example.apcnl.travel.fragment.mainfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.BanmiParticularsActivity;
import com.example.apcnl.travel.adapter.MainHomeRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Homepresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by apcnl on 2019/5/5.
 */

public class HomeFragment extends BaseFragment<HomeView, Homepresenter> implements HomeView {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private MainHomeRlvAdapter mAdapter;
    private ArrayList<HomeBean.ResultBean.BanmiBean> mList;
    @Override
    protected Homepresenter initPresenter() {
        return new Homepresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MainHomeRlvAdapter(getContext(), mList);
        mRlv.setAdapter(mAdapter);


    }

    @Override
    protected void initData() {
        mPresenter.initData((String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    public void setData(HomeBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    public void setFollowData(EverywhereFollowBean bean) {
        if (bean.getCode() == 0){
            ToastUtil.showShort(bean.getResult().getMessage());
        }
    }

    @Override
    public void setUnFollowData(EverywhereFollowBean bean) {
        if (bean.getCode() == 0){
            ToastUtil.showShort(bean.getResult().getMessage());
        }
    }

    @Override
    protected void initListener() {
        mAdapter.setFollowOnclickListener(new MainHomeRlvAdapter.FollowOnclickListener() {
            @Override
            public void clicklistener(int position) {
                HomeBean.ResultBean.BanmiBean bean = mList.get(position);
                String token = (String) SpUtil.getParam(Constants.TOKEN, "");
                mPresenter.setFollowData(bean.getId(),token);
                //ToastUtil.showShort("红心点击了");

            }

            @Override
            public void unclicklistener(int position) {

                HomeBean.ResultBean.BanmiBean bean = mList.get(position);
                String token = (String) SpUtil.getParam(Constants.TOKEN, "");
                mPresenter.setUnFollowData(bean.getId(),token);
            }
        });

        mAdapter.setOnItemclickListener(new MainHomeRlvAdapter.OnItemclickListener() {
            @Override
            public void clicklistener(int position) {
                HomeBean.ResultBean.BanmiBean bean = mList.get(position);
                SpUtil.setParam(Constants.BANMI_ID,bean.getId());
                startActivity(new Intent(getContext(), BanmiParticularsActivity.class));
            }
        });
    }
}
