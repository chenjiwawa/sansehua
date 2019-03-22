package com.tricolorflower.heartbeat.favorite.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.favorite.presenter.FavoriteVideoListPresenter;
import com.tricolorflower.heartbeat.search.adapter.SearchVideoRecyclerAdapterItem;
import com.tricolorflower.heartbeat.videodetail.model.Video;


public class FavoriteVideoListFragment extends QsPullRecyclerFragment<FavoriteVideoListPresenter, Video> {

    public static FavoriteVideoListFragment getInstance() {
        FavoriteVideoListFragment fragment = new FavoriteVideoListFragment();
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_searchvideo;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 2));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
        requstData(false);
    }


    public void requstData(boolean isLoadingMore) {
        getPresenter().requestFavoriteVideoList(isLoadingMore);
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
        return new SearchVideoRecyclerAdapterItem(mInflater, parent);
    }
}