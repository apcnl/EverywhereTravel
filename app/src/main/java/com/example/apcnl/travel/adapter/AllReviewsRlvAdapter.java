package com.example.apcnl.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.AllReviewsActivity;
import com.example.apcnl.travel.bean.AllReviewsBean;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/13.
 */

public class AllReviewsRlvAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<AllReviewsBean.ResultBean.ReviewsBean> mList;

    public AllReviewsRlvAdapter(Context context, ArrayList<AllReviewsBean.ResultBean.ReviewsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_all_reviews, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        AllReviewsBean.ResultBean.ReviewsBean reviewsBean = mList.get(position);
        Glide.with(mContext).load(reviewsBean.getUserPhoto()).apply(RequestOptions.circleCropTransform())
                .into(holder1.img);
        holder1.name.setText(reviewsBean.getUserName());
        holder1.time.setText(reviewsBean.getCreatedAt());
        holder1.content.setText(reviewsBean.getContent());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(AllReviewsBean bean) {
        this.mList.addAll(bean.getResult().getReviews());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name;
        private TextView time;
        private TextView content;
        private RecyclerView rlv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.user_img);
            name = itemView.findViewById(R.id.user_name);
            time = itemView.findViewById(R.id.user_time);
            rlv = itemView.findViewById(R.id.rlv);
            content = itemView.findViewById(R.id.user_content);
        }
    }
}
