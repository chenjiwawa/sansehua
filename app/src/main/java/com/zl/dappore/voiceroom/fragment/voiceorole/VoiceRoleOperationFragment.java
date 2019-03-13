package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.VoiceClientGridFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;


public class VoiceRoleOperationFragment extends QsFragment {

    String channelId = "";
    int voiceRole = 0;
    @Bind(R.id.role)
    Button role;
    @Bind(R.id.music)
    Button music;
    @Bind(R.id.onoff)
    Button onoff;
    @Bind(R.id.invite)
    Button invite;
    @Bind(R.id.mute)
    Button mute;
    @Bind(R.id.cancel)
    Button cancel;

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

        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        VoiceClientGridFragment voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(arguments);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }

    private void requstData(int voiceRole) {
    }

    protected void setVoiceRoleView() {

    }

    public void roleClick(View view) {

    }

    public void musicClick(View view) {

    }

    public void onoffClick(View view) {

    }

    public void inviteClick(View view) {

    }

    public void muteClick(View view) {

    }

    @OnClick({R.id.role, R.id.music, R.id.onoff, R.id.invite, R.id.mute, R.id.cancel})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.role:
                roleClick(view);
                break;
            case R.id.music:
                musicClick(view);
                break;
            case R.id.onoff:
                onoffClick(view);
                break;
            case R.id.invite:
                inviteClick(view);
                break;
            case R.id.mute:
                muteClick(view);
                break;
            case R.id.cancel:
                break;
        }
    }
}