package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.ThemeActivity;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.bean.ThemeBean;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ThemeRlvAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<ThemeBean.ResultBean.BundlesBean> mList;
    private onItemClickListener mClickListener;

    public ThemeRlvAdapter(Context context, ArrayList<ThemeBean.ResultBean.BundlesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_everywhere_article_two, null, false);
        return new Article2ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Article2ViewHolder holder1 = (Article2ViewHolder) holder;
        ThemeBean.ResultBean.BundlesBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getCardURL()).into(holder1.img_background);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null){
                    mClickListener.clickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(ThemeBean bean) {
        this.mList.addAll(bean.getResult().getBundles());
        notifyDataSetChanged();
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
}
