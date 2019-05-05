package com.example.apcnl.travel.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.fragment.LoginFragment;
import com.example.apcnl.travel.presenter.Loginpresenter;
import com.example.apcnl.travel.view.LoginView;

public class LoginActivity extends baseactivity<LoginView, Loginpresenter> implements LoginView {

    @Override
    protected Loginpresenter initPresenter() {
        return new Loginpresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    protected void iniView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container,new LoginFragment());
        transaction.commit();
    }
}
