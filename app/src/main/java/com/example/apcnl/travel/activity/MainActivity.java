package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.fragment.mainfragment.EverywhereFragment;
import com.example.apcnl.travel.fragment.mainfragment.HomeFragment;
import com.example.apcnl.travel.presenter.Mainpresenter;
import com.example.apcnl.travel.view.MainView;
import com.example.apcnl.travel.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends baseactivity<MainView, Mainpresenter> implements MainView {

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
        if (mDl.isDrawerOpen(mNv)){
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
        startActivity(new Intent(this,MeFollowActivity.class));
    }
}
