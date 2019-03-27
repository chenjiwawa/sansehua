package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.invitelist.InviteListActivity;
import com.tricolorflower.heartbeat.onlinelist.OnlineListActivity;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionClientRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMicrophoneRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMusicPermissionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceRoleOperationPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.tricolorflower.heartbeat.common.event.VoiceRoomEvent.State.FRESH;

public class VoiceRoleOperationFragment extends QsFragment<VoiceRoleOperationPresenter> {

    protected VoiceRoom voiceRoom;
    protected VoiceRole user;//当前用户
    protected VoiceRole data;
    protected String token;

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
        initArgumentData();
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        data = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR);
        token = UserConfig.getInstance().getAuthToken();

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData user " + user);
        L.i(initTag(), " initArgumentData data " + data);
        L.i(initTag(), " initArgumentData token " + token);

    }


    private void requstData() {
    }

    public String getRoleText(VoiceRole data) {
        if (data != null) {
            return ((data.voiceRole == VoiceRole.VOICE_ADMIN_CLIENT || data.voiceRole == VoiceRole.VOICE_CLIENT) ? "下麦" : "上麦");
        }
        return "";
    }

    public String getMusicText(VoiceRole data) {
        if (data != null) {
            return (data.musicEnable == 2 ? "打开音乐权限" : "关闭音乐权限");
        }
        return "";
    }

    public String getEnableText(VoiceRole data) {
        if (data != null) {
            return (data.voiceEnable == 1 ? "解除封麦" : "封麦");
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
            return "踢出房间";
        }
        return "";
    }

    public String getMuteText(VoiceRole data) {
        if (data != null) {

            return (data.voiceMute == 1 ? "关闭麦克风" : "打开麦克风");
        }
        return "";
    }


    public void roleClick(View view) {
    }

    public void musicClick(View view) {
        if (voiceRoom == null || user == null || data == null)
            return;

        VoicePositionMusicPermissionRequestBody body = new VoicePositionMusicPermissionRequestBody(token, voiceRoom.voiceRoomId, data.position, data.musicEnable == 1 ? 2 : 1);
        getPresenter().setVoicePositionMusicPermission(body);
    }

    public void enableClick(View view) {
        if (voiceRoom == null || user == null || data == null)
            return;

        VoicePositionRequestBody body = new VoicePositionRequestBody(token, voiceRoom.voiceRoomId, data.position, data.voiceEnable == 1 ? 2 : 1);
        getPresenter().setVoicePosition(body);
    }

    public void letroleClick(View view) {
    }

    protected void leroleLogin() {
        QsHelper.getInstance().intent2Activity(InviteListActivity.class, getArguments());
    }

    public void leroleLogout() {

    }

    public void leaveClick(View view) {
        if (voiceRoom == null || user == null || data == null)
            return;

        BaseVoiceRoleRequestBody body = new BaseVoiceRoleRequestBody(token, data.id);
        getPresenter().kickOutVoicePosition(body);
    }

    public void muteClick(View view) {
        if (voiceRoom == null || user == null || data == null)
            return;

        VoicePositionMicrophoneRequestBody body = new VoicePositionMicrophoneRequestBody(token, voiceRoom.voiceRoomId, data.position, data.voiceMute == 1 ? 2 : 1);
        getPresenter().setVoicePositionMicrophone(body);
    }

    public void cancelClick(View view) {
        QsHelper.getInstance().eventPost(new VoiceRoleOperationEvent.OnDialogFragment(VoiceRoleOperationEvent.OnDialogFragment.State.DIDMISS));
    }

    public void showLoadingDialog() {
        loading("请稍后...");
    }

    public void closeLoadingDialog() {
        loadingClose();
    }

    @Subscribe
    public void onEvent(VoiceRoleOperationEvent.OnLetRoleLoginEvent event) {
        if (event == null || event.data == null)
            return;

        VoicePositionClientRequestBody body = new VoicePositionClientRequestBody(token, voiceRoom.voiceRoomId, data.position, event.data.id);
        getPresenter().loginVoicePosition(body);

    }

    @Override
    public boolean isOpenViewState() {
        return false;
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}