package com.example.apcnl.travel.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.widget.LoadingDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by apcnl on 2019/5/3.
 */

public abstract class baseactivity<V extends baseView,P extends basePresenter>
        extends AppCompatActivity implements baseView {

    protected P mpresenter;
    private LoadingDialog mLoadingDialog;
    protected File mSd;

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
        initSd();
        initPer();
        iniData();
        iniListener();
    }

    private void initSd() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
            openSd();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openSd();
        }
    }

    private void openSd() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            mSd = Environment.getExternalStorageDirectory();
        }
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

    @Override
    public void toastShort(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
