package com.example.apcnl.travel.base;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/3.
 */

public abstract class basePresenter<V extends baseView>{
    protected V mMvpView;
    protected ArrayList<baseModel> mModels = new ArrayList<>();

    public void bind(V view) {
        this.mMvpView = view;
    }
    public basePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestory() {
        //打断P层和V层的联系,
        mMvpView = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (baseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
