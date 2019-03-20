package com.zl.dappore.voiceroom.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.voiceroomsetting.LabelList;

/**
 * Created by zhang on 2017/3/17.
 */
public class LabelGridRecyclerAdapterItem extends QsRecycleAdapterItem<LabelList.Label> {

    @Bind(R.id.rl_item_label_grid)
    RelativeLayout rl_item_label_grid;
    @Bind(R.id.tv_title_label_grid)
    TextView tvTitleLabelGrid;

    private LabelList.Label data;
    private ItemListener itemListener;
    private int preposition = 0;

    public LabelGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_label_grid;
    }

    @Override
    protected void onBindItemData(LabelList.Label data, int position, int totalCount) {
        this.data = data;

        tvTitleLabelGrid.setText(data.tag_name);

        rl_item_label_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClick(data, position, preposition, totalCount);
                }
                preposition = position;
            }
        });
    }

    @OnClick({R.id.tv_title_label_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_title_label_grid:
                break;
        }
    }

    public  interface ItemListener<T> {
        void onItemClick(T data, int position, int preposition, int totalCount);
    }
}