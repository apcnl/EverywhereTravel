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
import com.example.apcnl.travel.bean.MeCollectBean;

import java.util.ArrayList;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeCollectRlvAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<MeCollectBean.ResultBean.CollectedRoutesBean> mList;

    public MeCollectRlvAdapter(Context context, ArrayList<MeCollectBean.ResultBean.CollectedRoutesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_me_collect, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1  = (ViewHolder) holder;
        MeCollectBean.ResultBean.CollectedRoutesBean bean = mList.get(position);
        holder1.title.setText(bean.getTitle());
        holder1.content.setText(bean.getIntro());

        Glide.with(mContext).load(bean.getCardURL())
                .into(holder1.img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(MeCollectBean bean) {
        this.mList.addAll(bean.getResult().getCollectedRoutes());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title;
        private TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}
