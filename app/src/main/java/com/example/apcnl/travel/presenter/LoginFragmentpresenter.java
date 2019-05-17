package com.example.apcnl.travel.presenter;

import android.util.Log;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseApp;
import com.example.apcnl.travel.base.basePresenter;
import com.example.apcnl.travel.bean.LoginInfo;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.model.LoginFragmentModel;
import com.example.apcnl.travel.model.VerifyModel;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.net.ResultCallBack;
import com.example.apcnl.travel.util.Logger;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.LoginFragmentView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by apcnl on 2019/5/3.
 */

public class LoginFragmentpresenter extends basePresenter<LoginFragmentView> {

    private static final String TAG = "LoginFragmentpresenter";
    private LoginFragmentModel mModel;
    private VerifyModel mLoginModel;

    @Override
    protected void initModel() {
        mModel = new LoginFragmentModel();
        mModels.add(mModel);
        mLoginModel = new VerifyModel();
        mModels.add(mLoginModel);
    }

    public void oauthLogin(SHARE_MEDIA type) {
        //UMShareAPI.get(mMvpView.getAct()).deleteOauth(mMvpView.getAct(),SHARE_MEDIA.SINA,authListener);
        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.getAct());
        umShareAPI.getPlatformInfo(mMvpView.getAct(), type, authListener);
    }
    UMAuthListener authListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null){
                logMap(data);
                if (platform == SHARE_MEDIA.SINA){
                    loginSina(data.get("uid"));

                }
//                if (platform == SHARE_MEDIA.QQ){
//                    mMvpView.go2MainActivity();
//                }
            }
        }


        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Logger.logD("Tag",t.toString());
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_error));
        }


        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_cancel));
        }
    };

    private void loginSina(String s) {
        mModel.loginSina(s, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                saveUserInfo(bean.getResult());
                mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                Log.d(TAG, "onSuccess: "+bean.getResult().getToken());
                mMvpView.go2MainActivity();

            }

            @Override
            public void onFail(String msg) {
                mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
            }
        });
    }

    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN,result.getToken());
        SpUtil.setParam(Constants.DESC,result.getDescription());
        SpUtil.setParam(Constants.USERNAME,result.getUserName());
        SpUtil.setParam(Constants.GENDER,result.getGender());
        SpUtil.setParam(Constants.EMAIL,result.getEmail());
        SpUtil.setParam(Constants.PHOTO,result.getPhoto());
        SpUtil.setParam(Constants.PHONE,result.getPhone());
    }

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry:data.entrySet()){
            Log.d(TAG, "logMap: "+entry.getKey()+","+entry.getValue());
        }
    }

    public void initData() {
        mLoginModel.initData(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                    mMvpView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
