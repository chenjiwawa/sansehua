package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.View;

import com.zl.dappore.voiceroom.model.VoiceRole;


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
        if (data == null || user == null)
            return;

        super.setVoiceRoleView();
        if (user.id.equals(data.id)) {
            //
        } else {
            switch (user.voiceRole) {
                case VoiceRole.VOICE_AUDITOR:
                    role.setVisibility(View.GONE);
                    music.setVisibility(View.GONE);
                    enable.setVisibility(View.GONE);
                    invite.setVisibility(View.GONE);
                    mute.setVisibility(View.GONE);
                    break;
                case VoiceRole.VOICE_ADMIN_AUDITOR:
                    role.setVisibility(View.VISIBLE);
                    music.setVisibility(View.VISIBLE);
                    enable.setVisibility(View.VISIBLE);
                    invite.setVisibility(View.VISIBLE);
                    mute.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}