package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.View;


public class VoiceHolderOperationFragment extends VoiceRoleOperationFragment {

    public static VoiceHolderOperationFragment getInstance(Bundle extras) {
        VoiceHolderOperationFragment fragment = new VoiceHolderOperationFragment();
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
        if (user.id.equals(data.id)) {
            //
        } else {
            role.setVisibility(View.VISIBLE);
            music.setVisibility(View.VISIBLE);
            enable.setVisibility(View.VISIBLE);
            invite.setVisibility(View.VISIBLE);
            mute.setVisibility(View.VISIBLE);
        }
    }
}