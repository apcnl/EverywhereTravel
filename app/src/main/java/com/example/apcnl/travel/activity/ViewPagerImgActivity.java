package com.example.apcnl.travel.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.BanmiPartDongtaiRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.view.EmptyView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewPagerImgActivity extends baseactivity<EmptyView, Emptypresenter>
        implements EmptyView {


    private static final String TAG = "ViewPagerImgActivity";
    @BindView(R.id.vp)
    ViewPager mVp;
    private PhotoView mImg;


    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_view_pager_img;
    }

    @Override
    protected void iniView() {

        StatusBarUtil.setLightMode(this);

        int position = getIntent().getIntExtra("position", 0);
        int positionImg = getIntent().getIntExtra("position2", 0);
        Log.d(TAG, "iniView: " + position);
        Log.d(TAG, "iniView: " + positionImg);

        BanmiParicularsBean.ResultBean.ActivitiesBean bean = BanmiPartDongtaiRlvAdapter.mList.get(position);
        final List<?> images = bean.getImages();
        final ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            View inflate = LayoutInflater.from(ViewPagerImgActivity.this).inflate(R.layout.viewpage_item, null);
            mImg = inflate.findViewById(R.id.img);
            Glide.with(ViewPagerImgActivity.this).load(images.get(i)).into(mImg);
            views.add(inflate);

            mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            mImg.enable();

        }

        mVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //%views.size()
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(views.get(position));
            }
        });
        mVp.setCurrentItem(positionImg);
    }

    @Override
    protected void iniListener() {
    }


}


