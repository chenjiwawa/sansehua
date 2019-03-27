package com.tricolorflower.heartbeat.voiceroom.adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.AppDetailActivity;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppDetailConstants;
import com.tricolorflower.heartbeat.common.event.VoiceRoomProductEvent;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.LabelList;

/**
 * Created by zhang on 2017/3/17.
 */
public class ProductGridRecyclerAdapterItem extends QsRecycleAdapterItem<ProductList.Product> {

    @Bind(R.id.rl_item_product_grid)
    RelativeLayout item;
    @Bind(R.id.iv_img_product_grid)
    ImageView ivImgProductGrid;
    @Bind(R.id.tv_title_product_grid)
    TextView tvTitleProductGrid;
    @Bind(R.id.tv_des_product_grid)
    TextView tvDesProductGrid;

    private ProductList.Product data;
    private ItemSingleSelectListener itemListener;
    private int preposition = 0;

    public ProductGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, ItemSingleSelectListener itemListener) {
        super(inflater, parent);
        this.itemListener = itemListener;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_product_grid;
    }

    @Override
    protected void onBindItemData(ProductList.Product data, int position, int totalCount) {
        this.data = data;

        QsHelper.getInstance().getImageHelper().createRequest().load(data.giftPic).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgProductGrid);
        tvTitleProductGrid.setText(data.giftName);
        tvDesProductGrid.setText(data.price);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClick(data, preposition, position, totalCount);
                }
                preposition = position;
            }
        });
    }

    @OnClick({R.id.rl_item_product_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_product_grid:
                break;
        }
    }
}