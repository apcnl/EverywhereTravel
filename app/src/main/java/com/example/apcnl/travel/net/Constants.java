package com.example.apcnl.travel.net;

import android.os.Environment;

import com.example.apcnl.travel.base.BaseApp;

import java.io.File;

/**
 * Created by asus on 2019/3/5.
 */

public interface Constants {
    //是否为debug状态,正式上线版本需要改为false
    boolean isDebug = true;


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = BaseApp.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";


    String TOKEN = "token";
    String DESC = "description";
    String USERNAME = "userName";
    String GENDER = "gender";
    String EMAIL = "email";
    String PHOTO = "photo";
    String PHONE = "phone";
    String TYPE = "type";
    int REQUEST_CODE = 1000;
    String VERIFY_CODE = "verify_code";
    int RESULT_CODE = 1001;
    String ME_INFO_NAME = "me_info_name";
    String ME_INFO_QIANMING = "me_info_qianming";

    String ME_INFO_SEX = "me_info_sex";
    String FIRST_ENTER = "first_enter";
    int ALBOM_CODE = 200;
    String ME_INFO_IMG = "me_info_img";
    int IMG_RESULT_CODE = 2000;
    int CAMREA_CODE = 300;
    String PATH_ID = "path_id";
    String COLLECT_ID = "collect_id";
    String ROUTE = "route";
    String BUNDLE = "bundle";

    String BANMI_ID = "banmi_id";
    String COLLECT = "collect";
    String FOLLOW = "follow";
}
