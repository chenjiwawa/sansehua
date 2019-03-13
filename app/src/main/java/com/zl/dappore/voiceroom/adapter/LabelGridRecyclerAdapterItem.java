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

/**
 * Created by zhang on 2017/3/17.
 */
public class LabelGridRecyclerAdapterItem extends QsRecycleAdapterItem<App> {

    @Bind(R.id.rl_item_label_grid)
    RelativeLayout rl_item_label_grid;
    @Bind(R.id.tv_title_label_grid)
    TextView tvTitleLabelGrid;

    private App appDetail;
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
    protected void onBindItemData(App data, int position, int totalCount) {
        this.appDetail = data;

        tvTitleLabelGrid.setText(data.name);

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
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appDetail.id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
        }
    }

    public  interface ItemListener<T> {
        void onItemClick(T data, int position, int preposition, int totalCount);
    }
}