package com.example.apcnl.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.fragment.mainfragment.HowWanFragment;
import com.example.apcnl.travel.fragment.mainfragment.FindFragment;
import com.example.apcnl.travel.fragment.mainfragment.MeFragment;
import com.example.apcnl.travel.presenter.Mainpresenter;
import com.example.apcnl.travel.view.MainView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends baseactivity<MainView, Mainpresenter> implements MainView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    public static int TYPE_NAME = 0;
    public static int TYPE_SIGER = 1;
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
        list.add(new FindFragment());
        list.add(new HowWanFragment());
        list.add(new MeFragment());

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("发现");
        strings.add("怎么玩");
        strings.add("我的");

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
        TabLayout.Tab tabAt3 = mTablayout.getTabAt(2);
        tabAt.setIcon(R.drawable.selector_main_home);
        tabAt2.setIcon(R.drawable.selector_main_everywhere);
        tabAt3.setIcon(R.drawable.selector_main_me);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
}
