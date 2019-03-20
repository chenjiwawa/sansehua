package com.zl.dappore.voiceroom.fragment.voicerole.voiceclient;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceHolder2ClientOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceHolder2ClientOperationDialogFragment getInstance(Bundle extras) {
        VoiceHolder2ClientOperationDialogFragment fragment = new VoiceHolder2ClientOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceHolder2ClientOperationFragment.getInstance(getArguments()), VoiceHolder2ClientOperationFragment.class.getSimpleName());
    }

}
