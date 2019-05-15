package com.example.apcnl.travel.util;


import android.content.Context;
import android.content.res.Resources;

import com.example.apcnl.travel.base.BaseApp;


/**
 * Created by xts on 2017/3/9.
 * @描述 Ui相关的工具类,含Context获取,资源获取,包名
 */

public class UIUtils {
	/**得到上下文*/
	public static Context getContext() {
		return BaseApp.getInstance();
	}

	/**得到Resource对象*/
	public static Resources getResources() {
		return getContext().getResources();
	}

	/**得到string.xml中的字符*/
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**得到string.xml中的字符数组*/
	public static String[] getStringArr(int resId) {
		return getResources().getStringArray(resId);
	}

	/**得到color.xml中的颜色值*/
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/**得到应用程序的包名*/
	public static String getPackageName() {
		return getContext().getPackageName();
	}

}
