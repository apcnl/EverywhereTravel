package com.example.apcnl.travel.view;

import com.example.apcnl.travel.base.baseView;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.PathPartIcularsBean;

/**
 * Created by apcnl on 2019/5/10.
 */

public interface PathView extends baseView {
    void setData(PathPartIcularsBean bean);

    void setCollectData(HomeCollectBean bean);
    void setUnCollectData(HomeCollectBean bean);
}
