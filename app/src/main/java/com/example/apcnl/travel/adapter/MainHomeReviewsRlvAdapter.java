package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.PathParticularsActivity;
import com.example.apcnl.travel.bean.PathPartIcularsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apcnl on 2019/5/10.
 */

public class MainHomeReviewsRlvAdapter extends RecyclerView.Adapter{

    private static final String TAG = "MainHomeReviewsRlvAdapter";
    private PathParticularsActivity mContext;
    private ArrayList<PathPartIcularsBean.ResultBean.ReviewsBean> mList;
    private List<?> mImages;


    public MainHomeReviewsRlvAdapter(PathParticularsActivity context, ArrayList<PathPartIcularsBean.ResultBean.ReviewsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (int i = 0; i < mList.size(); i++) {
            mImages = mList.get(i).getImages();
        }
        if (mImages.size() == 0){
            viewType = 0;

            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_reviews_one, null, false);
            return new NotImageViewHolder(inflate);
        }else {
            viewType = 1;
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_reviews_two, null, false);
            return new ImageViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
            if (itemViewType == 0){
                NotImageViewHolder holder1 = (NotImageViewHolder) holder;
                PathPartIcularsBean.ResultBean.ReviewsBean bean = mList.get(position);
                holder1.name.setText(bean.getUserName());
                holder1.content.setText(bean.getContent());
                holder1.time.setText(bean.getCreatedAt());
                Glide.with(mContext).load(bean.getUserPhoto())
                        .apply(RequestOptions.circleCropTransform())
                        .into(holder1.img);
            }else {
                ImageViewHolder holder1 = (ImageViewHolder) holder;
                PathPartIcularsBean.ResultBean.ReviewsBean bean = mList.get(position);
                holder1.name.setText(bean.getUserName());
                holder1.content.setText(bean.getContent());
                holder1.time.setText(bean.getCreatedAt());
                Glide.with(mContext).load(bean.getUserPhoto())
                        .apply(RequestOptions.circleCropTransform())
                        .into(holder1.img);
                final List<?> list = bean.getImages();
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder1.rlv.setLayoutManager(manager);
                holder1.rlv.setAdapter(new RecyclerView.Adapter() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_reviews_img, null, false);
                        return new ViewHolder(inflate);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                        ViewHolder holder2 = (ViewHolder) holder;
                        String o = (String) list.get(position);
                        Glide.with(mContext).load(o).into(holder2.img);
                    }

                    @Override
                    public int getItemCount() {
                        return list.size();
                    }

                    class ViewHolder extends RecyclerView.ViewHolder {

                        private ImageView img;

                        public ViewHolder(View itemView) {
                            super(itemView);
                            img = itemView.findViewById(R.id.img);
                        }
                    }
                });
                //Log.d(TAG, "onBindViewHolder: "+list.toString());
            }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(PathPartIcularsBean bean) {
        this.mList.addAll(bean.getResult().getReviews());
        notifyDataSetChanged();
    }

    class NotImageViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView content;
        private TextView time;
        private ImageView img;

        public NotImageViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.user_img);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.review_time);
            name = itemView.findViewById(R.id.user_name);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView content;
        private TextView time;
        private ImageView img;
        private RecyclerView rlv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.user_img);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.review_time);
            name = itemView.findViewById(R.id.user_name);
            rlv = itemView.findViewById(R.id.rlv_img);
        }
    }
}
