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
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;

/**
 * Created by zhang on 2017/3/17.
 */
public class EmojiGridRecyclerAdapterItem extends QsRecycleAdapterItem<EmojiList.Emoji> {

    @Bind(R.id.rl_item_emoji_grid)
    RelativeLayout item;
    @Bind(R.id.iv_img_emoji_grid)
    ImageView ivImgEmojiGrid;
    @Bind(R.id.tv_title_emoji_grid)
    TextView tvTitleEmojiGrid;

    private EmojiList.Emoji data;

    public EmojiGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_emoji_grid;
    }

    @Override
    protected void onBindItemData(EmojiList.Emoji data, int position, int totalCount) {
        this.data = data;

        QsHelper.getInstance().getImageHelper().createRequest().load(data.emojiPic).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgEmojiGrid);
        tvTitleEmojiGrid.setText(data.emojiName);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QsHelper.getInstance().eventPost(new RoomRoleOperationEvent.OnEmojiEvent(data));
            }
        });
    }

    @OnClick({R.id.rl_item_emoji_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_emoji_grid:
                break;
        }
    }
}