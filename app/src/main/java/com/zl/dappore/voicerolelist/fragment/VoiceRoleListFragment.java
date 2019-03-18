package com.zl.dappore.voicerolelist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.voicerolelist.adapter.OnlineUserRecyclerAdapterItem;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voicerolelist.model.VoiceRoleListConstants;
import com.zl.dappore.voicerolelist.presenter.VoiceRoleListPresenter;


public class VoiceRoleListFragment extends QsPullRecyclerFragment<VoiceRoleListPresenter, VoiceRoleList.VoiceRole> {

    String app_taxon_id;
    int type = 0;

    public static Fragment getInstance(Bundle extras) {
        VoiceRoleListFragment fragment = new VoiceRoleListFragment();
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
        type = arguments.getInt(VoiceRoleListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, 0);
        app_taxon_id = arguments.getString(VoiceRoleListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_APP_TAXON_ID);
        L.i(initTag(), " type " + type);

        requestVoiceRoleList(false);
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
        requestVoiceRoleList(false);
    }

    @Override
    public void onLoad() {
        requestVoiceRoleList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new OnlineUserRecyclerAdapterItem(mInflater, parent, false);
    }

    private void requestVoiceRoleList(boolean isLoadingMore){
        getPresenter().requestVoiceRoleList(isLoadingMore,"","");
    }

}