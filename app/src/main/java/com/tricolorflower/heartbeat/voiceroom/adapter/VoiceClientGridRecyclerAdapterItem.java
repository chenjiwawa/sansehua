package com.tricolorflower.heartbeat.voiceroom.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.opensource.svgaplayer.SVGAImageView;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.listener.OnVoiceClientListener;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import static com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole.ID_EMPTY;


/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceClientGridRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceRole> {

    @Bind(R.id.iv_decorate_voice_client)
    ImageView ivDecorateVoiceClient;
    @Bind(R.id.iv_logo_voice_client)
    ImageView ivLogoVoiceClient;
    @Bind(R.id.iv_animation_voice_client)
    SVGAImageView ivAnimationVoiceClient;
    @Bind(R.id.iv_mute_voice_client)
    ImageView ivMuteVoiceClient;
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

        if (data.isIdEmpty()) {
            //空麦位
            if (data.isVoiceEnable()) {
                ivLogoVoiceClient.setBackgroundResource(R.mipmap.ic_add_voice_client);
            } else {
                ivLogoVoiceClient.setBackgroundResource(R.mipmap.ic_close_voice_client);
            }
            tvNameVoiceClient.setVisibility(View.INVISIBLE);
            ivMuteVoiceClient.setVisibility(View.GONE);
        } else {
            //有人麦位
            if (!TextUtils.isEmpty(data.logo)) {
                QsHelper.getInstance().getImageHelper().createRequest().load(data.logo).circleCrop().into(ivLogoVoiceClient);
            }
            tvNameVoiceClient.setVisibility(View.VISIBLE);
            tvNameVoiceClient.setText(data.name + "");
            if (data.isVoiceMute()) {
                ivMuteVoiceClient.setVisibility(View.VISIBLE);
            } else {
                ivMuteVoiceClient.setVisibility(View.GONE);
            }
        }


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