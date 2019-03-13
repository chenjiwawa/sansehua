package com.zl.dappore.voiceroom.fragment.roomrole.product;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.common.widget.itemdecoration.DividerGridItemDecoration;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.voiceroom.adapter.ProductGridRecyclerAdapterItem;
import com.zl.dappore.voiceroom.presenter.ProductGridPresenter;


public class ProductGridFragment extends QsRecyclerFragment<ProductGridPresenter, App> {

    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private String sortType = "";

    public static ProductGridFragment getInstance(Bundle bundle) {
        ProductGridFragment fragment = new ProductGridFragment();
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
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));

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
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new ProductGridRecyclerAdapterItem(mInflater, parent, false);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

}