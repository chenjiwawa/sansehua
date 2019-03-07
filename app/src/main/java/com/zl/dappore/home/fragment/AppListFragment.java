package com.zl.dappore.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.home.adapter.AppRecyclerAdapterItem;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.home.presenter.AppListPresenter;



public class AppListFragment extends QsPullRecyclerFragment<AppListPresenter, App> {

    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private String sortType = "";

    public static AppListFragment getInstance(Bundle bundle) {
        AppListFragment fragment = new AppListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        sortType = getString(R.string.sort_type_new);
        Bundle bundle = getArguments();
        categoryType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_ID, HomeConstants.CATEGORY_TYPE_DEFAULT);
        sortType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE, getString(R.string.sort_type_new));

        requstAppList(false);
//        getPresenter().requestRankAppList(false, categoryType, sortType);
    }


    private void requstAppList(boolean isLoadingMore) {
        if (sortType.equals(getString(R.string.sort_type_new))) {
            getPresenter().requestAppListByNew(isLoadingMore, categoryType, "1");
        } else if (sortType.equals(getString(R.string.sort_type_hot))) {
            getPresenter().requestAppListByHot(isLoadingMore, categoryType, "1");
        } else {
            getPresenter().requestAppListByScore(isLoadingMore, categoryType, "1");
        }
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
        return new AppRecyclerAdapterItem(mInflater, parent, false);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

}