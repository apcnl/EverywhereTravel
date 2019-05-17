package com.example.apcnl.travel.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.VersionNameBean;
import com.example.apcnl.travel.fragment.mainfragment.EverywhereFragment;
import com.example.apcnl.travel.fragment.mainfragment.HomeFragment;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Mainpresenter;
import com.example.apcnl.travel.util.InstallUtil;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.Tools;
import com.example.apcnl.travel.view.MainView;
import com.example.apcnl.travel.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class MainActivity extends baseactivity<MainView, Mainpresenter> implements MainView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.img)
    CircleImageView mImg;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    public static int TYPE_NAME = 0;
    public static int TYPE_SIGER = 1;
    @BindView(R.id.rl_collect)
    RelativeLayout mRlCollect;
    @BindView(R.id.rl_follow)
    RelativeLayout mRlFollow;
    @BindView(R.id.tv_detection)
    TextView mTvDetection;
    private boolean isPressedBackOnce = false;
    private long firstPressedTime = 0;
    private long secondPressedTime = 0;
    private long firstTime = 0;

    @Override
    protected Mainpresenter initPresenter() {
        return new Mainpresenter();
    }


    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);

        final ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new EverywhereFragment());
        list.add(new HomeFragment());

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("首页");
        strings.add("到处");

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });

        mTablayout.setupWithViewPager(mVp);
        TabLayout.Tab tabAt = mTablayout.getTabAt(0);
        TabLayout.Tab tabAt2 = mTablayout.getTabAt(1);
        tabAt.setIcon(R.drawable.selector_main_home);
        tabAt2.setIcon(R.drawable.selector_main_everywhere);

        IntentMeInfo();
    }

    /**
     * 跳转到个人信息
     */
    private void IntentMeInfo() {
        findViewById(R.id.comp_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MeInfoActivity.class));
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mDl.isDrawerOpen(mNv)) {
            mDl.closeDrawer(mNv);
            return false;
        }
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.img)
    public void onClick() {
        mDl.openDrawer(mNv);
    }

    @OnClick(R.id.rl_collect)
    public void onClick2() {
        startActivity(new Intent(this, MeCollectActivity.class));
    }


    @OnClick(R.id.rl_follow)
    public void onClick3() {
        startActivity(new Intent(this, MeFollowActivity.class));
    }
    

    @OnClick(R.id.tv_detection)
    public void onClick4() {
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        mpresenter.VersionNameData(param);
    }

    @Override
    public void onSuccessVersionName(VersionNameBean bean) {
        String version = bean.getResult().getInfo().getVersion();
        Log.d(TAG, "onSuccessVersionName: "+bean.getResult().getInfo().getVersion());
        String versionName = Tools.getVersionName();
        Log.d(TAG, "onSuccessVersionName: "+versionName);
        if (!version.equals(versionName)){
            showAleatDialog(version,versionName);
        }
    }

    private void showAleatDialog(String version, String versionName) {
        new AlertDialog.Builder(this)
                .setTitle("发现新版本")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("最新版本："+version+"\n\n"
                        +"当前版本："+versionName+"\n\n"
                        +"更新内容：\n"+"  1.增加***功能\n"+"  2.用户界面优化")
                .setNegativeButton("以后再说",null)
                .setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mpresenter.downloadAppData();
                    }
                })
                .show();
    }


    @Override
    public void setData(ResponseBody responseBody) {
        InputStream inputStream = responseBody.byteStream();
        long max = responseBody.contentLength();

        saveFile(inputStream,mSd+"/"+"abc.apk",max);
    }

    private void saveFile(InputStream inputStream, final String string, long max) {
        //读写的进度
        long count = 0;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(string));

            byte[] bys = new byte[1024*10];
            int length = -1;

            while((length = inputStream.read(bys))!=-1){
                outputStream.write(bys,0,length);

                count += length;

                Log.d(TAG, "progress: "+count +"  max:"+max);
            }

            inputStream.close();
            outputStream.close();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toastShort("下载完成");

                    InstallUtil.installApk(MainActivity.this,string);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
