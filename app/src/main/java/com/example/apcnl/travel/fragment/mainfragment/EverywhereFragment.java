package com.example.apcnl.travel.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.HomeBundleWebViewActivity;
import com.example.apcnl.travel.activity.PathParticularsActivity;
import com.example.apcnl.travel.adapter.MainEveryWhereRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.net.EveryWhereService;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.presenter.EveryWherepresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.EmptyView;
import com.example.apcnl.travel.view.EveryWhereView;
import com.just.agentweb.LogUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apcnl on 2019/5/5.
 */

public class EverywhereFragment extends BaseFragment<EveryWhereView, EveryWherepresenter> implements EveryWhereView {

    private static final String TAG = "EverywhereFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private String mToken = "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private ArrayList<EveryWhereBean.ResultBean.BannersBean> mBannersList;
    public ArrayList<EveryWhereBean.ResultBean.RoutesBean> mArticleList;
    private MainEveryWhereRlvAdapter mAdapter;

    @Override
    protected EveryWherepresenter initPresenter() {
        return new EveryWherepresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_everywhere;
    }

    @Override
    protected void initView() {
        showLoading();
        mBannersList = new ArrayList<>();
        mArticleList = new ArrayList<>();

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new MainEveryWhereRlvAdapter(getContext(), mBannersList, mArticleList);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        Log.d(TAG, "initData: "+param);
//        Log.d(TAG, "initData: "+param);SpUtil.getParam(Constants.TOKEN, mToken)+""
        mPresenter.initData(param);
    }

    @Override
    public void onSuccess(EveryWhereBean bean) {
        Log.d(TAG, "onSuccess: "+bean);
        if (bean.getCode() == EveryWhereService.SUCCESS_CODE){
            hideLoading();
            mAdapter.addData(bean);
        }

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    protected void initListener() {
        mAdapter.setonItemClickListener(new MainEveryWhereRlvAdapter.onItemClickListener() {
            @Override
            public void clickListener(int position) {
                EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(position);
                SpUtil.setParam(Constants.PATH_ID,bean.getId());
                startActivity(new Intent(getContext(),PathParticularsActivity.class));
            }
        });
        mAdapter.setonItemBundleClickListener(new MainEveryWhereRlvAdapter.onItemBundleClickListener() {
            @Override
            public void clickListener(int position) {
                EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(position);
                String url = bean.getContentURL();
                String title = bean.getTitle();
                Intent intent = new Intent(getContext(), HomeBundleWebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
