package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.add;

import android.os.Bundle;
import android.view.View;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentRelativeLayout;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.adminlist.AdminListActivity;
import com.tricolorflower.heartbeat.blacklist.BlackListActivity;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

import java.util.ArrayList;
import java.util.List;


public class RoomRoleAddFragment extends QsFragment {

    protected VoiceRoom voiceRoom;
    protected VoiceRole voiceHolder;
    protected List<VoiceRole> voiceClients;
    protected VoiceRole user;
    protected Bundle bundle;

    public static RoomRoleAddFragment getInstance(Bundle extras) {
        RoomRoleAddFragment fragment = new RoomRoleAddFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_room_role_add;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initArgumentData();

        setRoomRoleOperationView();
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

    protected void setRoomRoleOperationView() {
    }

    public void requstData() {
    }

    public void roomRole2AdminList() {
        bundle = new Bundle();
        bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_ID, "1");
        bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE, BaseVoiceRole.VOICE_HOLDER);
        QsHelper.getInstance().intent2Activity(AdminListActivity.class, bundle);
    }

    public void roomRole2BlackList() {
        bundle = new Bundle();
        bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_ID, "1");
        bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE, BaseVoiceRole.VOICE_HOLDER);
        QsHelper.getInstance().intent2Activity(BlackListActivity.class, bundle);
    }


}