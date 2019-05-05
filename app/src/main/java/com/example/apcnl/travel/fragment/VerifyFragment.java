package com.example.apcnl.travel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.presenter.Verificationpresenter;
import com.example.apcnl.travel.view.VerificationView;
import com.example.apcnl.travel.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apcnl on 2019/5/4.
 */

public class VerifyFragment extends BaseFragment<VerificationView, Verificationpresenter> implements VerificationView {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.icv)
    IdentifyingCodeView mIcv;
    @BindView(R.id.tv_later)
    TextView mTvLater;
    Unbinder unbinder;

    @Override
    protected Verificationpresenter initPresenter() {
        return new Verificationpresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_verification;
    }

    @Override
    protected void initListener() {
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
