package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomSettingEvent;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RoomRoleOperationDialogFragment extends QsDialogFragment {

    public static final int ROOM_ROLE_OPERATION_SHOW_ADD = 1;
    public static final int ROOM_ROLE_OPERATION_SHOW_EMOJI = 2;

    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.fragmentlayout)
    RelativeLayout fragmentlayout;
    QsFragment contentFragment;

    public int contentFragmentState;

    public static RoomRoleOperationDialogFragment getInstance(Bundle extras) {
        RoomRoleOperationDialogFragment fragment = new RoomRoleOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_role_operation, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void onStart() {
        super.onStart();
        L.i(initTag(), " onStart ");

        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setContentFragment();
    }

    public void setContentFragmentState(int contentFragmentState) {
        this.contentFragmentState = contentFragmentState;
    }

    public int getContentFragmentState() {
        return contentFragmentState;
    }

    protected void setContentFragment() {
        switch (getContentFragmentState()) {
            case ROOM_ROLE_OPERATION_SHOW_ADD:
                showAddView();
                break;
            case ROOM_ROLE_OPERATION_SHOW_EMOJI:
                showEmojiView();
                break;
        }
    }

    protected void showEmojiView() {
    }

    protected void showAddView() {
    }

    protected void removeContentFragment(QsFragment contentFragment) {
        if (contentFragment == null)
            return;

        if (contentFragment.isAdded()) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(contentFragment);
            fragmentTransaction.commit();
        }
    }


    @OnClick({R.id.content})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.content:
                break;
        }
        dismissAllowingStateLoss();
    }

    @Subscribe
    public void onEvent(RoomRoleOperationEvent.OnDialogFragment event) {
        if (event == null)
            return;

        switch (event.state) {
            case DIDMISS:
                dismiss();
                break;
        }
    }


    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
