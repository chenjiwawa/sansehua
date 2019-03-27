package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomProductEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomSettingEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.voiceroom.fragment.VoiceClientListFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.presenter.SendProductPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

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

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String token;

    VoiceClientListFragment voiceClientListFragment;
    ProductCategoryFragment productCategoryFragment;

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
        initArgumentData();

        voiceClientListFragment = (VoiceClientListFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_client_list);
        productCategoryFragment = (ProductCategoryFragment) getChildFragmentManager().findFragmentById(R.id.f_product_category);
        loadingClose();
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        token = UserConfig.getInstance().getAuthToken();

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

        voiceClientListFragment.setArguments(getArguments());

    }

    public void requstData() {

        if (TextUtils.isEmpty(voiceClientListFragment.getSelectVoiceRoleList())) {
            QsToast.show("请选择送礼物对象！");
            return;
        }

        if (productCategoryFragment.getSelectGiftProduct() == null) {
            QsToast.show("请选择礼物！");
            return;
        }

        SendProductRequestBody requestBody = new SendProductRequestBody();
        requestBody.token = token;
        requestBody.gift_id = productCategoryFragment.getSelectGiftProduct().id;
        requestBody.muid = voiceClientListFragment.getSelectVoiceRoleList();
        requestBody.room_id = voiceRoom.voiceRoomId;
        requestBody.number = 1;
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

    @ThreadPoint(ThreadType.MAIN)
    public void setSendProductSuccessView() {
        QsHelper.getInstance().eventPost(new RoomRoleOperationEvent.OnDialogFragment(RoomRoleOperationEvent.OnDialogFragment.State.DIDMISS));
    }

    @Subscribe
    public void onEvent(VoiceRoomProductEvent.OnChoiceEvent event) {
        if (event == null)
            return;

        switch (event.state) {
            case SELECT:

                break;
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}