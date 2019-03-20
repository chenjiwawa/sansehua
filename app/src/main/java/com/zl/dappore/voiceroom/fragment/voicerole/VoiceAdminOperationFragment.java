package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;


public class VoiceAdminOperationFragment extends VoiceRoleOperationFragment {

    public static VoiceAdminOperationFragment getInstance(Bundle extras) {
        VoiceAdminOperationFragment fragment = new VoiceAdminOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    @Override
    protected void setVoiceRoleView() {
        if (data == null || user == null)
            return;

        super.setVoiceRoleView();
        role.setVisibility(View.VISIBLE);
        music.setVisibility(View.GONE);
        enable.setVisibility(View.VISIBLE);
        letrole.setVisibility(View.VISIBLE);
        mute.setVisibility(View.VISIBLE);
        leave.setVisibility(View.VISIBLE);
    }
}