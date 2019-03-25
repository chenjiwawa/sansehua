package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceuser;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceClient2UserOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceClient2UserOperationDialogFragment getInstance(Bundle extras) {
        VoiceClient2UserOperationDialogFragment fragment = new VoiceClient2UserOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceClient2UserOperationFragment.getInstance(getArguments()), VoiceClient2UserOperationFragment.class.getSimpleName());
    }

}
