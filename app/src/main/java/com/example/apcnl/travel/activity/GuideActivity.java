package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.EmptyView;
import com.example.apcnl.travel.widget.PreviewIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页面
 */
public class GuideActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {


    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.indicator)
    PreviewIndicator mIndicator;
    @BindView(R.id.btn_expe)
    Button mBtnExpe;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_guide;
    }

    @Override
    protected void iniView() {


        mIndicator.initSize(80, 32, 6);
        mIndicator.setNumbers(3);

        final ArrayList<View> list = new ArrayList<>();
        View inflateOne = LayoutInflater.from(this).inflate(R.layout.item_guide_one, null);
        View inflateTwo = LayoutInflater.from(this).inflate(R.layout.item_guide_two, null);
        View inflateThree = LayoutInflater.from(this).inflate(R.layout.item_guide_three, null);
        list.add(inflateOne);
        list.add(inflateTwo);
        list.add(inflateThree);

        mVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(list.get(position % list.size()));
                return list.get(position % list.size());
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(list.get(position % list.size()));
            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.setSelected(position);
                if (position == PreviewIndicator.INDICATOR_COUNT - 1) {
                    mIndicator.setVisibility(View.GONE);
                    mBtnExpe.setVisibility(View.VISIBLE);
                } else {
                    mIndicator.setVisibility(View.VISIBLE);
                    mBtnExpe.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @OnClick(R.id.btn_expe)
    public void onClick() {
        startActivity(new Intent(GuideActivity.this,LoginActivity.class));
        SpUtil.setParam(Constants.FIRST_ENTER,false);
        finish();
    }


}
