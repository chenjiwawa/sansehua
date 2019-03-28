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
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.TypeList;

/**
 * Created by zhang on 2017/3/17.
 */
public class TypeGridRecyclerAdapterItem extends QsRecycleAdapterItem<TypeList.Type> {

    @Bind(R.id.rl_item_type_grid)
    RelativeLayout rl_item_type_grid;
    @Bind(R.id.tv_title_type_grid)
    TextView tvTitleGrid;

    private TypeList.Type data;
    private ItemSingleSelectListener itemListener;
    private static int preposition = 0;

    public TypeGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, ItemSingleSelectListener itemListener) {
        super(inflater, parent);
        this.itemListener = itemListener;
        preposition = 0;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_type_grid;
    }

    @Override
    protected void onBindItemData(TypeList.Type data, int position, int totalCount) {
        this.data = data;

        tvTitleGrid.setText(data.name);

        rl_item_type_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClick(data, preposition, position, totalCount);
                }
                preposition = position;
            }
        });
    }

    @OnClick({R.id.tv_title_type_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_title_type_grid:
                break;
        }
    }

}