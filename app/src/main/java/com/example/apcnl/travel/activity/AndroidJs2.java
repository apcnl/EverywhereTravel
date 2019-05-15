package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.util.SpUtil;

/**
 * Created by apcnl on 2019/5/13.
 */

public class AndroidJs2 extends Object{

    private static final String TAG = "AndroidJs";
    private Context mContext;


    public AndroidJs2(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void callAndroid(String type) {
        if (type.equals("subject_list")){
            //Log.d(TAG, "callAndroid: "+"12345645");
            mContext.startActivity(new Intent(mContext,ThemeActivity.class));
        }
        //subject_list
    }

}
