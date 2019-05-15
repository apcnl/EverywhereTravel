package com.example.apcnl.travel.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.MeInfoBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.MeInfopresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.util.UIUtils;
import com.example.apcnl.travel.view.MeInfoView;
import com.example.apcnl.travel.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeInfoActivity extends baseactivity<MeInfoView, MeInfopresenter> implements MeInfoView {


    private static final String TAG = "MeInfoActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img)
    CircleImageView mImg;
    @BindView(R.id.img_container)
    RelativeLayout mImgContainer;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.name_container)
    RelativeLayout mNameContainer;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.sex_container)
    RelativeLayout mSexContainer;
    @BindView(R.id.tv_qianming)
    TextView mTvQianming;
    @BindView(R.id.qianming_container)
    RelativeLayout mQianmingContainer;
    private int mType;
    private PopupWindow mPopupWindow;

    @Override
    protected MeInfopresenter initPresenter() {
        return new MeInfopresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_me_info;
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);
        showLoading();
    }

    @OnClick({R.id.img_container, R.id.name_container, R.id.sex_container, R.id.qianming_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_container:
                SkipIntentUpLoadImg();
                break;
            case R.id.name_container:
                SkipIntent(mType, UIUtils.getString(R.string.update_name),
                        mTvName.getText().toString());
                break;
            case R.id.sex_container:
                popSexWindow();
                break;
            case R.id.qianming_container:
                mType = 1;
                SkipIntent(mType, UIUtils.getString(R.string.update_qianming),
                        mTvQianming.getText().toString());
                break;
        }
    }

    //跳转页面上传头像
    private void SkipIntentUpLoadImg() {
        Intent intent = new Intent(MeInfoActivity.this, UpLoadImgActivity.class);
        intent.putExtra("iv", (String) SpUtil.getParam(Constants.ME_INFO_IMG, ""));
        startActivityForResult(intent,
                Constants.REQUEST_CODE);
    }


    private void popSexWindow() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow();
        }
        View inflate = LayoutInflater.from(MeInfoActivity.this).inflate(R.layout.pop_sex, null, false);
        mPopupWindow.setContentView(inflate);

        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.showAtLocation(mSexContainer, Gravity.BOTTOM, 100, 100);

        //点击取消
        inflate.findViewById(R.id.btn_dimiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        //点击男
        inflate.findViewById(R.id.btn_man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoSex(UIUtils.getString(R.string.man));
            }
        });
        inflate.findViewById(R.id.btn_wuman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoSex(UIUtils.getString(R.string.wuman));
            }
        });
        inflate.findViewById(R.id.btn_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoSex(UIUtils.getString(R.string.privacy));
            }
        });
    }

    private void setInfoSex(String sex) {
        mTvSex.setText(sex);
        ToastUtil.showShort(UIUtils.getString(R.string.update_ok));
        SpUtil.setParam(Constants.ME_INFO_SEX, sex);
        mPopupWindow.dismiss();
    }

    private void SkipIntent(int type, String tab_name, String name) {
        Intent intent = new Intent(MeInfoActivity.this, UpdateNameOrSigerActivity.class);
        intent.putExtra("TabName", tab_name);
        intent.putExtra("Name", name);
        intent.putExtra("type", type);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void iniData() {
        mpresenter.getInfoData((String) SpUtil.getParam(Constants.TOKEN, ""));
    }

    @Override
    public void setData(MeInfoBean bean) {
        if (bean.getCode() == 0) {
            hideLoading();
            MeInfoBean.ResultBean result = bean.getResult();
            String param = (String) SpUtil.getParam(Constants.ME_INFO_IMG, "");
            if (!TextUtils.isEmpty(param)) {
                Glide.with(this).load(param).into(mImg);
            } else {
                mImg.setImageResource(R.drawable.zhanweitu_touxiang);
            }
            Glide.with(this).load(param).into(mImg);
            Log.d(TAG, "setData: " + result.getPhoto());
            mTvName.setText((String) SpUtil.getParam(Constants.ME_INFO_NAME, result.getUserName()));
            mTvSex.setText((String) SpUtil.getParam(Constants.ME_INFO_SEX, result.getGender()));
            mTvQianming.setText((String) SpUtil.getParam(Constants.ME_INFO_QIANMING, UIUtils.getString(R.string.lazy)));
            Log.d(TAG, "setData: " + bean.toString());

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == Constants.RESULT_CODE) {
                int type = data.getIntExtra("type", 0);
                if (type == MainActivity.TYPE_NAME) {
                    String name = data.getStringExtra("name");
                    Log.d(TAG, "onActivityResult: " + name);
                    mTvName.setText(name);
                    ToastUtil.showShort(UIUtils.getString(R.string.update_ok));
                    SpUtil.setParam(Constants.ME_INFO_NAME, name);
                } else if (type == MainActivity.TYPE_SIGER) {
                    String name = data.getStringExtra("name");
                    Log.d(TAG, "onActivityResult: " + name);
                    mTvQianming.setText(name);
                    ToastUtil.showShort(UIUtils.getString(R.string.update_ok));
                    SpUtil.setParam(Constants.ME_INFO_QIANMING, name);
                    mType = 0;
                }
            }
            if (resultCode == Constants.IMG_RESULT_CODE) {
                String img = data.getStringExtra("img");
                Glide.with(this).load(img).into(mImg);
                ToastUtil.showShort(UIUtils.getString(R.string.update_ok));
            }
        }
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
