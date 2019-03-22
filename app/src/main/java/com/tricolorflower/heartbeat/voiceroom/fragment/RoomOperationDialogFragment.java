package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.agora.AgoraHelper;
import com.tricolorflower.heartbeat.voiceroom.VoiceRoomSettingActivity;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

import java.util.ArrayList;
import java.util.List;

public class RoomOperationDialogFragment extends QsDialogFragment {

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

    public static RoomOperationDialogFragment getInstance(Bundle extras) {
        RoomOperationDialogFragment fragment = new RoomOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_operation, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        initArgumentData();
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

    @Override
    public void onStart() {
        super.onStart();
        L.i(initTag(), " onStart ");

        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.leave, R.id.report, R.id.share, R.id.setting, R.id.cancel, R.id.content})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.leave:
                AgoraHelper.getInstance().leaveChannel();
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
        dismissAllowingStateLoss();
    }
}
