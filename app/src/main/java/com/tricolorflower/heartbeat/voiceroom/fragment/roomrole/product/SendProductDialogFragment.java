package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product;

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
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SendProductDialogFragment extends QsDialogFragment {
    @Bind(R.id.content)
    RelativeLayout content;

    public static SendProductDialogFragment getInstance(Bundle bundle) {
        SendProductDialogFragment fragment = new SendProductDialogFragment();
        fragment.setArguments(bundle);
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
        View view = layoutInflater.inflate(R.layout.dialog_send_product, viewGroup);
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
