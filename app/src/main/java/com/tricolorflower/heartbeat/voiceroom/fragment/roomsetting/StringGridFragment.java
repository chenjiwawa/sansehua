package com.tricolorflower.heartbeat.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.voiceroom.adapter.LabelGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.adapter.StringGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.presenter.StringGridPresenter;


public class StringGridFragment extends QsRecyclerFragment<StringGridPresenter, App> implements LabelGridRecyclerAdapterItem.ItemListener<App> {

    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private String sortType = "";

    public static StringGridFragment getInstance(Bundle bundle) {
        StringGridFragment fragment = new StringGridFragment();
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
        return new StringGridRecyclerAdapterItem(mInflater, parent);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    @Override
    public void onItemClick(App data, int position, int preposition, int totalCount) {
        notifyItemChanged(preposition);
        notifyItemChanged(position);
    }

    public void notifyItemChanged(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).liked = true;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public interface ItemListener<T> {
        void onItemSelect(T data);
    }
}