package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;


public class VoiceAuditorOperationFragment extends VoiceRoleOperationFragment {

    public static VoiceAuditorOperationFragment getInstance(Bundle extras) {
        VoiceAuditorOperationFragment fragment = new VoiceAuditorOperationFragment();
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