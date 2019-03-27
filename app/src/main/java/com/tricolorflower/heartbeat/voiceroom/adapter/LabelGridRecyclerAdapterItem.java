package com.tricolorflower.heartbeat.voiceroom.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.LabelList;

/**
 * Created by zhang on 2017/3/17.
 */
public class LabelGridRecyclerAdapterItem extends QsRecycleAdapterItem<LabelList.Label> {

    @Bind(R.id.rl_item_label_grid)
    RelativeLayout rl_item_label_grid;
    @Bind(R.id.tv_title_label_grid)
    TextView tvTitleLabelGrid;

    private LabelList.Label data;
    private ItemSingleSelectListener itemListener;
    private int preposition = 0;

    public LabelGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, ItemSingleSelectListener itemListener) {
        super(inflater, parent);
        this.itemListener = itemListener;
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
                    itemListener.onItemClick(data, preposition, position, totalCount);
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
}