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
import com.zl.dappore.voiceroom.adapter.TypeGridRecyclerAdapterItem;
import com.zl.dappore.voiceroom.model.voiceroomsetting.TypeList;
import com.zl.dappore.voiceroom.presenter.TypeGridPresenter;


public class TypeGridFragment extends QsRecyclerFragment<TypeGridPresenter, TypeList.Type> implements TypeGridRecyclerAdapterItem.ItemListener<TypeList.Type> {

    private ItemListener itemListener;

    public static TypeGridFragment getInstance(Bundle bundle) {
        TypeGridFragment fragment = new TypeGridFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
        showContentView();
    }


    public void requestData(String token) {
        getPresenter().requestData(token);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new TypeGridRecyclerAdapterItem(mInflater, parent);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    @Override
    public void onItemClick(TypeList.Type data, int position, int preposition, int totalCount) {
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

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener<T> {
        void onItemSelect(T data);
    }
}