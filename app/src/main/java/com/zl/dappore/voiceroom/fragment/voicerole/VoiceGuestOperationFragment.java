package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;


public class VoiceGuestOperationFragment extends VoiceRoleOperationFragment {

    public static VoiceGuestOperationFragment getInstance(Bundle extras) {
        VoiceGuestOperationFragment fragment = new VoiceGuestOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    @Override
    protected void setVoiceRoleView() {
    }
}