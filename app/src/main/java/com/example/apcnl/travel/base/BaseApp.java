package com.example.apcnl.travel.base;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by apcnl on 2019/5/3.
 */

public class BaseApp extends Application{

    private static int mWidthPixels;
    private static int mHeightPixels;
    private static BaseApp sBaseApp;

    @Override
    public void onCreate() {
        super.onCreate();

        sBaseApp = this;

        getScreenWH();
        UMConfigure.init(this, "5c0a1ce9b465f541250001c5", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("468512016", "6180868aa92e0b1be95855a6b98d1ffd",
                "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    //计算屏幕宽高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    public static BaseApp getInstance(){
        return sBaseApp;
    }

    public static Resources getRes() {
        return sBaseApp.getResources();
    }
}
