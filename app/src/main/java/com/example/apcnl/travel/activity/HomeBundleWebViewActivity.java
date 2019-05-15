package com.example.apcnl.travel.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.view.EmptyView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeBundleWebViewActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {


    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_home_bundle_web_view;
    }

    @Override
    protected void iniView() {

        StatusBarUtil.setLightMode(this);

        mTvTitle.setText(getIntent().getStringExtra("title"));

        WebSettings settings = mWebview.getSettings();

        settings.setJavaScriptEnabled(true);

        mWebview.loadUrl(getIntent().getStringExtra("url") + "?os=android");

        AndroidJs androidJs = new AndroidJs(this);

        mWebview.addJavascriptInterface(androidJs, "android");

    }


    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }
}
