package com.example.apcnl.travel.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by $lzj on 2019/3/21.
 */
public class InstallUtil {


    public static final int UNKNOWN_CODE = 2019;
    public static void installApk(Context context, String path) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startInstallO(context,path);
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            startInstallN(context,path);
        }else {
            startInstall(context,path);
        }
    }

    /**
     *android1.x-6.x
     *@param path 文件的路径
     */
    private static void startInstall(Context context, String path) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }

    /**
     * android7.x
     * @param path 文件路径
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void startInstallN(Context context, String path) {
        //参数1 上下文, 参数2 在AndroidManifest中的android:authorities值, 参数3  共享的文件
        Uri apkUri = FileProvider.getUriForFile(context, "com.baidu.upload.2provider", new File(path));
        Intent install = new Intent(Intent.ACTION_VIEW);
        //由于没有在Activity环境下启动Activity,设置下面的标签
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        context.startActivity(install);
    }

    /**
     * android8.x
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void startInstallO(final Context context, String path) {
        boolean isGranted = context.getPackageManager().canRequestPackageInstalls();
        if (isGranted) startInstallN(context,path);//安装应用的逻辑(写自己的就可以)
        else new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("安装应用需要打开未知来源权限，请去设置中开启权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        Activity act = (Activity) context;
                        act.startActivityForResult(intent, UNKNOWN_CODE);
                    }
                })
                .show();
    }
}