package com.zl.dappore.voiceroom.adapter;


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
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.voiceroom.model.VoiceClient;


/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceClientRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceClient> {


    @Bind(R.id.iv_decorate_voice_client)
    ImageView ivDecorateVoiceClient;
    @Bind(R.id.iv_logo_voice_client)
    ImageView ivLogoVoiceClient;
    @Bind(R.id.iv_animation_voice_client)
    ImageView ivAnimationVoiceClient;
    @Bind(R.id.rl_item_voice_client)
    RelativeLayout rlItemVoiceClient;
    @Bind(R.id.tv_name_voice_client)
    TextView tvNameVoiceClient;
    private VoiceClient data;

    public VoiceClientRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_voice_client;
    }

    @Override
    protected void onBindItemData(VoiceClient data, int position, int totalCount) {
        this.data = data;
        QsHelper.getInstance().getImageHelper().createRequest().load(R.mipmap.ic_launcher).circleCrop().into(ivLogoVoiceClient);
        tvNameVoiceClient.setText(position + "分");

    }


    @OnClick({R.id.iv_animation_voice_client, R.id.rl_item_voice_client})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_animation_voice_client:
                break;
            case R.id.rl_item_voice_client:
                break;
        }
    }
}