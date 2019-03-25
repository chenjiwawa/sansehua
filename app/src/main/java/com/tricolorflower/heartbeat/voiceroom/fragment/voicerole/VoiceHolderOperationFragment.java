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
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;


public class VoiceHolderOperationFragment extends VoiceRoleOperationFragment {

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


    public static VoiceHolderOperationFragment getInstance(Bundle extras) {
        VoiceHolderOperationFragment fragment = new VoiceHolderOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setVoiceRoleView();
    }

    protected void setVoiceRoleView() {
        L.i(initTag(), " setVoiceRoleView user " + user + " data " + data);

        role.setText("下麦");

        if (data == null || user == null)
            return;

        showContentView();

        role.setText(getRoleText(data));
        music.setText(getMusicText(data));
        enable.setText(getEnableText(data));
        mute.setText(getMuteText(data));
        letrole.setText(getLeaveText(data));

        role.setVisibility(View.GONE);
        music.setVisibility(View.VISIBLE);
        enable.setVisibility(View.VISIBLE);
        letrole.setVisibility(View.VISIBLE);
        mute.setVisibility(View.VISIBLE);
        leave.setVisibility(View.GONE);
    }

    @OnClick({R.id.role, R.id.music, R.id.enable, R.id.letrole, R.id.mute, R.id.cancel})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.role:
                L.i(initTag(), " onViewClick role ");
                roleClick(view);
                break;
            case R.id.music:
                L.i(initTag(), " onViewClick music ");
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