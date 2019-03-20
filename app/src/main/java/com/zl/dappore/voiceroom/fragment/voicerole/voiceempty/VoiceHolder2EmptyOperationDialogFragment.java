package com.zl.dappore.voiceroom.fragment.voicerole.voiceempty;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceHolder2EmptyOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceHolder2EmptyOperationDialogFragment getInstance(Bundle extras) {
        VoiceHolder2EmptyOperationDialogFragment fragment = new VoiceHolder2EmptyOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceHolder2EmptyOperationFragment.getInstance(getArguments()), VoiceHolder2EmptyOperationFragment.class.getSimpleName());
    }

}
