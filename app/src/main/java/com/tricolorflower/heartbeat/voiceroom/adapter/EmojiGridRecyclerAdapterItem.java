package com.tricolorflower.heartbeat.voiceroom.adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.AppDetailActivity;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppDetailConstants;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;

/**
 * Created by zhang on 2017/3/17.
 */
public class EmojiGridRecyclerAdapterItem extends QsRecycleAdapterItem<App> {

    @Bind(R.id.iv_img_emoji_grid)
    ImageView ivImgEmojiGrid;
    @Bind(R.id.tv_title_emoji_grid)
    TextView tvTitleEmojiGrid;

    private App appDetail;

    public EmojiGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_emoji_grid;
    }

    @Override
    protected void onBindItemData(App data, int position, int totalCount) {
        this.appDetail = data;

        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgEmojiGrid);
        tvTitleEmojiGrid.setText(data.name);

    }

    @OnClick({R.id.rl_item_emoji_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_emoji_grid:
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appDetail.id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
        }
    }
}