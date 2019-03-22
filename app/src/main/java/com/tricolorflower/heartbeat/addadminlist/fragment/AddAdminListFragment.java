package com.tricolorflower.heartbeat.addadminlist.fragment;

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
import com.tricolorflower.heartbeat.addadminlist.adapter.AddAdminRecyclerAdapterItem;
import com.tricolorflower.heartbeat.addadminlist.model.AddAdminListConstants;
import com.tricolorflower.heartbeat.addadminlist.presenter.AddAdminListPresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;


public class AddAdminListFragment extends QsPullRecyclerFragment<AddAdminListPresenter, VoiceRoleList.VoiceRole> {

    String token;
    String namestr;

    public static Fragment getInstance(Bundle extras) {
        AddAdminListFragment fragment = new AddAdminListFragment();
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

        requestData(token, namestr);
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
        requestData(token, namestr);
    }

    @Override
    public void onLoad() {
        requestData(token, namestr);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new AddAdminRecyclerAdapterItem(mInflater, parent);
    }

    private void requestData(String token, String namestr) {
        getPresenter().searchAdmin(token, namestr);
    }

    @Override
    public int emptyLayoutId() {
        return R.layout.empty_layout_add_admin_list;
    }
}