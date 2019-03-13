package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.dappore.R;

public class VoiceClientOperationDialogFragment extends VoiceRoleOperationDialogFragment {

    public static VoiceClientOperationDialogFragment getInstance(Bundle extras) {
        VoiceClientOperationDialogFragment fragment = new VoiceClientOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    
    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_client_operation, viewGroup);
        return view;
    }

}
