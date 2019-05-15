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
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.bean.HomeBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.util.SpUtil;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/6.
 */

public class MainHomeRlvAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<HomeBean.ResultBean.BanmiBean> mList;
    private FollowOnclickListener mFollowListener;
    private OnItemclickListener mItemListener;

    public MainHomeRlvAdapter(Context context, ArrayList<HomeBean.ResultBean.BanmiBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        final HomeBean.ResultBean.BanmiBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getPhoto()).into(holder1.img);
        holder1.tv_name.setText(bean.getName());
        holder1.tv_following.setText(bean.getFollowing()+"人关注");
        holder1.tv_location.setText(bean.getLocation());
        holder1.tv_occupation.setText(bean.getOccupation());
        if (bean.isIsFollowed()){
            holder1.img_collect.setImageResource(R.mipmap.follow);
        }else {
            holder1.img_collect.setImageResource(R.mipmap.follow_unselected);
        }
        holder1.img_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(position).isIsFollowed()){
                    if (mFollowListener != null){
                        mList.get(position).setIsFollowed(false);
                        holder1.img_collect.setImageResource(R.mipmap.follow_unselected);
                        mFollowListener.unclicklistener(position);
                    }
                }else {
                    if (mFollowListener != null){
                        mList.get(position).setIsFollowed(true);
                        holder1.img_collect.setImageResource(R.mipmap.follow);
                        mFollowListener.clicklistener(position);
                    }
                }

            }
        });
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null){
                    mItemListener.clicklistener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(HomeBean bean) {
        mList.addAll(bean.getResult().getBanmi());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private ImageView img_collect;
        private TextView tv_name;
        private TextView tv_following;
        private TextView tv_location;
        private TextView tv_occupation;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img_collect = itemView.findViewById(R.id.img_collect);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_following = itemView.findViewById(R.id.tv_following);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_occupation = itemView.findViewById(R.id.tv_occupation);
        }
    }

    public interface FollowOnclickListener{
        void clicklistener(int position);
        void unclicklistener(int position);
    }

    public void setFollowOnclickListener(FollowOnclickListener listener){
        this.mFollowListener = listener;
    }

    public interface OnItemclickListener{
        void clicklistener(int position);
    }

    public void setOnItemclickListener(OnItemclickListener listener){
        this.mItemListener = listener;
    }


}
