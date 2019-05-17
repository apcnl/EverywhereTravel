package com.example.apcnl.travel.fragment.mainfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.BanmiParticularsActivity;
import com.example.apcnl.travel.adapter.MainHomeRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Homepresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by apcnl on 2019/5/5.
 */

public class MeFragment extends BaseFragment<HomeView, Homepresenter> implements HomeView {

    @Override
    protected Homepresenter initPresenter() {
        return new Homepresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {



    }


    @Override
    protected void initListener() {

    }
}
