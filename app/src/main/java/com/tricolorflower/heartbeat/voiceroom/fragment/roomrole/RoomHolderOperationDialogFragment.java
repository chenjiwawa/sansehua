package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.add.RoomHolderAddFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationFragment;

public class RoomHolderOperationDialogFragment extends RoomRoleOperationDialogFragment {

    public static RoomHolderOperationDialogFragment getInstance(Bundle extras) {
        RoomHolderOperationDialogFragment fragment = new RoomHolderOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected void showAddView() {
        super.showAddView();

        RoomHolderAddFragment contentFragment = RoomHolderAddFragment.getInstance(getArguments());
        removeContentFragment(contentFragment);

        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, RoomHolderAddFragment.getInstance(getArguments()), RoomHolderAddFragment.class.getSimpleName());
    }

}
