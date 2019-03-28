package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.VoiceRoomOperationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RoomOperationDialogFragment extends QsDialogFragment {

    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.fragmentlayout)
    RelativeLayout fragmentlayout;

    RoomOperationFragment fragment;

    public static RoomOperationDialogFragment getInstance(Bundle extras) {
        RoomOperationDialogFragment fragment = new RoomOperationDialogFragment();
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
        View view = layoutInflater.inflate(R.layout.dialog_room_operation, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        setContentView();
    }

    private void setContentView() {
        fragment = (RoomOperationFragment) getChildFragmentManager().findFragmentById(R.id.roomoperation);
        fragment.setArguments(getArguments());
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
    public void onEvent(VoiceRoomOperationEvent.OnDialogFragment event) {
        if (event == null || event.state == null)
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
