package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceAdmin2EmptyOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceAdmin2EmptyOperationDialogFragment getInstance(Bundle extras) {
        VoiceAdmin2EmptyOperationDialogFragment fragment = new VoiceAdmin2EmptyOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceAdmin2EmptyOperationFragment.getInstance(getArguments()), VoiceAdmin2EmptyOperationFragment.class.getSimpleName());
    }

}
