package com.tricolorflower.heartbeat.appdetail.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.AppDetailActivity;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppDetailConstants;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;

import java.util.List;
public class SimilarAppAdapter extends RecyclerView.Adapter<SimilarAppAdapter.ViewHolder> {

    private List<App> apps;

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_item_app_similar;
        ImageView iv_img_app_similar;
        TextView tv_title_app_similar;

        public ViewHolder(View view){
            super(view);
            ll_item_app_similar = (LinearLayout) view.findViewById(R.id.ll_item_app_similar);
            iv_img_app_similar = (ImageView) view.findViewById(R.id.iv_img_app_similar);
            tv_title_app_similar = (TextView) view.findViewById(R.id.tv_title_app_similar);
        }
    }

    public SimilarAppAdapter(List<App> apps){
        this.apps = apps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app_similar,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QsHelper.getInstance().getImageHelper().createRequest().load(apps.get(position).logoUrl).roundedCorners(CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10)).into(holder.iv_img_app_similar);
        holder.tv_title_app_similar.setText(apps.get(position).name);
        holder.ll_item_app_similar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, apps.get(position).id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


}