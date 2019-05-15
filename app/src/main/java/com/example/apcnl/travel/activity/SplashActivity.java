package com.example.apcnl.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {


    @BindView(R.id.img)
    ImageView mImg;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_splash;
    }

    @Override
    protected void iniView() {

        AlphaAnimation animation = new AlphaAnimation(1, 1);
        animation.setDuration(2000);
        mImg.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean param = (boolean) SpUtil.getParam(Constants.FIRST_ENTER, true);
                if (!param){
                    String param2 = (String) SpUtil.getParam(Constants.TOKEN, "");
                    if (!TextUtils.isEmpty(param2)){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                        return;
                    }else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }
                    return;
                }else {
                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
