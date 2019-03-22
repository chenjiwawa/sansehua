package com.tricolorflower.heartbeat.blacklist.fragment;

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
import com.tricolorflower.heartbeat.blacklist.adapter.BlackRecyclerAdapterItem;
import com.tricolorflower.heartbeat.blacklist.model.BlackListConstants;
import com.tricolorflower.heartbeat.blacklist.presenter.BlackListPresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;


public class BlackListFragment extends QsPullRecyclerFragment<BlackListPresenter, VoiceRoleList.VoiceRole> {

    String app_taxon_id;
    int type = 0;

    public static Fragment getInstance(Bundle extras) {
        BlackListFragment fragment = new BlackListFragment();
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
        type = arguments.getInt(BlackListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, 0);
        app_taxon_id = arguments.getString(BlackListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_APP_TAXON_ID);
        L.i(initTag(), " type " + type);

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
        return new BlackRecyclerAdapterItem(mInflater, parent);
    }

    private void requestData(boolean isLoadingMore) {
    }

    @Override
    public int emptyLayoutId() {
        return R.layout.empty_layout_black_list;
    }
}