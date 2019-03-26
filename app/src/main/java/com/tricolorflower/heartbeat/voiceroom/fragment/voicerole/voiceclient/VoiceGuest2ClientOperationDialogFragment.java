package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceGuest2ClientOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceGuest2ClientOperationDialogFragment getInstance(Bundle extras) {
        VoiceGuest2ClientOperationDialogFragment fragment = new VoiceGuest2ClientOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
    }

}
