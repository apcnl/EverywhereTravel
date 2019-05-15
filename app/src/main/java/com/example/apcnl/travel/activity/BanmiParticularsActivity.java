package com.example.apcnl.travel.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.fragment.banmiFragment.DynamicFragment;
import com.example.apcnl.travel.fragment.banmiFragment.ParticularsFragment;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.BanmiParticularspresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.BanmiParticularsView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BanmiParticularsActivity extends baseactivity<BanmiParticularsView, BanmiParticularspresenter> implements BanmiParticularsView {


    private static final String TAG = "BanmiParticularsActivity";
    @BindView(R.id.banmi_img)
    ImageView mBanmiImg;
    @BindView(R.id.banmi_name)
    TextView mBanmiName;
    @BindView(R.id.banmi_follow)
    TextView mBanmiFollow;
    @BindView(R.id.img_follow)
    ImageView mImgFollow;
    @BindView(R.id.banmi_city)
    TextView mBanmiCity;
    @BindView(R.id.banmi_work)
    TextView mBanmiWork;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.banmi_content)
    TextView mBanmiContent;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.nsv)
    NestedScrollView mNsv;
    private int mId;
    private ArrayList<BanmiParicularsBean.ResultBean.BanmiBean> mList;
    private FragmentManager mManager;
    private ArrayList<BaseFragment> mFragments;
    private int mLastFragmentPosition;

    @Override
    protected BanmiParticularspresenter initPresenter() {
        return new BanmiParticularspresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_banmi_particulars;
    }

    @Override
    protected void iniView() {


        StatusBarUtil.setLightMode(this);

        mFragments = new ArrayList<>();
        mFragments.add(new DynamicFragment());
        mFragments.add(new ParticularsFragment());

        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fl_container, mFragments.get(0));
        transaction.commit();


        showLoading();
        mLlContainer.setVisibility(View.GONE);

        mId = (int) SpUtil.getParam(Constants.BANMI_ID, 0);

        mTab.addTab(mTab.newTab().setText(R.string.dongtai));
        mTab.addTab(mTab.newTab().setText(R.string.particulars));


        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
                //mNsv.setFillViewport(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void switchFragment(int position) {
        FragmentTransaction transaction = mManager.beginTransaction();
        BaseFragment fragment = mFragments.get(position);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment);
        }
        transaction.hide(mFragments.get(mLastFragmentPosition));
        transaction.show(fragment);
        transaction.commit();
        mLastFragmentPosition = position;
    }

    @Override
    protected void iniData() {
        mpresenter.getBanmiParticularsData((int) SpUtil.getParam(Constants.BANMI_ID, 0),
                1, (String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    public void setData(BanmiParicularsBean bean) {
        //Log.d(TAG, "setData: "+bean.toString());
        if (bean.getCode() == 0) {
            hideLoading();
            mLlContainer.setVisibility(View.VISIBLE);
            BanmiParicularsBean.ResultBean.BanmiBean banmi = bean.getResult().getBanmi();
            Glide.with(this).load(banmi.getPhoto()).into(mBanmiImg);
            mBanmiCity.setText(banmi.getLocation());
            mBanmiWork.setText(banmi.getOccupation());
            mBanmiName.setText(banmi.getName());
            mBanmiFollow.setText(banmi.getFollowing() + "人关注");
            mBanmiContent.setText(banmi.getIntroduction());
            if (banmi.isIsFollowed()) {
                mImgFollow.setImageResource(R.mipmap.follow);
            } else {
                mImgFollow.setImageResource(R.mipmap.follow_unselected);
            }
        }
    }

    @Override
    protected void iniListener() {

    }

    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }



    //重写  onMeasure方法
}
