package com.tricolorflower.heartbeat.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.common.listener.ItemSelectListener;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.voiceroom.adapter.TypeGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.TypeList;
import com.tricolorflower.heartbeat.voiceroom.presenter.TypeGridPresenter;


public class TypeGridFragment extends QsRecyclerFragment<TypeGridPresenter, TypeList.Type> implements ItemSingleSelectListener<TypeList.Type> {

    TypeList.Type data;
    private ItemSelectListener itemListener;

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
        return new TypeGridRecyclerAdapterItem(mInflater, parent, this);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    @Override
    public void onItemClick(TypeList.Type data, int preposition, int position, int totalCount) {
        if (data == null)
            return;

        if (preposition == position) {
            notifyItemChanged(position, !getData().get(position).isSelect);
        } else {
            notifyItemChanged(preposition, false);
            notifyItemChanged(position, true);
        }

        if (getCurrentItem(position).isSelect) {
            if (itemListener != null) {
                itemListener.onItemSelect(getCurrentItem(position));
            }
        } else {
            if (itemListener != null) {
                itemListener.onItemSelect(null);
            }
        }
    }

    public void notifyItemChanged(int pos, boolean isSelect) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).isSelect = isSelect;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public TypeList.Type getCurrentItem(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            return getData().get(pos);
        }

        return new TypeList.Type();
    }

    public void setItemListener(ItemSelectListener itemListener) {
        this.itemListener = itemListener;
    }

}