package com.zl.dappore.voiceroom.fragment.voicerole.voiceempty;

import android.os.Bundle;

import com.zl.dappore.voiceroom.fragment.voicerole.VoiceHolderOperationFragment;


public class VoiceHolder2EmptyOperationFragment extends VoiceHolderOperationFragment {

    public static VoiceHolder2EmptyOperationFragment getInstance(Bundle extras) {
        VoiceHolder2EmptyOperationFragment fragment = new VoiceHolder2EmptyOperationFragment();
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
        letrole.setText("抱TA上麦");
    }
}