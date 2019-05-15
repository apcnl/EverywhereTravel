package com.example.apcnl.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.util.SpUtil;

/**
 * Created by apcnl on 2019/5/13.
 */

public class AndroidJs extends Object{

    private static final String TAG = "AndroidJs";
    private Context mContext;


    public AndroidJs(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void callAndroid(String type,int id) {
        if (type.equals("route_details")){
            Log.d(TAG, "callAndroid: "+id);
            SpUtil.setParam(Constants.PATH_ID,id);
            mContext.startActivity(new Intent(mContext,PathParticularsActivity.class));

        }
    }


    @JavascriptInterface
    public void callAndroid(String type) {
        if (type.equals("subject_list")){
            Log.d(TAG, "callAndroid: "+"1234566456");
            mContext.startActivity(new Intent(mContext,ThemeActivity.class));
        }
    }

}
