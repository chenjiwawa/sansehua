package com.zl.dappore.voiceroom.fragment.roomrole;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationFragment;

public class RoomGuestOperationDialogFragment extends RoomRoleOperationDialogFragment {

    public static RoomGuestOperationDialogFragment getInstance(Bundle extras) {
        RoomGuestOperationDialogFragment fragment = new RoomGuestOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment(){
    }
}
