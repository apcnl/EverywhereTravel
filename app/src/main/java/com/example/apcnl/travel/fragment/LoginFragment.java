package com.example.apcnl.travel.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.LoginActivity;
import com.example.apcnl.travel.activity.MainActivity;
import com.example.apcnl.travel.activity.WebViewActivity;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.LoginFragmentpresenter;
import com.example.apcnl.travel.util.Tools;
import com.example.apcnl.travel.view.LoginFragmentView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by apcnl on 2019/5/4.
 */

public class LoginFragment extends BaseFragment<LoginFragmentView, LoginFragmentpresenter> implements LoginFragmentView {
    private static final int COUNT_DOWN_TIME = 60;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.umeng_qq)
    ImageView mUmengQq;
    @BindView(R.id.umeng_weibo)
    ImageView mUmengWeibo;
    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.tv_protocol)
    TextView mTvProtocol;
    @BindView(R.id.or_container)
    LinearLayout mOrContainer;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.wechat_login)
    TextView mWechatLogin;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private int mType;
    private VerifyFragment mVerifyFragment;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    private String mVerifyCode = "";

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
                getVerifyCode();
                initTime();
                //countDwon();
                break;
            case R.id.umeng_qq:

                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.umeng_weibo:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                break;
        }
    }

    private void initTime() {
        if (mTime>0 && mTime<COUNT_DOWN_TIME){
            return;
        }
        countDwon();
    }

    /**
     * 倒计时
     */
    public void countDwon() {
        if (mHandler == null){
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTime<=0){
                    mTime = COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (mVerifyFragment != null){
                    mVerifyFragment.setCountDownTime(mTime);
                }
                countDwon();
            }
        },1000);
    }

    private void getVerifyCode() {
        if (mTime>0 && mTime<COUNT_DOWN_TIME-1){
            return;
        }
        mPresenter.initData();
    }

    @Override
    protected void initView() {

        //ToastUtil.showShort(getAppMetaData(getContext(),"channel"));


        mBtnSend.setClickable(false);
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
                Tools.closeKeyBoard(getActivity());
            }
        });
        getArgumentsData();
        setProtocol();
        ShowOrHideView();
    }

    private void ShowOrHideView() {
        if (mType == LoginActivity.TYPE_LOGIN) {
            //登录
            mImgBack.setVisibility(View.INVISIBLE);
            mLlContainer.setVisibility(View.VISIBLE);
            mOrContainer.setVisibility(View.VISIBLE);
        } else {
            //手机号绑定
            mImgBack.setVisibility(View.VISIBLE);
            mLlContainer.setVisibility(View.GONE);
            mOrContainer.setVisibility(View.GONE);
        }
    }

    private void getArgumentsData() {
        mType = getArguments().getInt(Constants.TYPE);
    }

    private void setProtocol() {
        SpannableStringBuilder builder = new SpannableStringBuilder(getResources().getString(R.string.user_protocol));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                WebViewActivity.start(getActivity());
            }
        };

        builder.setSpan(clickableSpan, 12, 16, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        builder.setSpan(underlineSpan, 12, 16, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //颜色
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.c_FA6A13));
        builder.setSpan(span, 12, 16, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        mTvProtocol.setText(builder);
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())) {
            return;
        }

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        mVerifyFragment = VerifyFragment.newsInstance(mVerifyCode);
        transaction.add(R.id.fl_container, mVerifyFragment);
        transaction.commit();
        Tools.closeKeyBoard(getActivity());
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
            mBtnSend.setClickable(false);
        } else {
            mBtnSend.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
            mBtnSend.setClickable(true);
        }
    }

    @Override
    public String getPhone() {
        return mEdPhone.getText().toString().trim();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.start(getContext());
        getActivity().finish();
    }

    @Override
    public void onSuccess(VerifyCodeBean bean) {
        this.mVerifyCode = bean.getData();
        mVerifyFragment.onSuccess(bean);
    }

    @Override
    public void onFail(String msg) {

    }

    public LoginFragment newsIntance(int type) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

}
