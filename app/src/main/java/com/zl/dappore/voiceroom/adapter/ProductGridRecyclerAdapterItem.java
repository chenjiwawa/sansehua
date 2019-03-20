package com.zl.dappore.voiceroom.adapter;


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
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.event.VoiceRoomProductEvent;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.voiceroom.model.voicerole.ProductList;

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

    public ProductGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
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
                QsHelper.getInstance().eventPost(new VoiceRoomProductEvent.OnChoiceEvent(VoiceRoomProductEvent.OnChoiceEvent.State.SELECT, data));
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