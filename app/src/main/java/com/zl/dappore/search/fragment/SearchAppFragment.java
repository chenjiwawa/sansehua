package com.zl.dappore.search.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.search.adapter.SearchAppRecyclerAdapterItem;
import com.zl.dappore.search.model.SearchConstants;
import com.zl.dappore.search.presenter.SearchAppPresenter;


public class SearchAppFragment extends QsRecyclerFragment<SearchAppPresenter, App> {

    private String hotWord = "";

    public static SearchAppFragment getInstance(Bundle bundle) {
        SearchAppFragment fragment = new SearchAppFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        hotWord = bundle.getString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD);

        requstSearchAppList(false);
    }


    public void requstSearchAppList(boolean isLoadingMore) {
        getPresenter().requestAppListByHotWord(isLoadingMore, hotWord);
    }
//
//    @Override
//    public void onRefresh() {
//        requstSearchAppList(false);
//    }
//
//    @Override
//    public void onLoad() {
//        requstSearchAppList(true);
//    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new SearchAppRecyclerAdapterItem(mInflater, parent, false);
    }
}