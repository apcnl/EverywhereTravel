package com.example.apcnl.travel.fragment.mainfragment;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.view.EmptyView;

/**
 * Created by apcnl on 2019/5/17.
 */

public class FindFragment extends BaseFragment<EmptyView,Emptypresenter> implements EmptyView{
    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }
}
