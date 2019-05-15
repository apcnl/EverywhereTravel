package com.example.apcnl.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.adapter.MainHomeReviewsRlvAdapter;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.HomeCollectBean;
import com.example.apcnl.travel.bean.PathPartIcularsBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.PathPresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.util.UIUtils;
import com.example.apcnl.travel.view.PathView;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PathParticularsActivity extends baseactivity<PathView, PathPresenter>
        implements PathView {


    private static final String TAG = "PathParticularsActivity";
    @BindView(R.id.img_background)
    ImageView mImgBackground;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_intro)
    TextView mTvIntro;
    @BindView(R.id.banmi_img)
    ImageView mBanmiImg;
    @BindView(R.id.tv_banmi_name)
    TextView mTvBanmiName;
    @BindView(R.id.tv_banmi_city)
    TextView mTvBanmiCity;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.tv_banmi_work)
    TextView mTvBanmiWork;
    @BindView(R.id.fa_container)
    RelativeLayout mFaContainer;
    @BindView(R.id.img_collect)
    ImageView mImgCollect;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.rl_collect)
    RelativeLayout mRlCollect;
    @BindView(R.id.look)
    TextView mLook;
    @BindView(R.id.img_close)
    ImageView mImgClose;
    @BindView(R.id.rl_share)
    RelativeLayout mRlShare;
    private int mId;
    public ArrayList<PathPartIcularsBean.ResultBean.ReviewsBean> mList;
    private MainHomeReviewsRlvAdapter mAdapter;
    private String mPhoto;
    private String mCardURL;
    private String mShareImageWechat;
    private String mShareURL;
    private int mBanmiID;

    @Override
    protected PathPresenter initPresenter() {
        return new PathPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_path_particulars;
    }

    @Override
    protected void iniView() {
        mFaContainer.setVisibility(View.GONE);
        showLoading();
        StatusBarUtil.setLightMode(this);
        mId = (int) SpUtil.getParam(Constants.PATH_ID, 0);


        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new MainHomeReviewsRlvAdapter(this, mList);
        mRlv.setAdapter(mAdapter);
    }


    @Override
    protected void iniData() {
        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        mpresenter.getPathData(mId, token);
    }

    @Override
    public void setData(PathPartIcularsBean bean) {
        if (bean.getCode() == 0) {
            hideLoading();
            mFaContainer.setVisibility(View.VISIBLE);
            PathPartIcularsBean.ResultBean.BanmiBean banmi = bean.getResult().getBanmi();
            mPhoto = banmi.getPhoto();
            Glide.with(this).load(banmi.getPhoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mBanmiImg);
            mTvBanmiName.setText(banmi.getName());
            mTvBanmiWork.setText(banmi.getOccupation());
            mTvBanmiCity.setText(banmi.getLocation());
            mTvContent.setText(banmi.getIntroduction());
            PathPartIcularsBean.ResultBean.RouteBean route = bean.getResult().getRoute();
            mTvCity.setText(route.getCity());
            mTvTitle.setText(route.getTitle());
            mTvIntro.setText(route.getIntro());
            mBanmiID = route.getBanmiID();

            mCardURL = route.getCardURL();
            mShareImageWechat = route.getShareImageWechat();
            mShareURL = route.getShareURL();

            Glide.with(this).load(route.getCardURL()).into(mImgBackground);
            if (bean.getResult().getRoute().isIsCollected()) {
                mImgCollect.setImageResource(R.drawable.collect_highlight);
                mTvCollect.setText(UIUtils.getString(R.string.yew_collect));
                SpUtil.setParam(Constants.COLLECT, true);
            } else {
                mImgCollect.setImageResource(R.drawable.collect_default);
                mTvCollect.setText(UIUtils.getString(R.string.collect_not));
                SpUtil.setParam(Constants.COLLECT, false);
            }
            mAdapter.addData(bean);

            mLook.setText("查看全部" + bean.getResult().getReviewsCount() + "条评价");
            mLook.setTextColor(UIUtils.getColor(R.color.c_FA6A13));
            List<PathPartIcularsBean.ResultBean.ReviewsBean> reviews = bean.getResult().getReviews();
            for (int i = 0; i < reviews.size(); i++) {
                List<?> list = reviews.get(i).getImages();
                if (list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        Log.d(TAG, "setData: " + list.get(j));
                    }
                }
            }
        }
    }

    @Override
    public void setCollectData(HomeCollectBean bean) {
        if (bean.getCode() == 0) {
            mImgCollect.setImageResource(R.drawable.collect_highlight);
            mTvCollect.setText(UIUtils.getString(R.string.yew_collect));
            ToastUtil.showShort(bean.getDesc());
            SpUtil.setParam(Constants.COLLECT, true);
        }
    }

    @Override
    public void setUnCollectData(HomeCollectBean bean) {
        if (bean.getCode() == 0) {
            mImgCollect.setImageResource(R.drawable.collect_default);
            mTvCollect.setText(UIUtils.getString(R.string.collect_not));
            ToastUtil.showShort(bean.getDesc());
            SpUtil.setParam(Constants.COLLECT, false);
        }
    }

    @Override
    protected void iniListener() {
        mRlCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = (String) SpUtil.getParam(Constants.TOKEN, "");
                Log.d(TAG, "onClick: " + token);

                boolean param = (boolean) SpUtil.getParam(Constants.COLLECT, true);
                if (param) {
                    mpresenter.setUnCollectData(mId, token);
                } else {
                    mpresenter.setCollectData(mId, token);
                }

            }
        });
    }

    @OnClick(R.id.img_close)
    public void onClick() {
        finish();
    }

    /**
     * 第三方分享
     */
    @OnClick(R.id.rl_share)
    public void onClick2() {

        UMWeb umWeb = new UMWeb(mShareURL);
        UMImage umImage = new UMImage(this, mShareImageWechat);
        umImage.compressStyle = UMImage.CompressStyle.SCALE;
        umWeb.setThumb(umImage);
        umWeb.setTitle(mTvTitle.getText().toString());
        umWeb.setDescription(mTvIntro.getText().toString());

        new ShareAction(PathParticularsActivity.this)
                .withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            toastShort("分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            toastShort("分享取消了");
        }
    };

    @OnClick(R.id.look)
    public void onClick3() {
        startActivity(new Intent(this, AllReviewsActivity.class));
    }

    public void startAct(AndroidJs androidJs) {
        finish();
    }



    @OnClick(R.id.banmi_img)
    public void onClick4() {
        SpUtil.setParam(Constants.BANMI_ID,mBanmiID);
        startActivity(new Intent(this,BanmiParticularsActivity.class));
    }
}
