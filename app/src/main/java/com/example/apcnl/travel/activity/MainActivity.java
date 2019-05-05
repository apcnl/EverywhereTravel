package com.example.apcnl.travel.activity;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.presenter.Mainpresenter;
import com.example.apcnl.travel.view.MainView;

public class MainActivity extends baseactivity<MainView, Mainpresenter> implements MainView {

    @Override
    protected Mainpresenter initPresenter() {
        return new Mainpresenter();
    }


    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }
}
