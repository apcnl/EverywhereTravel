package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.ViewPagerImgActivity;
import com.example.apcnl.travel.bean.BanmiParicularsBean;
import com.example.apcnl.travel.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiPartDongtaiRlvAdapter extends RecyclerView.Adapter{

    private Context mContext;
    public static ArrayList<BanmiParicularsBean.ResultBean.ActivitiesBean> mList;
    public BanmiPartDynamicImgRlvAdapter mAdapter;
    private onItemClickListener mItemListener;

    public BanmiPartDongtaiRlvAdapter(Context context, ArrayList<BanmiParicularsBean.ResultBean.ActivitiesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_banmi_dongtai, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        BanmiParicularsBean.ResultBean.ActivitiesBean bean = mList.get(position);
        holder1.time.setText(bean.getDate());
        String content = bean.getContent();
        holder1.content.setText(bean.getContent());
        holder1.likecount.setText(bean.getLikeCount()+"");
        holder1.commentcount.setText(bean.getReplyCount()+"");
        final List<?> images = bean.getImages();
        if (images.size()>0){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            holder1.rlv.setLayoutManager(layoutManager);
            mAdapter = new BanmiPartDynamicImgRlvAdapter(mContext,images);
            holder1.rlv.setAdapter(mAdapter);
            mAdapter.setonItemImgClickListener(new BanmiPartDynamicImgRlvAdapter.onItemImgClickListener() {
                @Override
                public void clickListener(View v, int position2) {
                    //ToastUtil.showShort(position+"");
                    Intent intent = new Intent(mContext, ViewPagerImgActivity.class);
                    intent.putExtra("position2",position2);
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }
            });
        }else {
            holder1.rlv.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(BanmiParicularsBean bean) {
        this.mList.addAll(bean.getResult().getActivities());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView title;
        private TextView content;
        private TextView likecount;
        private TextView commentcount;
        private RecyclerView rlv;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_time);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            likecount = itemView.findViewById(R.id.tv_like);
            commentcount = itemView.findViewById(R.id.tv_comment);
            rlv = itemView.findViewById(R.id.rlv);
        }
    }

//    public interface onItemImgClickListener{
//        void clickListener(int position);
//    }
//
//    public void setonItemImgClickListener(onItemImgClickListener listener){
//        this.mListener = listener;
//    }

    public interface onItemClickListener{
        void clickListener(int position);
    }

    public void setonItemClickListener(onItemClickListener listener){
        this.mItemListener = listener;
    }

}
