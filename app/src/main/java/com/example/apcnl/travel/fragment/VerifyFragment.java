package com.example.apcnl.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.LoginActivity;
import com.example.apcnl.travel.activity.MainActivity;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Verificationpresenter;
import com.example.apcnl.travel.util.UIUtils;
import com.example.apcnl.travel.view.VerificationView;
import com.example.apcnl.travel.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private int mTime;

    public static VerifyFragment newsInstance(String code){
        VerifyFragment fragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected Verificationpresenter initPresenter() {
        return new Verificationpresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_verification;

    }


    @Override
    protected void initView() {
        String string = getArguments().getString(Constants.VERIFY_CODE);
        if (!TextUtils.isEmpty(string)) {
            mTvLater.setText(UIUtils.getString(R.string.please_verify_code) + string);
        }




        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTime == 0){
                    mPresenter.initData();
                    LoginFragment fragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    fragment.countDwon();
                }
            }
        });
    }


    @Override
    protected void initListener() {
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        mIcv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });

    }


    private void autoLogin() {
        if (mIcv.getTextContent().length() >= 4) {
            //自动登录
            toastShort("登录成功");
            mIcv.setBackgroundEnter(false);
            mTvLater.setText(UIUtils.getString(R.string.place_later));
            //startActivity(new Intent(getContext(), MainActivity.class));
        }
    }

    @Override
    protected void initData() {
        //mPresenter.initData();
    }

    @Override
    public void onSuccess(VerifyCodeBean str) {
        String data = str.getData();
        if (!TextUtils.isEmpty(data) && mTvLater != null) {
            mTvLater.setText(UIUtils.getString(R.string.please_verify_code) + data);
        }
    }

    @Override
    public void onFail(final String str) {
        toastShort(str);
    }

    @OnClick(R.id.tv_time)
    public void onClick() {

        mPresenter.initData();
    }

    public void setCountDownTime(int time) {
        if (mTvTime != null){
            mTime = time;
            if (time != 0){
                String format = String.format(UIUtils.getString(R.string.send_again) + "(%ss)", time);
                mTvTime.setText(format);
                mTvTime.setTextColor(UIUtils.getColor(R.color.c_999999));
            }else {
                mTvTime.setText(UIUtils.getString(R.string.send_again));
                mTvTime.setTextColor(UIUtils.getColor(R.color.c_FA6A13));
            }
        }
    }
}
