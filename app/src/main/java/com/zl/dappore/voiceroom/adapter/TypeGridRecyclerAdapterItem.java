package com.zl.dappore.voiceroom.adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.voiceroom.model.TypeList;

/**
 * Created by zhang on 2017/3/17.
 */
public class TypeGridRecyclerAdapterItem extends QsRecycleAdapterItem<TypeList.Type> {

    @Bind(R.id.rl_item_type_grid)
    RelativeLayout rl_item_type_grid;
    @Bind(R.id.tv_title_type_grid)
    TextView tvTitleGrid;

    private TypeList.Type data;
    private ItemListener itemListener;
    private int preposition = 0;

    public TypeGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
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
                    itemListener.onItemClick(data, position, preposition, totalCount);
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

    public  interface ItemListener<T> {
        void onItemClick(T data, int position, int preposition, int totalCount);
    }
}