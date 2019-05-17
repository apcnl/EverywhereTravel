package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.net.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/6.
 */

public class MainEveryWhereRlvAdapter extends RecyclerView.Adapter {

    private static final String TAG = "MainEveryWhereRlvAdapter";
    private Context mContext;
    private ArrayList<EveryWhereBean.ResultBean.RoutesBean> mArticleList;
    private onItemClickListener mClickListener;
    private int mNewsPosition;
    private String mType;
    private onItemBundleClickListener mClickBundleListener;

    public MainEveryWhereRlvAdapter(Context context, ArrayList<EveryWhereBean.ResultBean.RoutesBean> articleList) {

        mContext = context;
        mArticleList = articleList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_everywhere_article, null, false);
                return new ArticleViewHolder(inflate);
            }else {
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_everywhere_article_two, null, false);
                return new Article2ViewHolder(inflate);
            }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
         if (itemViewType == 1){
            mNewsPosition = position;
            ArticleViewHolder holder1 = (ArticleViewHolder) holder;
            EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(mNewsPosition);
            holder1.tv_city.setText(bean.getCity());
            holder1.tv_title.setText(bean.getTitle());
            holder1.tv_intro.setText(bean.getIntro());
            holder1.tv_purchasedTimes.setText(bean.getPurchasedTimes()+"人购买");
            Glide.with(mContext).load(bean.getCardURL()).into(holder1.img_background);
            boolean isPurchased = bean.isIsPurchased();
            if (isPurchased){
                holder1.btn_isPurchased.setBackgroundResource(R.drawable.bg_btn_ea3_d15);
                holder1.btn_isPurchased.setText("已购买");
            }
            final int finalNewsPosition = mNewsPosition;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null){
                        mClickListener.clickListener(finalNewsPosition);
                    }
                }
            });
        }
        else if (itemViewType == 2 ){
            int newsPosition = position;
            Article2ViewHolder holder1 = (Article2ViewHolder) holder;
            EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(newsPosition);
            Glide.with(mContext).load(bean.getCardURL()).into(holder1.img_background);
            final int finalNewsPosition = newsPosition;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickBundleListener != null){
                        mClickBundleListener.clickListener(finalNewsPosition);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
            return mArticleList.size();
    }

    @Override
    public int getItemViewType(int position) {

        int index = 0;
            int newsposition = position;
                if (mArticleList.get(newsposition).getType().equals(Constants.ROUTE)){
                    index =  1;
                }else {
                    index =  2;
                }

        return index;
    }

    public void addData(EveryWhereBean bean) {
        mArticleList.addAll(bean.getResult().getRoutes());
        notifyDataSetChanged();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_background;
        private TextView tv_title;
        private TextView tv_city;
        private TextView tv_intro;
        private TextView tv_purchasedTimes;
        private Button btn_isPurchased;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            img_background = itemView.findViewById(R.id.img_background);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_intro = itemView.findViewById(R.id.tv_intro);
            tv_purchasedTimes = itemView.findViewById(R.id.tv_purchasedTimes);
            btn_isPurchased = itemView.findViewById(R.id.btn_isPurchased);
        }
    }

    class Article2ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_background;

        public Article2ViewHolder(View itemView) {
            super(itemView);
            img_background = itemView.findViewById(R.id.img_background);
        }
    }

    public interface onItemClickListener{
        void clickListener(int position);
    }

    public void setonItemClickListener(onItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    public interface onItemBundleClickListener{
        void clickListener(int position);
    }

    public void setonItemBundleClickListener(onItemBundleClickListener clickListener){
        this.mClickBundleListener = clickListener;
    }
}
