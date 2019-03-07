package com.zl.dappore.favorite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.favorite.presenter.FavoriteAppListPresenter;
import com.zl.dappore.home.adapter.AppRecyclerAdapterItem;


public class FavoriteAppListFragment extends QsPullRecyclerFragment<FavoriteAppListPresenter, App> {


    public static FavoriteAppListFragment getInstance() {
        FavoriteAppListFragment fragment = new FavoriteAppListFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        requstData(false);
    }


    public void requstData(boolean isLoadingMore) {
        getPresenter().requestFavoriteAppList(isLoadingMore);
    }

    @Override
    public void onRefresh() {
        requstData(false);
    }

    @Override
    public void onLoad() {
        requstData(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new AppRecyclerAdapterItem(mInflater, parent, false);
    }
}