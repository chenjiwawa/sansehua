package com.zl.dappore.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;

public class VoiceGuestOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceGuestOperationDialogFragment getInstance(Bundle extras) {
        VoiceGuestOperationDialogFragment fragment = new VoiceGuestOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_guest_operation, viewGroup);
        return view;
    }

    protected void setContentFragment(){
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceRoleOperationFragment.getInstance(getArguments()), VoiceRoleOperationFragment.class.getSimpleName());
    }

}
