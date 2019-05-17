package com.example.apcnl.travel.net;

import com.example.apcnl.travel.bean.AllReviewsBean;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.bean.BanmiParticularsPathBean;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.MeCollectBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;
import com.example.apcnl.travel.bean.MeFollowBean;
import com.example.apcnl.travel.bean.MeInfoBean;
import com.example.apcnl.travel.bean.PathPartIcularsBean;
import com.example.apcnl.travel.bean.ThemeBean;
import com.example.apcnl.travel.bean.VerifyCodeBean;
import com.example.apcnl.travel.bean.VersionNameBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by apcnl on 2019/5/6.
 */

public interface EveryWhereApiService {

    String mUrl = "http://api.banmi.com/";

    @GET("api/3.0/content/routesbundles?page=1")
    Observable<EveryWhereBean> getData(@Header("banmi-app-token") String header);

    @GET("api/3.0/banmi?page=1")
    Observable<HomeBean> getHomeData(@Header("banmi-app-token") String header);

    @GET("api/3.0/account/info")
    Observable<MeInfoBean> getInfoData(@Header("banmi-app-token") String header);

    @GET("api/3.0/content/routes/{routeId}")
    Observable<PathPartIcularsBean> getPathData(@Path("routeId") int routeId,@Header("banmi-app-token") String header);

    @POST("api/3.0/content/routes/{routeId}/like")
    Observable<HomeCollectBean> setCollectData(@Path("routeId") int routeId, @Header("banmi-app-token") String header);

    @POST("api/3.0/content/routes/{routeId}/dislike")
    Observable<HomeCollectBean> setUnCollectData(@Path("routeId") int routeId, @Header("banmi-app-token") String header);

    //http://api.banmi.com/api/3.0/account/collectedRoutes?page=1
    @GET("api/3.0/account/collectedRoutes")
    Observable<MeCollectBean> getCollectData(@Query("page") int page, @Header("banmi-app-token") String header);

    @POST("api/3.0/banmi/{banmiId}/follow")
    Observable<EverywhereFollowBean> setFollowData(@Path("banmiId") int id, @Header("banmi-app-token") String header);

    @POST("api/3.0/banmi/{banmiId}/unfollow")
    Observable<EverywhereFollowBean> setUnFollowData(@Path("banmiId") int id, @Header("banmi-app-token") String header);

    @GET("api/3.0/account/followedBanmi")
    Observable<MeFollowBean> getFollowData(@Header("banmi-app-token") String header);

    @GET("api/3.0/content/routes/{routeId}/reviews")
    Observable<AllReviewsBean> getAllReviewsData(@Path("routeId") int id, @Header("banmi-app-token") String header);

    //http://api.banmi.com/api/3.0/content/bundles
    @GET("api/3.0/content/bundles")
    Observable<ThemeBean> getThemeData(@Header("banmi-app-token") String header);

    @GET("api/3.0/banmi/{banmiId}")
    Observable<BanmiParicularsBean> getBanmiParticularsData(@Path("banmiId") int id,
                                                            @Query("page") int page,
                                                            @Header("banmi-app-token") String header);
    @GET("api/3.0/banmi/{banmiId}/routes")
    Observable<BanmiParticularsPathBean> getBanmiParticularsPathData(@Path("banmiId") int id,
                                                                          @Query("page") int page,
                                                                          @Header("banmi-app-token") String header);

    @GET("api/app/common/getVersionInfo?operating_system=android")
    Observable<VersionNameBean> getVersionNameData(@Header("banmi-app-token") String header);

    public String sDownLoadAppUrl = "http://cdn.banmi.com/banmiapp/";

    @GET("apk/banmi_330.apk")
    Observable<ResponseBody> downloadData();
}
