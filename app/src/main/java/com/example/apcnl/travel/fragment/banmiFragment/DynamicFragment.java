package com.example.apcnl.travel.fragment.banmiFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.ViewPagerImgActivity;
import com.example.apcnl.travel.adapter.BanmiPartDongtaiRlvAdapter;
import com.example.apcnl.travel.adapter.BanmiPartDynamicImgRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.BanmiParticularspresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.BanmiParticularsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apcnl on 2019/5/14.
 */

public class DynamicFragment extends BaseFragment<BanmiParticularsView, BanmiParticularspresenter> implements BanmiParticularsView {

    private static final String TAG = "DynamicFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private BanmiPartDongtaiRlvAdapter mAdapter;
    public ArrayList<BanmiParicularsBean.ResultBean.ActivitiesBean> mList;

    @Override
    protected BanmiParticularspresenter initPresenter() {
        return new BanmiParticularspresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView() {

        mList = new ArrayList<>();

        mAdapter = new BanmiPartDongtaiRlvAdapter(getContext(), mList);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRlv.setAdapter(mAdapter);

        mRlv.setNestedScrollingEnabled(false);
    }

    @Override
    public void setData(BanmiParicularsBean bean) {
        Log.d(TAG, "setData: "+bean.toString());
        mAdapter.addData(bean);
    }

    @Override
    protected void initData() {
        mPresenter.getBanmiParticularsData((int) SpUtil.getParam(Constants.BANMI_ID, 0),
                1, (String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    protected void initListener() {
//        new BanmiPartDynamicImgRlvAdapter(getContext(),mList)
//                .setonItemImgClickListener(new BanmiPartDynamicImgRlvAdapter.onItemImgClickListener() {
//            @Override
//            public void clickListener(View v, int position) {
//                Intent intent = new Intent(getContext(), ViewPagerImgActivity.class);
//                intent.putExtra("position",position);
//                startActivity(intent);
//            }
//        });

    }
}
