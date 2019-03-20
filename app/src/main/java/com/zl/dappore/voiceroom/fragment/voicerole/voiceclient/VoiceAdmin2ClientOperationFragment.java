package com.zl.dappore.voiceroom.fragment.voicerole.voiceclient;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.fragment.voicerole.VoiceAdminOperationFragment;


public class VoiceAdmin2ClientOperationFragment extends VoiceAdminOperationFragment {

    public static VoiceAdmin2ClientOperationFragment getInstance(Bundle extras) {
        VoiceAdmin2ClientOperationFragment fragment = new VoiceAdmin2ClientOperationFragment();
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
        role.setVisibility(View.GONE);
        music.setVisibility(View.GONE);
        letrole.setText("抱TA下麦");
    }
}