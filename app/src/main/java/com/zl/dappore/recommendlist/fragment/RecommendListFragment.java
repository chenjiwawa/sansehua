package com.zl.dappore.recommendlist.fragment;

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
import com.zl.dappore.recommendlist.adapter.RecommendListRecyclerAdapterItem;
import com.zl.dappore.recommendlist.model.RecommendListConstants;
import com.zl.dappore.recommendlist.presenter.RecommendListPresenter;


public class RecommendListFragment extends QsPullRecyclerFragment<RecommendListPresenter, App> {

    String app_taxon_id;
    int type = 0;

    public static Fragment getInstance(Bundle extras) {
        RecommendListFragment fragment = new RecommendListFragment();
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
        type = arguments.getInt(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, 0);
        app_taxon_id = arguments.getString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_APP_TAXON_ID);
        L.i(initTag(), " type " + type);

        requstAppList(false);

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
        requstAppList(false);
    }

    @Override
    public void onLoad() {
        requstAppList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new RecommendListRecyclerAdapterItem(mInflater, parent);
    }

    private void requstAppList(boolean isLoadingMore) {
        switch (type) {
            case RecommendListConstants.TYPE_NEW:
                getPresenter().requestAppListByNew(isLoadingMore, "1");
                break;
            case RecommendListConstants.TYPE_SIMILAR:
                getPresenter().requestAppListBySimilar(isLoadingMore, app_taxon_id, "1");
                break;
            case RecommendListConstants.TYPE_HOT:
                getPresenter().requestAppListByHot(isLoadingMore, "", "1");
                break;
        }
    }
}