package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;


public class VoiceRoleOperationFragment extends QsFragment {

    @Bind(R.id.role)
    protected Button role;
    @Bind(R.id.music)
    protected Button music;
    @Bind(R.id.enable)
    protected Button enable;
    @Bind(R.id.letrole)
    protected Button letrole;
    @Bind(R.id.leave)
    protected Button leave;
    @Bind(R.id.mute)
    protected Button mute;
    @Bind(R.id.cancel)
    protected Button cancel;

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
        if (arguments == null) return;
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        data = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR);

        L.i(initTag(), " user " + user + " data " + data);

        setVoiceRoleView();
        showContentView();
    }

    private void requstData(int voiceRole) {
    }

    protected void setVoiceRoleView() {
        if (data == null || user == null)
            return;

        role.setText(((data.voiceRole == VoiceRole.VOICE_ADMIN_CLIENT || data.voiceRole == VoiceRole.VOICE_CLIENT) ? "下麦" : "上麦"));
        music.setText((data.musicEnable ? "打开音乐权限" : "关闭音乐权限"));
        enable.setText((data.voiceEnable ? "解除封麦" : "封麦"));
        mute.setText((data.voiceMute ? "关闭麦克风" : "打开麦克风"));
        letrole.setText(((data.voiceRole == VoiceRole.VOICE_ADMIN_CLIENT || data.voiceRole == VoiceRole.VOICE_CLIENT) ? "关闭麦克风" : "打开麦克风"));
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

    @OnClick({R.id.role, R.id.music, R.id.enable, R.id.letrole, R.id.mute, R.id.cancel})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.role:
                roleClick(view);
                break;
            case R.id.music:
                musicClick(view);
                break;
            case R.id.enable:
                enableClick(view);
                break;
            case R.id.letrole:
                letroleClick(view);
                break;
            case R.id.leave:
                leaveClick(view);
                break;
            case R.id.mute:
                muteClick(view);
                break;
            case R.id.cancel:
                break;
        }
    }
}