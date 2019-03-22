package com.tricolorflower.heartbeat.search.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.search.adapter.SearchVideoRecyclerAdapterItem;
import com.tricolorflower.heartbeat.search.model.SearchConstants;
import com.tricolorflower.heartbeat.search.presenter.SearchVideoPresenter;
import com.tricolorflower.heartbeat.videodetail.model.Video;


public class SearchVideoFragment extends QsRecyclerFragment<SearchVideoPresenter, Video> {

    private String hotWord = "";

    public static SearchVideoFragment getInstance(Bundle bundle) {
        SearchVideoFragment fragment = new SearchVideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_searchvideo;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        hotWord = bundle.getString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD);

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 2));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
        requstSearchVideoList(false);
    }


    public void requstSearchVideoList(boolean isLoadingMore) {
        getPresenter().requestVideoListByHotWord(isLoadingMore, hotWord);
    }

//    @Override
//    public void onRefresh() {
//        requstSearchVideoList(false);
//    }
//
//    @Override
//    public void onLoad() {
//        requstSearchVideoList(true);
//    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new SearchVideoRecyclerAdapterItem(mInflater, parent);
    }
}