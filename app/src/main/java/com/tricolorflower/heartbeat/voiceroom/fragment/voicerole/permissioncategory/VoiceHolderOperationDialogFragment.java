package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.permissioncategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;

public class VoiceHolderOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceHolderOperationDialogFragment getInstance(Bundle extras) {
        VoiceHolderOperationDialogFragment fragment = new VoiceHolderOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_holder_operation, viewGroup);
        return view;
    }
    protected void setContentFragment(){
//        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.voiceinfolayout, VoiceRoleInfoFragment.getInstance(getArguments()), VoiceRoleInfoFragment.class.getSimpleName());
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, VoiceHolderOperationFragment.getInstance(getArguments()), VoiceHolderOperationFragment.class.getSimpleName());
    }

}
