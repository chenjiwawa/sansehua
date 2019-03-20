package com.zl.dappore.voiceroom.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.listener.OnVoiceClientListener;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;


/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceClientGridRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceRole> {

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
    private VoiceRole data;
    OnVoiceClientListener onVoiceClientListener;

    public VoiceClientGridRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, OnVoiceClientListener onVoiceClientListener) {
        super(inflater, parent);
        this.onVoiceClientListener = onVoiceClientListener;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_voice_client_grid;
    }

    @Override
    protected void onBindItemData(VoiceRole data, int position, int totalCount) {
        this.data = data;
        L.i(initTag(), " onBindItemData " + data);

        if (!TextUtils.isEmpty(data.logo)) {
            QsHelper.getInstance().getImageHelper().createRequest().load(data.logo).circleCrop().into(ivLogoVoiceClient);
        }
        tvNameVoiceClient.setText(data.name + "");

        rlItemVoiceClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVoiceClientListener != null) {
                    onVoiceClientListener.onItemSelect(data, position, totalCount);
                }
            }
        });
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