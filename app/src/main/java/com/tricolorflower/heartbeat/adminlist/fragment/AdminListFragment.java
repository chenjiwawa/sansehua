package com.tricolorflower.heartbeat.adminlist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.adminlist.adapter.AdminRecyclerAdapterItem;
import com.tricolorflower.heartbeat.adminlist.model.AdminListConstants;
import com.tricolorflower.heartbeat.adminlist.presenter.AdminListPresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;


public class AdminListFragment extends QsPullRecyclerFragment<AdminListPresenter, VoiceRoleList.VoiceRole> {

    String token;

    public static Fragment getInstance(Bundle extras) {
        AdminListFragment fragment = new AdminListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return super.initView(inflater);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        requestData(false);
        getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onRefresh() {
        requestData(false);
    }

    @Override
    public void onLoad() {
        requestData(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new AdminRecyclerAdapterItem(mInflater, parent);
    }

    private void requestData(boolean isLoadingMore) {
        getPresenter().requestAdminList(token);
    }

    @Override
    public int emptyLayoutId() {
        return R.layout.empty_layout_admin_list;
    }
}