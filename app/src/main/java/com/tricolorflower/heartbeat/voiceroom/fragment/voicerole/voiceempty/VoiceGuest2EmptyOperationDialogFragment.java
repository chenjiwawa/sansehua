package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceGuest2EmptyOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceGuest2EmptyOperationDialogFragment getInstance(Bundle extras) {
        VoiceGuest2EmptyOperationDialogFragment fragment = new VoiceGuest2EmptyOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceGuest2EmptyOperationFragment.getInstance(getArguments()), VoiceGuest2EmptyOperationFragment.class.getSimpleName());
    }

}
