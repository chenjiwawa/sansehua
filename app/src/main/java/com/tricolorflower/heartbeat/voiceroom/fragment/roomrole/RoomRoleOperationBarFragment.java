package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.agora.AgoraHelper;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.voiceroom.fragment.chatroom.InputMessageDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.SendProductDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.presenter.RoomRoleOperationBarPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationDialogFragment.ROOM_ROLE_OPERATION_SHOW_ADD;
import static com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationDialogFragment.ROOM_ROLE_OPERATION_SHOW_EMOJI;


public class RoomRoleOperationBarFragment extends QsFragment<RoomRoleOperationBarPresenter> {

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

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

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
        initArgumentData();

        setRoomRoleOperationBar(user);
        showContentView();
    }


    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

    }

    public void setRoomRoleOperationBar(VoiceRole user) {
        if (user == null)
            return;

        switch (user.permission) {
            case BaseVoiceRole.PERMISSION_GUEST:
                ibAddRoomRoleOperation.setVisibility(View.GONE);
                break;
            case BaseVoiceRole.PERMISSION_HOLDER:
            case BaseVoiceRole.PERMISSION_ADMIN:
                ibAddRoomRoleOperation.setVisibility(View.VISIBLE);
                break;
        }
    }

    RoomRoleOperationDialogFragment roomRoleOperationDialogFragment;

    public void initRoomRoleOperationDialogFragment(VoiceRole user) {
        if (user == null)
            return;

        switch (user.permission) {
            case BaseVoiceRole.PERMISSION_GUEST:
                roomRoleOperationDialogFragment = RoomGuestOperationDialogFragment.getInstance(getArguments());
                break;
            case BaseVoiceRole.PERMISSION_HOLDER:
                roomRoleOperationDialogFragment = RoomHolderOperationDialogFragment.getInstance(getArguments());
                break;
            case BaseVoiceRole.PERMISSION_ADMIN:
                roomRoleOperationDialogFragment = RoomAdminOperationDialogFragment.getInstance(getArguments());
                break;
        }
    }

    public void requstData() {
    }

    private void setParentDialogDismiss() {
        QsHelper.getInstance().eventPost(new RoomRoleOperationEvent.OnDialogFragment(RoomRoleOperationEvent.OnDialogFragment.State.DIDMISS));
    }

    @OnClick({R.id.ib_local_room_role_operation, R.id.ib_remote_room_role_operation, R.id.ib_add_room_role_operation, R.id.ib_message_room_role_operation, R.id.ib_logo_anim_room_role_operation, R.id.ib_product_room_role_operation, R.id.rl_frame_room_role_operation, R.id.frame})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.ib_local_room_role_operation:
                break;
            case R.id.ib_remote_room_role_operation:
                if (voiceHolder != null) {
                    AgoraHelper.getInstance().muteRemoteAudioStream(voiceHolder.id, true);
                }
                break;
            case R.id.ib_add_room_role_operation:
                L.i(initTag(), " onViewClick ib_add_room_role_operation ");
                initArgumentData();
                initRoomRoleOperationDialogFragment(user);
                if (roomRoleOperationDialogFragment != null) {
                    roomRoleOperationDialogFragment.setContentFragmentState(ROOM_ROLE_OPERATION_SHOW_ADD);
                    roomRoleOperationDialogFragment.show();
                }
                break;
            case R.id.ib_message_room_role_operation:
                setParentDialogDismiss();
                InputMessageDialogFragment.getInstance().show();
                break;
            case R.id.ib_logo_anim_room_role_operation:
                initArgumentData();
                initRoomRoleOperationDialogFragment(user);
                if (roomRoleOperationDialogFragment != null) {
                    roomRoleOperationDialogFragment.setContentFragmentState(ROOM_ROLE_OPERATION_SHOW_EMOJI);
                    roomRoleOperationDialogFragment.show();
                }
                break;
            case R.id.ib_product_room_role_operation:
                setParentDialogDismiss();
                SendProductDialogFragment.getInstance(getArguments()).show();
                break;
            case R.id.rl_frame_room_role_operation:
                break;
            case R.id.frame:
                break;
        }
    }
}