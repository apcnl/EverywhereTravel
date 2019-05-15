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

import java.util.List;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiPartDynamicImgRlvAdapter extends RecyclerView.Adapter
        implements View.OnClickListener{


    private Context mContext;
    private List<?> mImages;
    private onItemImgClickListener mListener;

    public  BanmiPartDynamicImgRlvAdapter(Context context, List<?> images) {

        mContext = context;
        mImages = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_banmi_dongtai_img, null, false);
        inflate.setOnClickListener(this);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder2 = (ViewHolder) holder;
        Glide.with(mContext).load(mImages.get(position)).into(holder2.img);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.clickListener(v,(int)v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

    public interface onItemImgClickListener{
        void clickListener(View v,int position);
    }

    public void setonItemImgClickListener(onItemImgClickListener listener){
        this.mListener = listener;
    }
}
