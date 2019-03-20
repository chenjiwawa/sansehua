package com.zl.dappore.voiceroom.fragment.voicerole.voiceclient;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.fragment.voicerole.VoiceHolderOperationFragment;


public class VoiceHolder2ClientOperationFragment extends VoiceHolderOperationFragment {

    public static VoiceHolder2ClientOperationFragment getInstance(Bundle extras) {
        VoiceHolder2ClientOperationFragment fragment = new VoiceHolder2ClientOperationFragment();
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
        letrole.setText("抱TA下麦");
        leave.setVisibility(View.VISIBLE);
    }
}