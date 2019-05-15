package com.example.apcnl.travel.view;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.EveryWhereBean;

/**
 * Created by apcnl on 2019/5/3.
 */

public interface EveryWhereView extends baseView {
    void onSuccess(EveryWhereBean bean);

    void onFail(String msg);
}
