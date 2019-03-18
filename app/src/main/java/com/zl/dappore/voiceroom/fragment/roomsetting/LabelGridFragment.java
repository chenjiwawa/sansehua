package com.zl.dappore.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.widget.itemdecoration.DividerGridItemDecoration;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.voiceroom.adapter.LabelGridRecyclerAdapterItem;
import com.zl.dappore.voiceroom.model.LabelList;
import com.zl.dappore.voiceroom.presenter.LabelGridPresenter;


public class LabelGridFragment extends QsRecyclerFragment<LabelGridPresenter, LabelList.Label> implements LabelGridRecyclerAdapterItem.ItemListener<LabelList.Label> {

    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private String sortType = "";
    private TypeGridFragment.ItemListener itemListener;

    public static LabelGridFragment getInstance(Bundle bundle) {
        LabelGridFragment fragment = new LabelGridFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        sortType = getString(R.string.sort_type_new);
        Bundle bundle = getArguments();
        categoryType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_ID, HomeConstants.CATEGORY_TYPE_DEFAULT);
        sortType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE, getString(R.string.sort_type_new));

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));

        getPresenter().requestData("");
    }


    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new LabelGridRecyclerAdapterItem(mInflater, parent);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    @Override
    public void onItemClick(LabelList.Label data, int position, int preposition, int totalCount) {
        notifyItemChanged(preposition);
        notifyItemChanged(position);
        if (itemListener != null) {
            itemListener.onItemSelect(data);
        }
    }

    public void notifyItemChanged(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).isSelect = true;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public void setItemListener(TypeGridFragment.ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener<T> {
        void onItemSelect(T data);
    }
}