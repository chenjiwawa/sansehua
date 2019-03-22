package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;

public class VoiceAdminOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceAdminOperationDialogFragment getInstance(Bundle extras) {
        VoiceAdminOperationDialogFragment fragment = new VoiceAdminOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_admin_operation, viewGroup);
        return view;
    }

    protected void setContentFragment(){
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceAdminOperationFragment.getInstance(getArguments()), VoiceAdminOperationFragment.class.getSimpleName());
    }

}
