package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.tricolorflower.heartbeat.R;

public class VoiceRoleOperationDialogFragment extends QsDialogFragment {
    @Bind(R.id.content)
    protected RelativeLayout content;
    @Bind(R.id.voiceinfolayout)
    protected RelativeLayout voiceinfolayout;
    @Bind(R.id.fragmentlayout)
    protected RelativeLayout fragmentlayout;

    protected VoiceRoleOperationFragment fragment;
    protected VoiceRoleInfoFragment voiceinfo;

    public static VoiceRoleOperationDialogFragment getInstance(Bundle extras) {
        VoiceRoleOperationDialogFragment fragment = new VoiceRoleOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_role_operation, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setContentFragment();
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
    public void onResume() {
        super.onResume();
    }

    protected void setContentFragment(){
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
}
