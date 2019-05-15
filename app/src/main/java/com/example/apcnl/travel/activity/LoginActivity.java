package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.fragment.LoginFragment;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Loginpresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.LoginView;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends baseactivity<LoginView, Loginpresenter> implements LoginView {

    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_BIND = 1;
    public static final String TAG = "LoginFragment";
    private int mType;


    public static void startAct(Context context , int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);

    }

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
        //getSpTokenSkipMain();
        getIntentData();
        FragmentManager manager = getSupportFragmentManager();
        LoginFragment fragment = new LoginFragment().newsIntance(mType);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container,fragment,TAG);
        transaction.commit();

    }

    private void getSpTokenSkipMain() {
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        if (!TextUtils.isEmpty(param)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
            return;
        }
    }

    private void getIntentData() {
        mType = getIntent().getIntExtra(Constants.TYPE,TYPE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
}
