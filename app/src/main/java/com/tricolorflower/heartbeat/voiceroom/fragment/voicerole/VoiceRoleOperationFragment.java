package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceRoleOperationPresenter;

public class VoiceRoleOperationFragment extends QsFragment<VoiceRoleOperationPresenter> {

    protected VoiceRole user;//当前用户
    protected VoiceRole data;

    public static VoiceRoleOperationFragment getInstance(Bundle extras) {
        VoiceRoleOperationFragment fragment = new VoiceRoleOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_voice_role_operation;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        arguments = new Bundle();//TODO
        if (arguments == null) return;
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        data = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR);

        L.i(initTag(), " user " + user + " data " + data);

        showContentView();
    }

    private void requstData(int voiceRole) {
    }

    public String getRoleText(VoiceRole data) {
        if (data != null) {
            return ((data.voiceRole == VoiceRole.VOICE_ADMIN_CLIENT || data.voiceRole == VoiceRole.VOICE_CLIENT) ? "下麦" : "上麦");
        }
        return "";
    }

    public String getMusicText(VoiceRole data) {
        if (data != null) {
            return (data.musicEnable ? "打开音乐权限" : "关闭音乐权限");
        }
        return "";
    }

    public String getEnableText(VoiceRole data) {
        if (data != null) {
            return (data.voiceEnable ? "解除封麦" : "封麦");
        }
        return "";
    }

    public String getLetroleText(VoiceRole data) {
        if (data != null) {

            return ((data.voiceRole == VoiceRole.VOICE_ADMIN_CLIENT || data.voiceRole == VoiceRole.VOICE_CLIENT) ? "关闭麦克风" : "打开麦克风");
        }
        return "";
    }

    public String getLeaveText(VoiceRole data) {
        if (data != null) {
            return "";
        }
        return "";
    }

    public String getMuteText(VoiceRole data) {
        if (data != null) {

            return (data.voiceMute ? "关闭麦克风" : "打开麦克风");
        }
        return "";
    }


    public void roleClick(View view) {

    }

    public void musicClick(View view) {

    }

    public void enableClick(View view) {

    }

    public void letroleClick(View view) {

    }

    public void leaveClick(View view) {

    }

    public void muteClick(View view) {

    }

    @Override
    public boolean isOpenViewState() {
        return false;
    }
}