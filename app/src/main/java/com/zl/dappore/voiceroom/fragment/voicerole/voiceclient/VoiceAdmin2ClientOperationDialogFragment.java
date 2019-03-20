package com.zl.dappore.voiceroom.fragment.voicerole.voiceclient;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceAdmin2ClientOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceAdmin2ClientOperationDialogFragment getInstance(Bundle extras) {
        VoiceAdmin2ClientOperationDialogFragment fragment = new VoiceAdmin2ClientOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceAdmin2ClientOperationFragment.getInstance(getArguments()), VoiceAdmin2ClientOperationFragment.class.getSimpleName());
    }

}
