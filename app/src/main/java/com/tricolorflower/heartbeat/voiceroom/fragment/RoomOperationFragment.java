package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.agora.AgoraHelper;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomOperationEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.voiceroom.VoiceRoomSettingActivity;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.presenter.RoomOperationPresenter;

import java.util.ArrayList;
import java.util.List;

public class RoomOperationFragment extends QsFragment<RoomOperationPresenter> {

    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.leave)
    Button leave;
    @Bind(R.id.report)
    Button report;
    @Bind(R.id.share)
    Button share;
    @Bind(R.id.setting)
    Button setting;
    @Bind(R.id.cancel)
    Button cancel;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String token;

    public static RoomOperationFragment getInstance(Bundle extras) {
        RoomOperationFragment fragment = new RoomOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_room_operation;
    }

    @Override
    public void initData(Bundle bundle) {
        initArgumentData();

        setView();
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

    }

    public void leaveVoiceRoom() {
        if (voiceRoom == null)
            return;

        getPresenter().leaveVoiceRoom(token, voiceRoom.voiceRoomId);
    }

    private void setView() {
        if (user == null)
            return;

        if (user.isVoiceHolder()) {
            report.setVisibility(View.GONE);
            setting.setVisibility(View.VISIBLE);
        } else {
            report.setVisibility(View.VISIBLE);
            setting.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.leave, R.id.report, R.id.share, R.id.setting, R.id.cancel, R.id.content})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.leave:
                leaveVoiceRoom();
                break;
            case R.id.report:
                break;
            case R.id.share:
                break;
            case R.id.setting:
                QsHelper.getInstance().intent2Activity(VoiceRoomSettingActivity.class, getArguments());
                break;
            case R.id.cancel:
                break;
            case R.id.content:
                break;
        }
    }

    public void setSuccessView() {
        QsHelper.getInstance().eventPost(new VoiceRoomOperationEvent.OnDialogFragment(VoiceRoomOperationEvent.OnDialogFragment.State.DIDMISS));
        QsHelper.getInstance().eventPost(new VoiceRoomEvent.OnLeave());
    }
}
