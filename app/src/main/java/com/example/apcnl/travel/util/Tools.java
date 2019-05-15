
package com.example.apcnl.travel.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/**
 * Created by xts on 2016-3-16.
 * @描述 操作文件的工具类
 */
public class Tools {

    /**
     * 获取versionCode（ANDROID版本号）
     */
    public static int getVersionCode() {
        int versioncode = 0;
        try {
            PackageInfo pinfo = BaseApp.getInstance().getPackageManager().getPackageInfo(UIUtils.getPackageName(), 0);
            versioncode = pinfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versioncode;
    }

    /**
     * 获取VersionName
     *
     * @return
     */
    public static String getVersionName() {
        try {
            PackageInfo pinfo = BaseApp.getInstance().getPackageManager()
                    .getPackageInfo(UIUtils.getPackageName(), 0);
            String versionName = pinfo.versionName;
            if (null != versionName) {
                return versionName.toLowerCase();
                //return "2.2.1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取IP
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        String ip = inetAddress.getHostAddress().toString();
                        if (ip.startsWith("10.")) {
                            return "";
                        } else if (ip.startsWith("192.168.")) {
                            return "";
                        } else if (ip.startsWith("176") && (Integer.valueOf(ip.split(".")[1]) >= 16)
                                && (Integer.valueOf(ip.split(".")[1]) <= 31)) {
                            return "";
                        } else {
                            return ip;
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取wifi的mac地址
     *
     * @return
     */
//    public static String getMacAddress() {
//        try {
//            WifiManager wifi = (WifiManager) BaseApplication.BaseApp.getInstance().getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = wifi.getConnectionInfo();
//            String mac = info.getMacAddress();
//            if (null != mac) {
//                return mac;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /**
     * 获得当前日期和时间 格式 yyyy-MM-dd HH:mm
     */
    public static String getCurrentDateTimeNoSS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * * String日期转换为Long
     *
     * @param ("MM/dd/yyyy HH:mm:ss")
     * @param date("12/31/2013 21:08:00")
     * @return * @throws ParseException
     */
    public static Long getLongTime(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        return dt.getTime();
    }

    /**
     * * String日期转换为Long
     *
     * @param ("MM/dd/yyyy HH:mm")
     * @param date("12/31/2013 21:08:00")
     * @return * @throws ParseException
     */
    public static Long getLongTime1(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = null;
        try {
            dt = sdf.parse(date);
            return dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * * String日期转换为Long
     *
     * @param ("HH:mm")
     * @param date(21:08:00")
     * @return * @throws ParseException
     */
    public static Long getLongTime3(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date dt = sdf.parse(date);
        return dt.getTime();
    }

    /**
     * 获得当前日期和时间 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前年份
     */
    public static int getYear() {
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月,英文
     * @return
     */
    public static String getMonth(){
        int month = Calendar.getInstance().get(Calendar.MONTH);
        String monthStr = "01";
        switch (month){
            case 0:
                monthStr = "January";
                break;
            case 1:
                monthStr = "February";
                break;
            case 2:
                monthStr = "March";
                break;
            case 3:
                monthStr = "April";
                break;
            case 4:
                monthStr = "May";
                break;
            case 5:
                monthStr = "June";
                break;
            case 6:
                monthStr = "July";
                break;
            case 7:
                monthStr = "August";
                break;
            case 8:
                monthStr = "September";
                break;
            case 9:
                monthStr = "October";
                break;
            case 10:
                monthStr = "November";
                break;
            case 11:
                monthStr = "December";
                break;
        }
        return monthStr;
    }

    public static String getDay(){
        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.DAY_OF_MONTH);
        if (i>9){
            return i+"";
        }else {
            return "0"+i;
        }
    }

    /**
     * 获得当前日期和时间 格式yyyy-MM-dd HH:mm:ss:SS
     */
    public static String getCurrentDateTimeWithSS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前日期和时间 格式yyyy-MM-dd HH:mm:ss:
     */
    public static String getCurrentDateTimeWithSSS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前时间的年月日
     */
    public static String getymd() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前时间
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前时间
     */
    public static String getCurrentTimeMM() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("mm", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 返回当前时间，单位毫秒
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间 string 2 long
     *
     * @param date
     * @return
     */
    public static long getDateStringToLong(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt2 = sdf.parse(date);
            //继续转换得到秒数的long型
            long time = dt2.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取时间 string 2 long
     *
     * @param date
     * @return
     */
    public static long getDateToLong(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date dt2 = sdf.parse(date);
            //继续转换得到秒数的long型
            long time = dt2.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到指定的日期
     *
     * @param date
     * @return
     */
    public static long getDateTimeStringToLong(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt2 = sdf.parse(date);
            //继续转换得到秒数的long型
            long time = dt2.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得当前日期
     */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获得当前日期
     */
    public static String getTodayate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    public static boolean isAfterToday(String curDate) {
        long curLong = getDateTimeStringToLong(curDate);
        long todayLong = System.currentTimeMillis();

        return curLong > todayLong;

    }

    /**
     * 获取指定时间的字符
     *
     * @param time
     * @return
     */
    public static String getDateToSS(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获取指定时间的字符
     *
     * @param time
     * @return
     */
    public static String getDateToMM(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 获取指定时间的字符
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String current_time = sdf.format(date);
        return current_time;
    }

    /**
     * 对时间进行处理
     *
     * @param time
     * @return
     */
    public static String timeLongToString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date dt = new Date(time * 1000l);
        String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 2015-12-7T16:00:00.000Z 转换成毫秒值
     * @param date
     * @return
     */
    public static String parseData(String date){
        if (TextUtils.isEmpty(date)){
            return "";
        }
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        try {
            Date d = format.parse(date );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format1 = sdf.format(d);
            return format1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 时间补零
     *
     * @param c
     * @return
     */
    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /**
     * 获得天数
     */
    public static int getDayNum(long millisTime) {
        int day = (int) (millisTime / (1000 * 60 * 60 * 24));
        if (millisTime % (1000 * 60 * 60 * 24) != 0) {
            return day + 1;
        }
        return day;
    }

    public static int getHourNum(long millisTime) {

        int day = (int) (millisTime / (1000 * 60 * 60));
        if (millisTime % (1000 * 60 * 60) != 0) {
            return day + 1;
        }
        return day;

    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @Description: 两个日期之间相差天数
     */
    public static int getDayBetweenDay(long startTime, long endTime) {
        int startDay = getDayNum(startTime);
        int endDay = getDayNum(endTime);
        return Math.abs(startDay - endDay);
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @Description: 两个日期之间相差的小时数
     */
    public static int getHourBetweenDay(long startTime, long endTime) {
        int startDay = getHourNum(startTime);
        int endDay = getHourNum(endTime);
        return Math.abs(startDay - endDay);
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @Description: 两个日期之间相差的小时数
     */
    public static int getHourBetweenDay(Date startTime, Date endTime) {
        int startDay = getHourNum(startTime.getTime());
        int endDay = getHourNum(endTime.getTime());
        return Math.abs(startDay - endDay);
    }

    /**
     * 返回两次的时间差的显示方式
     *
     * @param startTime
     * @param nowTime
     * @return
     */
    public static String showRuleTime(long startTime, long nowTime) {
        String re = "";

        long difftime = nowTime - startTime;
        if (difftime < 0) {
            re = "1秒前";
        } else if (difftime < 60 * 1000) {
            // 小于60s
            re = difftime / 1000 + "秒前";
        } else if (difftime < 60 * 60 * 1000) {
            // 小于60min
            re = (difftime / 1000) / 60 + "分钟前";
        } else {
            Date date_start = new Date(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String nowDay = formatter.format(new Date(nowTime));
            String yesterDay = formatter.format(new Date(nowTime - 24 * 60 * 60 * 1000));
            String startDay = formatter.format(date_start);
            if (startDay.equals(nowDay)) {
                SimpleDateFormat myFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
                re = "今天  " + myFormatter.format(date_start);
            } else if (startDay.equals(yesterDay)) {
                SimpleDateFormat myFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
                re = "昨天  " + myFormatter.format(date_start);
            } else {
                SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                re = myFormatter.format(date_start);
            }
        }
        return re;
    }

    /**
     * 判断两个更新时间差
     *
     * @param beforeTime  上一次的时间
     * @param nowTime     本次的时间
     * @param defaultDiff 需要的差距
     * @return
     */
    public static boolean dateDiff(String beforeTime, String nowTime, long defaultDiff) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date_before = formatter.parse(beforeTime);
            Date date_after = formatter.parse(nowTime);
            long now_time = date_after.getTime();
            long before_time = date_before.getTime();
            long diff = now_time - before_time;
            if (diff - defaultDiff > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 秒转分钟或小时字符串
     *
     * @param second        总秒数
     * @param isKeepSeconds 是否保留秒
     * @return
     */
    public static String secondToMin(long second, boolean isKeepSeconds) {
        String timeStr = 0 + "分";
        try {
            if (second > 0) {
                int minute = (int) (second / 60);
                int seconds = (int) (second % 60);
                if (minute > 0) {
                    if (seconds > 0) {
                        if (isKeepSeconds) {
                            if (seconds < 10) {
                                timeStr = minute + "分0" + seconds + "秒";
                            } else {
                                timeStr = minute + "分" + seconds + "秒";
                            }
                        } else {
                            if (seconds >= 30) {// 超过30秒+1分钟
                                timeStr = (minute + 1) + "分";
                            } else {// 不足30秒忽略掉
                                timeStr = minute + "分";
                            }
                        }
                    } else {
                        timeStr = minute + "分" + "00秒";
                    }
                } else {
                    timeStr = seconds + "秒";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 秒转分钟或小时字符串,格式01:23:45
     *
     * @param second        总秒数
     * @param isKeepSeconds 是否保留秒
     * @return
     */
    public static String secondToMin2(long second, boolean isKeepSeconds) {
        String timeStr = "00:00";
        try {
            if (second > 0) {
                int hour = (int)(second/3600);
                int minute = (int) (second %3600 / 60);
                int seconds = (int) (second %3600 % 60);
                if (hour >0){
                    if ( hour >=10){
                        timeStr = hour+":";
                    }else {
                        timeStr = "0"+hour+":";
                    }

                    if (minute >= 10){
                        timeStr += minute+":";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }

                    }else if (minute > 0){
                        timeStr += "0"+minute+":";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }
                    }else {
                        timeStr += "00:";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }
                    }
                }else {
                    if (minute >= 10){
                        timeStr = minute+":";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }

                    }else if (minute > 0){
                        timeStr = "0"+minute+":";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }
                    }else {
                        timeStr = "00:";
                        if (seconds >= 10){
                            timeStr += seconds;
                        }else if (seconds > 0){
                            timeStr += "0"+seconds;
                        }else {
                            timeStr +="00";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 添加桌面快捷方式
     *
     * @param activity
     * @param icon     点击图标启动intent
     * @param icon     桌面icon
     */
    public static void addShortcut(Activity activity, int icon, Activity start) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, activity.getString(R.string.app_name));
        shortcut.putExtra("duplicate", false); // 不允许重复创建

        // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
        // ComponentName comp = new ComponentName(activity.getPackageName(),
        // "."+activity.getLocalClassName());

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(activity, start.getClass())
                .setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER));
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

        // 快捷方式的图标
        ShortcutIconResource iconRes = ShortcutIconResource.fromContext(activity, icon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        activity.sendBroadcast(shortcut);
    }

    /**
     * 限制特殊字符 密码输入等处需要做判断
     */
    public static boolean limitSpecialCharacters(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ 　]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return !m.replaceAll("").equalsIgnoreCase(str);
    }

    /**
     * 读取图片资源
     *
     * @param resId 上下文
     * @param resId 资源id
     * @return
     */
    public static Bitmap readBitmap(int resId) {
        InputStream stream = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Config.ARGB_8888;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            stream = BaseApp.getInstance().getResources().openRawResource(resId);
            return BitmapFactory.decodeStream(stream, null, opt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 图片的不等比缩放
     *
     * @param src        源图片
     * @param destWidth  缩放的宽度
     * @param destHeigth 缩放的高度
     * @return
     */
    public static Bitmap lessenBitmap(Bitmap src, int destWidth, int destHeigth) {
        try {
            if (src == null)
                return null;

            int w = src.getWidth();// 源文件的大小
            int h = src.getHeight();
            float scaleWidth = ((float) destWidth) / w;// 宽度缩小比例
            float scaleHeight = ((float) destHeigth) / h;// 高度缩小比例
            Matrix m = new Matrix();// 矩阵
            m.postScale(scaleWidth, scaleHeight);// 设置矩阵比例
            Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0, w, h, m, true);// 直接按照矩阵的比例把源文件画入进行
            return resizedBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 等比缩放图片
     *
     * @param src
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap isometricScaleBitmap(Bitmap src, int targetWidth, int targetHeight) {
        if (src != null) {
            int width = src.getWidth();
            int height = src.getHeight();
            if (width * targetHeight > targetWidth * height) {
                targetHeight = targetWidth * height / width;
            } else if (width * targetHeight < targetWidth * height) {
                targetWidth = targetHeight * width / height;
            }
            float scaleWidth = ((float) targetWidth) / width;// 宽度缩小比例
            float scaleHeight = ((float) targetHeight) / height;// 高度缩小比例
            Matrix m = new Matrix();// 矩阵
            m.postScale(scaleWidth, scaleHeight);// 设置矩阵比例
            Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0, width, height, m, true);// 直接按照矩阵的比例把源文件画入进行
            return resizedBitmap;
        }
        return null;
    }

    /**
     * 从指定路径读取图片（原图读取 不会改变大小）
     *
     * @param imagePath
     * @return
     */
    public static Bitmap readBitmapFormPath(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            return null;
        }
        try {
            Bitmap bitmap = null;
            File file = new File(imagePath);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(imagePath);
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从sdcard或data文件夹读取图片(会在原图基础上进行压缩)
     *
     * @param imagePath
     * @return
     */
    public static Bitmap createBitmapFormSdcardOrData(String imagePath) {
        if (null == imagePath) {
            return null;
        }
        InputStream stream = null;
        try {
            File file = new File(imagePath);
            if (!file.exists())
                return null;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(imagePath), null, o);

            final int REQUIRED_SIZE = 100;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale++;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imagePath), null, o2);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 从sdcard或data文件夹读取图片
     *
     * @param imagePath
     * @return
     */
    public static Bitmap createBitmapFormSdcardOrData(String imagePath, int height, int width) {
        if (null == imagePath) {
            return null;
        }
        InputStream stream = null;
        try {
            File file = new File(imagePath);
            if (!file.exists() && !file.canRead() && !file.isFile())
                return null;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(imagePath), null, o);
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (width_tmp / scale > width && height_tmp / scale > height) {
                width_tmp /= 2;
                height_tmp /= 2;
                scale++;
            }
            o.inJustDecodeBounds = false;
            o.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imagePath), null, o);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 图片圆角处理
     *
     * @param bitmap  源图片
     * @param roundPx 圆角角度
     * @return
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int roundPx) {
        return getRoundBitmap(bitmap, roundPx, 1.0f, 1.0f);
    }

    /**
     * 图片圆角处理
     *
     * @param bitmap      源图片
     * @param roundPx     圆角角度
     * @param widthScale  处理后的图片宽与原图宽比例 不需要改变宽度直接传1.0f
     * @param heightScale 处理后的图片高度与原图高度比例 不需要改变高度直接传1.0f
     * @return
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int roundPx, float widthScale, float heightScale) {
        Bitmap roundBitmap = null;
        try {
            roundBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(roundBitmap);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            int width = (int) (bitmap.getWidth() * widthScale);
            int height = (int) (bitmap.getHeight() * heightScale);
            final Rect rect = new Rect(0, 0, width, height);
            final RectF rectF = new RectF(rect);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (Exception e) {
            e.printStackTrace();
            roundBitmap = bitmap;
        }
        return roundBitmap;
    }

    /**
     * 从assets文件夹读取图片
     *
     * @param imagePath
     * @return
     */
    public static Bitmap createBitmapFormAssets(String imagePath) {
        InputStream stream = null;
        try {
            if (imagePath != null) {
                stream = BaseApp.getInstance().getAssets().open(imagePath);
            }
            if (stream != null) {
                return Bitmap.createBitmap(BitmapFactory.decodeStream(stream));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取详细地址
     *
     * @param location
     * @return
     */
    public static String getAddress(Location location) {
        try {
            if (location != null) {
                Geocoder geo = new Geocoder(BaseApp.getInstance(), Locale.getDefault());
                List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (!addresses.isEmpty()) {
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String addressName = address.getAddressLine(0);
                        if (addressName == null || addressName.length() <= 3) {
                            addressName = address.getLocality();
                        }
                        return addressName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "定位失败";
    }

    /**
     * 获取城市名
     *
     * @param location
     * @return
     */
    public static String getCurrentCity(Location location) {
        try {
            if (location != null) {
                Geocoder geo = new Geocoder(BaseApp.getInstance(), Locale.getDefault());
                List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (!addresses.isEmpty()) {
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String addressName = address.getLocality();
                        return addressName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "北京";
    }

    /**
     * 获得手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        try {
            String phoneVersion = Build.MODEL;
            if (null != phoneVersion) {
                return phoneVersion;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 调用系统短信
     * <p/>
     * Activity自身
     *
     * @param body 信息内容
     */
    public static void sendSms(Activity activity, String phoneNumber, String body) {
        try {
            Uri smsToUri = Uri.parse("smsto:" + phoneNumber);// 联系人地址
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            intent.putExtra("address", phoneNumber);
            intent.putExtra("sms_body", body);// 短信的内容
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统电话
     *
     * @param activity
     * @param phoneNum
     */
    public static void openSystemPhone(Activity activity, String phoneNum) {
        try {
            Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
            activity.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统浏览器
     *
     * @param activity
     * @param url
     */
    public static void openSystemBrowser(Activity activity, String url) {
        try {

            Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统网络设置
     *
     * @param activity
     */
    public static void openSystemNetworkSetting(Activity activity) {
        try {
            Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统地图
     *
     * @param activity
     */
    public static void openSystemMap(Activity activity, String latitude, String longitude) {
        try {
            Uri mUri = Uri.parse("geo:" + latitude + "," + longitude);
            Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
            activity.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得屏幕的宽度
     *
     * @return int
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = BaseApp.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return int
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = BaseApp.getInstance().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取使用内存大小
     */
    public static int getMemory() {
        int pss = 0;
        ActivityManager myAM = (ActivityManager) BaseApp.getInstance()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = BaseApp.getInstance().getPackageName();
        List<RunningAppProcessInfo> appProcesses = myAM.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)) {
                int pids[] = {appProcess.pid};
                Debug.MemoryInfo self_mi[] = myAM.getProcessMemoryInfo(pids);
                pss = self_mi[0].getTotalPss();
            }
        }
        return pss;
    }

    /**
     * 获得CPU使用率
     */
    public static int getCpuInfo() {
        int cpu = 0;
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();
            String[] toks = load.split(" ");
            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            reader.seek(0);
            load = reader.readLine();
            reader.close();
            toks = load.split(" ");
            long idle2 = Long.parseLong(toks[5]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);
            cpu = (int) (100 * (cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cpu;
    }

    /**
     * 获得手机IMEI
     *
     * @return
     */
    public static String getIMEI() {
        try {
            TelephonyManager tm = (TelephonyManager) BaseApp.getInstance()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String imei = tm.getDeviceId();
            if (null != imei) {
                return imei;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获得手机IMSI
     *
     * @return
     */
    public static String getIMSI() {
        try {
            TelephonyManager tm = (TelephonyManager) BaseApp.getInstance()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String imsi = tm.getSubscriberId();
            if (null != imsi) {
                return imsi;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 得到系统亮度
     *
     * @return
     */
    public static int getSystemBrightness() {
        int brightness = 5;
        try {
            brightness = Settings.System.getInt(BaseApp.getInstance().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            brightness = brightness * 100 / 255;
        } catch (SettingNotFoundException ex) {
            ex.printStackTrace();
        }
        return brightness >= 5 ? brightness : 5;
    }

    /**
     * 调节屏幕亮度
     *
     * @param value
     */
    public static void setBackLight(Activity activity, int value) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.screenBrightness = (float) (value * (0.01));
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sdcard或data的剩余空间
     */
    public static long getSdcardFreeSize(String rootPath) {
        // 取得sdcard文件路径
        StatFs statFs = new StatFs(rootPath);
        // 获取block的SIZE
        long blocSize = statFs.getBlockSize();
        // 可使用的Block的数量
        long availaBlock = statFs.getAvailableBlocks();
        // 剩余空间大小
        long freeSize = availaBlock * blocSize;
        return freeSize;
    }

    /**
     * 获得系统版本号
     *
     * @return
     */
    public static String getSDK() {
        try {
            String release = Build.VERSION.RELEASE;
            if (null != release) {
                return release;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取所有的launcher信息
     *
     * @param context
     * @return
     */
    private static List<String> getAllTheLauncher(Context context) {
        List<String> names = null;
        PackageManager pkgMgt = context.getPackageManager();
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> ra = pkgMgt.queryIntentActivities(it, 0);
        if (ra.size() != 0) {
            names = new ArrayList<String>();
        }
        for (int i = 0; i < ra.size(); i++) {
            String packageName = ra.get(i).activityInfo.packageName;
            names.add(packageName);
        }
        return names;
    }

    /**
     * 判断字符串是否为数字(不能判断float类型)
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str.matches("\\d*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为数字(包含float类型)
     *
     * @param str
     */
    public static boolean isNumeric(String str) {
        if (str.matches("\\d+(.\\d+)?")) {
            return true;
        } else {// 不是数字
            return false;
        }
    }

    /**
     * 设置Selector
     */
    public static StateListDrawable newSelector(Context context, int[] state) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = state[0] == -1 ? null : context.getResources().getDrawable(state[0]);
        Drawable pressed = state[1] == -1 ? null : context.getResources().getDrawable(state[1]);
        Drawable focused = state[1] == -1 ? null : context.getResources().getDrawable(state[1]);
        Drawable unable = state[0] == -1 ? null : context.getResources().getDrawable(state[0]);
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focused);
        // View.ENABLED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_enabled}, normal);
        // View.FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_focused}, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_window_focused}, unable);
        // View.EMPTY_STATE_SET
        bg.addState(new int[]{}, normal);
        return bg;
    }

    /**
     * 切换软键盘
     */
    public static void switchKeyBoardCancle(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }
    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     */
    public static void openKeybord(EditText mEditText)
    {
        InputMethodManager imm = (InputMethodManager) BaseApp.getInstance()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    /**
     * 关闭软键盘
     *
     * @param activity
     */
    public static void closeKeyBoard(Activity activity,View v) {
       /* InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(activity.getWindow().getDecorView().getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);*/
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
    /**
     * 关闭软键盘
     *
     * @param activity
     */
    public static void closeKeyBoard(Activity activity) {
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(activity.getWindow().getDecorView().getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * MD5加密
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.substring(0, md5StrBuff.length()).toString();
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static String getRandomNumber() {
        return new DecimalFormat("0000000000").format(new Random().nextInt(1000000000));
    }

    /**
     * 截取并按规则组合字符串
     *
     * @return
     */
    public static String subAndCombinationString(String str, int subLength,
                                                 boolean isReduction) {
        if (isReduction) {
            String str1 = str.substring(0, subLength);
            String str2 = str.replace(str1, "");
            String result = str2 + str1;
            return result;
        } else {
            String temp = str.substring(0, str.length() - subLength);
            String str1 = temp.substring(0, subLength);
            String str2 = temp.replace(str1, "");
            String str3 = str.replace(temp, "");
            String result = str3 + str1 + str2;
            return result;
        }
    }

    /**
     * 保存一张图片到sd卡
     *
     * @param bitmapPath
     * @param bitmapName
     * @param mBitmap
     */
    public static void saveBitmapToSdcard(String bitmapPath, String bitmapName, Bitmap mBitmap) {
        FileOutputStream fOut = null;
        try {
            File f = new File(bitmapPath, bitmapName);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!f.exists()){
                f.createNewFile();
            }

            fOut = new FileOutputStream(f);

            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fOut != null) {
                    fOut.flush();
                    fOut.close();
                    fOut = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 保存一张图片到sd卡
     *
     * @param bitmapPath
     * @param mBitmap
     */
    public static void saveBitmapToSdcard(String bitmapPath, Bitmap mBitmap) {
        FileOutputStream fOut = null;
        try {
            File f = new File(bitmapPath);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!f.exists()){
                f.createNewFile();
            }

            fOut = new FileOutputStream(f);

            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (fOut != null) {
                    fOut.flush();
                    fOut.close();
                    fOut = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * string转boolean
     *
     * @param s
     * @return
     */
    public static boolean stringToBoolean(String s) {
        if (s != null) {
            if (s.equals("1")) {
                return true;
            } else if (s.equals("0")) {
                return false;
            } else {
                return Boolean.parseBoolean(s);
            }
        }
        return false;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 退出软件
     */
    public static void exitApp(boolean is) {

//		BaseApplication.BaseApp.getInstance().exit();
//		Context BaseApp.getInstance() = BaseApplication.BaseApp.getInstance();
//		NotificationManager notificationManager = (NotificationManager) BaseApp.getInstance()
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//		notificationManager.cancelAll();
//		if(is)
//		{
//			ActivityManager activityManager = (ActivityManager) BaseApp.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
//			activityManager.restartPackage(BaseApp.getInstance().getPackageName());
//		}


//		android.os.Process.killProcess(android.os.Process.myPid());


    }

    public static void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(UIUtils.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 保存数据到sd卡
     *
     * @param content
     * @param fileName
     * @param filePath
     * @param isGzip   true压缩保存
     * @param isAppend true续写文件，false重新写文件
     * @return 0:成功，1：sd卡错误，2：其他错误,3:存储卡已满
     * @throws
     */
    public synchronized static void writeDataToSdcard(byte[] content, String filePath, String fileName, boolean isGzip,
                                                      boolean isAppend) throws Exception {
        FileOutputStream fos = null;
        GZIPOutputStream gzin = null;
        try {
            // 判断当前的可用盘符，优先使用sdcard
            File testDir = new File(filePath);
            if (!testDir.exists()) {
                testDir.mkdirs();
            }

            File file = new File(filePath + fileName);
            if (isAppend) {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } else {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            }

            if (file.exists() && file.canWrite()) {
                fos = new FileOutputStream(file, isAppend);
                if (isGzip) {
                    gzin = new GZIPOutputStream(fos);
                    gzin.write(content);
                    gzin.flush();
                } else {
                    fos.write(content);
                    fos.flush();
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (gzin != null) {
                    gzin.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取指定画笔的文字高度
     *
     * @param paint
     * @return
     */
    public static float getFontHeight(Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * 获取全部图片的_id和路径
     *
     * @return
     */
    public static LinkedHashMap<Long, String> getAllImages(Context context) {
        LinkedHashMap<Long, String> images = new LinkedHashMap<Long, String>();
        try {
            // 得到数据库所有图片的Cursor
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            Uri uri = intent.getData();
            String[] proj = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " DESC");
            while (cursor.moveToNext()) {
                // 获取图片的_id
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                // 获取图片的路径
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                images.put(id, path);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    /**
     * 的到图片的缩略图
     *
     * @param context
     * @param imageId 数据库中的图片_id
     * @return
     */
    public static Bitmap getImageThumbnail(Context context, long imageId) {
        Bitmap bitmap = null;
        // 根据ID获取缩略图
        bitmap = Thumbnails.getThumbnail(context.getContentResolver(), imageId, Thumbnails.MICRO_KIND, null);
        return bitmap;
    }

    /**
     * 获取全部文件夹下的图片的_id和路径
     *
     * @return
     */
    public static LinkedHashMap<Long, String> getAllImages(Context context, String folderPath) {
        LinkedHashMap<Long, String> images = new LinkedHashMap<Long, String>();
        try {
            // 得到数据库所有图片的Cursor
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            Uri uri = intent.getData();
            String[] proj = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " DESC");
            while (cursor.moveToNext()) {
                // 获取图片的_id
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                // 获取图片的路径
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                int lastIndexOf = path.lastIndexOf(File.separator);
                String substring = path.substring(0, lastIndexOf);
                if (folderPath.equals(substring)) {
                    images.put(id, path);
                }
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

	/**
	 * 获取视频第一帧图片
	 *
	 * @param videoPath
	 *            视频地址
	 * @return
	 */
	public static Bitmap getVideoFirstFrame(String videoPath) {
		Bitmap bitmap = null;
		try {
			if (!TextUtils.isEmpty(videoPath)) {
				MediaMetadataRetriever media = new MediaMetadataRetriever();
				media.setDataSource(videoPath);
				bitmap = media.getFrameAtTime(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 是否匹配第一位是1共11位数字的正则表达式
     *
     * @param no
     * @return
     */
    public static boolean matchesPhoneNo(String no) {
        String pattern = "^[1]\\d{10}$";
        return Pattern.compile(pattern).matcher(no).matches();
    }


    /**
     * 是否匹配数字和字母
     *
     * @param no
     * @return
     */
    public static boolean isPsd(String no) {
        String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        return Pattern.compile(pattern).matcher(no).matches();
    }

    /**
     * 获取yyyy-MM-dd的日期格式  并且加天天数
     *
     * @param s 传入2010-01-01格式的数据
     * @param n 传入你想要加的天数
     * @return
     */
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取yyyy-MM-dd的日期格式  并且减天数
     *
     * @param s 2010-01-01格式的数据
     * @param n 传入你想要减的天数
     * @return
     */
    public static String JianDay(String s, int n) {
        try {

            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
            Calendar date = Calendar.getInstance();
            date.setTime(dft.parse(s));
            date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);//减一天
//	    		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-1);//减一月
            Date endDate = dft.parse(dft.format(date.getTime()));
            return dft.format(endDate);

        } catch (Exception e) {
            return null;
        }

    }


    /**
     * @param curTime
     * @param start   HH：mm 格式
     * @param end     HH：mm 格式
     * @return
     * @Description: 判断时间是否在指点时间段内
     */
    public static boolean isTimeBetween(long curTime, String date, String start, String end) {

        long startLong = Tools.getDateStringToLong(date + " " + start + ":00");
        long endLong = Tools.getDateStringToLong(date + " " + end + ":00");

        return curTime > startLong && curTime < endLong;
    }

    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     * <p/>
     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
     * <p/>
     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
     * <p/>
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param path
     * @return
     */

    public static Bitmap convertToBitmap(String path) {
        int w = 100;
        int h = 100;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
//        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
        return Bitmap.createBitmap(weak.get());
    }


    public static boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) BaseApp.getInstance().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = BaseApp.getInstance().getApplicationContext().getPackageName();

        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     * <p/>
     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
     * <p/>
     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
     * <p/>
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param path
     * @return
     */

    public static Bitmap convertToBitmap(String path,int w,int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }
    /**
     * 取得圆形图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //保证是方形，并且从中心画
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int w;
        int deltaX = 0;
        int deltaY = 0;
        if (width <= height) {
            w = width;
            deltaY = height - w;
        } else {
            w = height;
            deltaX = width - w;
        }
        final Rect rect = new Rect(deltaX, deltaY, w, w);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        //圆形，所有只用一个

        int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    public static String getSelectTime(int y, int m, int d) {
        StringBuffer sb = new StringBuffer();
        sb.append(y);
        sb.append("-");
        if (m < 10)
            sb.append(0);
        sb.append(m);
        sb.append("-");
        if (d < 10)
            sb.append(0);
        sb.append(d);

        return sb.toString();
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gps) {
            return true;
        }

        return false;
    }

    /**
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (CanceledException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将list<String>转成逗号隔开的字符串
     */
    public static String getListString(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        if (null != list && list.size() > 0) {
            for (String str : list) {
                buffer.append(str + ",");
            }
            return buffer.toString().substring(0, buffer.toString().length() - 1);
        }
        return "";
    }

    /**
     * 将逗号隔开的字符串转成list<String></>
     */
    public static List<String> getList(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] array = str.split(",");
            return Arrays.asList(array);
        }
        return null;
    }

    public static Bitmap createColorBitmap(int color, int width, int height) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bmp.eraseColor(color);
        return bmp;
    }


    /**
     * 计算两个坐标之间的距离
     */
    public static double getDistanceFromXtoY(double lat_a, double lng_a,
                                             double lat_b, double lng_b) {
        double pk = (double) (180 / 3.14169);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }

    //将图片按照某个角度进行旋转
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    //bitmap转化为drawable
    public static Bitmap setDrawable1(Bitmap backGroundMap) {
        int widthDrawable = backGroundMap.getWidth();
        int heightDrawable = backGroundMap.getHeight();//获取背景图片的宽和高

        int center_X = widthDrawable/2;
        int center_Y = heightDrawable/2;

        int WH = 100;
        if (widthDrawable > WH && heightDrawable > WH) {
            WH = 100;
        }else{
            if (widthDrawable > heightDrawable) {
                WH = heightDrawable;
            }else{
                WH = widthDrawable;
            }
        }
        int start_x = center_X - WH / 2;
        int start_y = center_Y - WH / 2;
        return Bitmap.createBitmap(backGroundMap, start_x, start_y, WH,
                WH, null, true);

    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    // 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathByUri4kitkat(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore
            // (and
            // general)
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
         /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            Integer invoke = (Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg);
            return ( invoke== AppOpsManager.MODE_ALLOWED) || invoke == AppOpsManager.MODE_DEFAULT;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDateThisTimeZone(String scenicTime,String scenicTimeZone) {
        //Asia/Tokyo,Asia/Shanghai
        if (TextUtils.isEmpty(scenicTime)) {
            return "";
        }
        scenicTime = "2017-03-03T"+scenicTime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone(scenicTimeZone));
        Date value = null;
        try {
            value = formatter.parse(scenicTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        String dt = dateFormatter.format(value);
        String[] ts = dt.split("T");
        return ts[1];
    }

    /**
     * 判断是否已过24小时或者已过0点,用来抽奖活动每日0点刷新弹出次数
     * 单位为毫秒
     * @param currentTime 现在时间
     * @param pastTime 上次的时间
     */
    public static boolean judgeTime(long currentTime,long pastTime){
        long current = currentTime / (1000 * 3600 * 24);
        long past = pastTime / (1000 * 3600 * 24);
        if (current - past >=1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 加密
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public static String  encrypt(String plaintext) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Return pseudo unique ID,Serial序列号-->UUID
     * @return ID
     */
    public static String getDeviceId(){

        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 将资源图片转换为bitmap
     */
    public static Bitmap convertRes2Bitmap(Context context ,int resId){
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static String getIP(Context context){

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 外网ip
     * @return
     */
    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
                int start = strber.indexOf("{");
                int end = strber.indexOf("}");
                String json = strber.substring(start, end + 1);
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        line = jsonObject.optString("cip");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
