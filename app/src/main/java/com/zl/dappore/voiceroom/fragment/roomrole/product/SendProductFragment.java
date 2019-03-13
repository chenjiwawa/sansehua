package com.zl.dappore.voiceroom.fragment.roomrole.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.VoiceClientGridFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.SendProductPresenter;

public class SendProductFragment extends QsFragment<SendProductPresenter> {

    @Bind(R.id.iv_decorate_voice_client_list)
    ImageView ivDecorateVoiceClientList;
    @Bind(R.id.iv_logo_voice_client_list)
    ImageView ivLogoVoiceClientList;
    @Bind(R.id.iv_animation_voice_client_list)
    ImageView ivAnimationVoiceClientList;
    @Bind(R.id.rl_item_voice_client_list)
    RelativeLayout rlItemVoiceClientList;
    @Bind(R.id.tv_name_voice_client_list)
    TextView tvNameVoiceClientList;
    @Bind(R.id.f_voice_client_list)
    Fragment fVoiceClientList;
    @Bind(R.id.f_product_category)
    Fragment fProductCategory;
    @Bind(R.id.btn_be_vip_send_product)
    Button btnBeVipSendProduct;
    @Bind(R.id.tv_diamond_send_product)
    TextView tvDiamondSendProduct;
    @Bind(R.id.tv_to_charge_send_product)
    TextView tvToChargeSendProduct;
    @Bind(R.id.tv_sum_send_product)
    TextView tvSumSendProduct;
    @Bind(R.id.btn_send_product)
    Button btnSendProduct;

    String channelId = "";
    int voiceRole = 0;

    public static SendProductFragment getInstance(Bundle extras) {
        SendProductFragment fragment = new SendProductFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_send_product;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        VoiceClientGridFragment voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(arguments);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

    @OnClick({R.id.rl_item_voice_client_list, R.id.btn_be_vip_send_product, R.id.tv_diamond_send_product, R.id.tv_to_charge_send_product, R.id.tv_sum_send_product, R.id.btn_send_product})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_voice_client_list:
                break;
            case R.id.btn_be_vip_send_product:
                break;
            case R.id.tv_diamond_send_product:
                break;
            case R.id.tv_to_charge_send_product:
                break;
            case R.id.tv_sum_send_product:
                break;
            case R.id.btn_send_product:
                break;
        }
    }
}