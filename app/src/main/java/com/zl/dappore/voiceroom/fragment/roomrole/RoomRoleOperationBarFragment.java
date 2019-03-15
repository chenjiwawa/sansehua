package com.zl.dappore.voiceroom.fragment.roomrole;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.chatroom.InputMessageDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomrole.product.SendProductDialogFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceOperationPresenter;


public class RoomRoleOperationBarFragment extends QsFragment<VoiceOperationPresenter> {

    @Bind(R.id.ib_local_room_role_operation)
    ImageButton ibLocalRoomRoleOperation;
    @Bind(R.id.ib_remote_room_role_operation)
    ImageButton ibRemoteRoomRoleOperation;
    @Bind(R.id.ib_add_room_role_operation)
    ImageButton ibAddRoomRoleOperation;
    @Bind(R.id.ib_message_room_role_operation)
    ImageButton ibMessageRoomRoleOperation;
    @Bind(R.id.ib_logo_anim_room_role_operation)
    ImageButton ibLogoAnimRoomRoleOperation;
    @Bind(R.id.ib_product_room_role_operation)
    ImageButton ibProductRoomRoleOperation;
    @Bind(R.id.rl_frame_room_role_operation)
    RelativeLayout rlFrameRoomRoleOperation;
    @Bind(R.id.frame)
    RelativeLayout frame;

    String channelId = "";
    int voiceRole = 0;

    public static RoomRoleOperationBarFragment getInstance(Bundle extras) {
        RoomRoleOperationBarFragment fragment = new RoomRoleOperationBarFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_room_role_operation_bar;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

    private void setDialogDismiss() {
        if (getParentFragment() != null && getParentFragment() instanceof QsDialogFragment) {
            ((QsDialogFragment) getParentFragment()).dismiss();
        }
    }

    @OnClick({R.id.ib_local_room_role_operation, R.id.ib_remote_room_role_operation, R.id.ib_add_room_role_operation, R.id.ib_message_room_role_operation, R.id.ib_logo_anim_room_role_operation, R.id.ib_product_room_role_operation, R.id.rl_frame_room_role_operation, R.id.frame})
    public void onViewClick(View view) {
        super.onViewClick(view);

        RoomRoleOperationDialogFragment roomRoleOperationDialogFragment;
        switch (view.getId()) {
            case R.id.ib_local_room_role_operation:
                break;
            case R.id.ib_remote_room_role_operation:
                break;
            case R.id.ib_add_room_role_operation:
                roomRoleOperationDialogFragment = RoomRoleOperationDialogFragment.getInstance();
                roomRoleOperationDialogFragment.showAddView();
                roomRoleOperationDialogFragment.show();
                break;
            case R.id.ib_message_room_role_operation:
//                setDialogDismiss();
                InputMessageDialogFragment.getInstance().show();
                break;
            case R.id.ib_logo_anim_room_role_operation:
                roomRoleOperationDialogFragment = RoomRoleOperationDialogFragment.getInstance();
                roomRoleOperationDialogFragment.showLogoAnimView();
                roomRoleOperationDialogFragment.show();
                break;
            case R.id.ib_product_room_role_operation:
                SendProductDialogFragment.getInstance().show();
                break;
            case R.id.rl_frame_room_role_operation:
                break;
            case R.id.frame:
                break;
        }
    }
}