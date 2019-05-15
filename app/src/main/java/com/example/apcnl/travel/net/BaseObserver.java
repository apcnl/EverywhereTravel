package com.example.apcnl.travel.net;

import android.net.ParseException;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseApp;
import com.example.apcnl.travel.util.Logger;
import com.example.apcnl.travel.util.SystemUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by asus on 2019/3/4.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private final String TAG = getClass().getName();
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;

    @Override
    public void onError(Throwable e) {
        Logger.logD(TAG, "error: "+e.toString());
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                error(e.toString());
            } else {
                error(BaseApp.getRes().getString(R.string.unknow_error));
            }
        }
    }

    private void onException(int unknownError) {
        switch (unknownError) {
            case CONNECT_ERROR:
                error(BaseApp.getRes().getString(R.string.conn_error));
                break;

            case CONNECT_TIMEOUT:
                error(BaseApp.getRes().getString(R.string.conn_timeout));
                break;

            case BAD_NETWORK:
                error(BaseApp.getRes().getString(R.string.net_error));
                break;

            case PARSE_ERROR:
                error(BaseApp.getRes().getString(R.string.parse_error));
                break;

            default:
                break;
        }
    }

    public abstract void error(String msg);

    @Override
    public void onComplete() {
        Logger.logD(TAG, "onComplete: ");
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!SystemUtil.isNetworkConnected()){
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.net_unused));
            return;
        }
        subscribe(d);
    }

    protected abstract void subscribe(Disposable d);

}
