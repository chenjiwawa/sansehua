package com.tricolorflower.heartbeat.appdetail.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.web.WebViewActivity;
import com.tricolorflower.heartbeat.web.model.WebConstants;

import java.util.List;

public class ContractRecyclerAdapter extends RecyclerView.Adapter<ContractRecyclerAdapter.ViewHolder> {

    private App data;
    private List<App.Contract> contracts;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        TextView tv_contract_address_appdetail;
        TextView tv_contract_open_appdetail;
        TextView tv_contract_safe_appdetail;

        public ViewHolder(View view) {
            super(view);
            item = view;
            tv_contract_address_appdetail = (TextView) view.findViewById(R.id.tv_contract_address_appdetail);
            tv_contract_open_appdetail = (TextView) view.findViewById(R.id.tv_contract_open_appdetail);
            tv_contract_safe_appdetail = (TextView) view.findViewById(R.id.tv_contract_safe_appdetail);
        }
    }

    public ContractRecyclerAdapter(List<App.Contract> contracts, Context context, App data) {
        this.contracts = contracts;
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contract, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_contract_address_appdetail.setText(contracts.get(position).address);
        holder.tv_contract_address_appdetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        holder.tv_contract_address_appdetail.getPaint().setAntiAlias(true);//抗锯齿
        if (contracts.get(position).opened) {
            holder.tv_contract_open_appdetail.setText("已开源");
            Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_open);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_contract_open_appdetail.setCompoundDrawables(drawable, null, null, null);
        } else {
            holder.tv_contract_open_appdetail.setText("未开源");
            Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_notopen);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_contract_open_appdetail.setCompoundDrawables(drawable, null, null, null);
        }

/*        0 - 3 low；
        3 - 4 middle;
        4 - 5 high;*/
        Drawable drawable = null;
        if (0 <= contracts.get(position).safety && contracts.get(position).safety < 3) {
            drawable = context.getResources().getDrawable(R.mipmap.ic_safe_low);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else if (3 <= contracts.get(position).safety && contracts.get(position).safety < 4) {
            drawable = context.getResources().getDrawable(R.mipmap.ic_safe_middle);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else {
            drawable = context.getResources().getDrawable(R.mipmap.ic_safe_high);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        holder.tv_contract_safe_appdetail.setCompoundDrawables(drawable, null, null, null);
        holder.tv_contract_safe_appdetail.setText(contracts.get(position).safetyStr);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, data.name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, contracts.get(position).url);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contracts == null) {
            return 0;
        }

        return contracts.size();
    }
}