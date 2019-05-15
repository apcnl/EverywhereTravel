package com.example.apcnl.travel.util;
import android.util.Log;

import com.example.apcnl.travel.net.Constants;


/**
 * Created by asus on 2019/3/5.
 */

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
