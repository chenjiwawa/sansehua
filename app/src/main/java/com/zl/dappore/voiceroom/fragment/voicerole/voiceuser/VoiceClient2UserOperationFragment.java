package com.zl.dappore.voiceroom.fragment.voicerole.voiceuser;

import android.os.Bundle;

import com.zl.dappore.voiceroom.fragment.voicerole.VoiceClientOperationFragment;


public class VoiceClient2UserOperationFragment extends VoiceClientOperationFragment {

    public static VoiceClient2UserOperationFragment getInstance(Bundle extras) {
        VoiceClient2UserOperationFragment fragment = new VoiceClient2UserOperationFragment();
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
        role.setText("下麦");
    }
}