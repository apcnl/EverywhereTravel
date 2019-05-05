package com.example.apcnl.travel.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.presenter.LoginFragmentpresenter;
import com.example.apcnl.travel.view.LoginFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by apcnl on 2019/5/4.
 */

public class LoginFragment extends BaseFragment<LoginFragmentView, LoginFragmentpresenter> implements LoginFragmentView {
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.umeng_qq)
    ImageView mUmengQq;
    @BindView(R.id.umeng_weibo)
    ImageView mUmengWeibo;
    Unbinder unbinder;
    @BindView(R.id.container)
    RelativeLayout mContainer;

    @Override
    protected LoginFragmentpresenter initPresenter() {
        return new LoginFragmentpresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @OnClick({R.id.btn_send, R.id.umeng_qq, R.id.umeng_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                addVerifyFragment();
                break;
            case R.id.umeng_qq:
                break;
            case R.id.umeng_weibo:
                break;
        }
    }

    @Override
    protected void initView() {
        mEdPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdPhone.setCursorVisible(true);
            }
        });
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdPhone.setCursorVisible(false);
            }
        });
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())) {
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fl_container, new VerifyFragment());
        transaction.commit();
    }

    @Override
    protected void initListener() {
        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switvhBtnstate(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void switvhBtnstate(CharSequence s) {
        if (TextUtils.isEmpty(s)) {
            mBtnSend.setBackgroundResource(R.drawable.bg_btn_ea3_d15);
        } else {
            mBtnSend.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }
    }

    @Override
    public String getPhone() {
        return mEdPhone.getText().toString().trim();
    }

}