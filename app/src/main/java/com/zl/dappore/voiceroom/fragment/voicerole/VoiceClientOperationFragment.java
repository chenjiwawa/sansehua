package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;


public class VoiceClientOperationFragment extends VoiceRoleOperationFragment {

    public static VoiceClientOperationFragment getInstance(Bundle extras) {
        VoiceClientOperationFragment fragment = new VoiceClientOperationFragment();
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
        enable.setVisibility(View.GONE);
        letrole.setVisibility(View.GONE);
        mute.setVisibility(View.GONE);
        leave.setVisibility(View.GONE);
    }
}