package com.zl.dappore.voiceroom.fragment.roomrole;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.roomrole.add.RoomAdminAddFragment;
import com.zl.dappore.voiceroom.fragment.roomrole.add.RoomHolderAddFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationFragment;

public class RoomAdminOperationDialogFragment extends RoomRoleOperationDialogFragment {

    public static RoomAdminOperationDialogFragment getInstance(Bundle extras) {
        RoomAdminOperationDialogFragment fragment = new RoomAdminOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    protected void setContentFragment() {
    }

    @Override
    protected void showAddView() {
        super.showAddView();

        RoomAdminAddFragment contentFragment = RoomAdminAddFragment.getInstance(getArguments());

        removeContentFragment(contentFragment);
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, contentFragment, RoomAdminAddFragment.class.getSimpleName());
    }
}
