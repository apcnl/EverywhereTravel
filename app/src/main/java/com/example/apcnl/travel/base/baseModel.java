package com.example.apcnl.travel.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by apcnl on 2019/5/3.
 */

public class baseModel {
    private CompositeDisposable mCompositeDisposable;

    public void onDestory() {
        //切换所有的Disposable对象
        mCompositeDisposable.clear();
    }

    public void addDisposable(Disposable d){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }
}
