package com.example.apcnl.travel.base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by apcnl on 2019/5/3.
 */

public abstract class baseactivity<V extends baseView,P extends basePresenter> extends AppCompatActivity implements baseView {

    protected P mpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.bind(this);
        mpresenter = initPresenter();
        if (mpresenter!=null){
            mpresenter.bind(this);
        }
        iniView();
        initPer();
        iniData();
        iniListener();
    }

    private void initPer() {
        String[] per = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, per, 100);
    }

    protected abstract P initPresenter();

    protected void iniListener(){}

    protected  void iniView(){}

    protected void iniData() {}

    protected abstract int getLayoutid();
}
