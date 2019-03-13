package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;

public class VoiceAuditorOperationDialogFragment extends QsDialogFragment {
    @Bind(R.id.content)
    RelativeLayout content;
    VoiceRoleOperationFragment fragment;


    public static VoiceAuditorOperationDialogFragment getInstance(Bundle extras) {
        VoiceAuditorOperationDialogFragment fragment = new VoiceAuditorOperationDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_voice_auditor_operation, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        fragment = (VoiceRoleOperationFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
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
}
