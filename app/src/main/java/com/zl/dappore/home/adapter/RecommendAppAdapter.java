package com.zl.dappore.home.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.utils.CommonUtils;

import java.util.List;

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {

    private List<App> apps;


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_recommend_app;

        public ViewHolder(View view) {
            super(view);
            iv_recommend_app = (ImageView) view.findViewById(R.id.iv_recommend_app);
        }
    }

    public RecommendAppAdapter(List<App> apps) {
        this.apps = apps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommend_app, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QsHelper.getInstance().getImageHelper().createRequest().load(apps.get(position).logoUrl).roundedCorners(CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10)).into(holder.iv_recommend_app);
        holder.iv_recommend_app.setOnClickListener(new View.OnClickListener() {
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