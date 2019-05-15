package com.example.apcnl.travel.view;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.bean.EverywhereFollowBean;

/**
 * Created by apcnl on 2019/5/3.
 */

public interface HomeView extends baseView {
    void setData(HomeBean bean);

    void setFollowData(EverywhereFollowBean bean);

    void setUnFollowData(EverywhereFollowBean bean);
}
