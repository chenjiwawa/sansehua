package com.zl.dappore.voiceroom.fragment.voicerole.voiceempty;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.fragment.voicerole.VoiceAdminOperationFragment;


public class VoiceAdmin2EmptyOperationFragment extends VoiceAdminOperationFragment {

    public static VoiceAdmin2EmptyOperationFragment getInstance(Bundle extras) {
        VoiceAdmin2EmptyOperationFragment fragment = new VoiceAdmin2EmptyOperationFragment();
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
        music.setVisibility(View.GONE);
        leave.setVisibility(View.GONE);
        letrole.setText("抱TA上麦");
    }
}