package com.example.apcnl.travel.net;

import com.example.apcnl.travel.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by apcnl on 2019/5/6.
 */

public interface VerifyService {

    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    @GET("verify")
    Observable<VerifyCodeBean> getData();
}
