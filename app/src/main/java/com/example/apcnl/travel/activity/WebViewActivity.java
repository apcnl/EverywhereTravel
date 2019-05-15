package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.view.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    private AgentWeb mAgentWeb;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_web_view;
    }

    public static void start(Context activity) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void iniView() {

        StatusBarUtil.setLightMode(this);

        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.back_white);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLlContainer, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    mTvTitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });


    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
