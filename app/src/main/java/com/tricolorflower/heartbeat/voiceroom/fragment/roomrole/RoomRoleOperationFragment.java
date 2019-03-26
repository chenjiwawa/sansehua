package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.presenter.RoomRoleOperationPresenter;


public class RoomRoleOperationFragment extends QsFragment<RoomRoleOperationPresenter> {

    String channelId = "";
    int voiceRole = 0;

    public static RoomRoleOperationFragment getInstance(Bundle extras) {
        RoomRoleOperationFragment fragment = new RoomRoleOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_room_role_operation;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

}