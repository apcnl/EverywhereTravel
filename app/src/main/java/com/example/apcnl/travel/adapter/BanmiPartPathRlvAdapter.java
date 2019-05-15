package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.bean.BanmiParticularsPathBean;
import com.example.apcnl.travel.bean.EveryWhereBean;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiPartPathRlvAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private ArrayList<BanmiParticularsPathBean.ResultBean.RoutesBean> mList;

    public BanmiPartPathRlvAdapter(Context context, ArrayList<BanmiParticularsPathBean.ResultBean.RoutesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_everywhere_article, null, false);
        return new ArticleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleViewHolder holder1 = (ArticleViewHolder) holder;
        BanmiParticularsPathBean.ResultBean.RoutesBean bean = mList.get(position);
        holder1.tv_city.setText(bean.getCity());
        holder1.tv_title.setText(bean.getTitle());
        holder1.tv_intro.setText(bean.getIntro());
        holder1.tv_purchasedTimes.setText(bean.getPriceInCents()+"人购买");
        Glide.with(mContext).load(bean.getCardURL()).into(holder1.img_background);
        boolean isPurchased = bean.isIsPurchased();
        if (isPurchased){
            holder1.btn_isPurchased.setBackgroundResource(R.drawable.bg_btn_ea3_d15);
            holder1.btn_isPurchased.setText("已购买");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(BanmiParticularsPathBean bean) {
        this.mList.addAll(bean.getResult().getRoutes());
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
}
