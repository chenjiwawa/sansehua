package com.zl.dappore.voiceroom.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;

public class RoomOperationDialogFragment extends QsDialogFragment {
    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.leave)
    Button leave;
    @Bind(R.id.report)
    Button report;
    @Bind(R.id.share)
    Button share;
    @Bind(R.id.setting)
    Button setting;
    @Bind(R.id.cancel)
    Button cancel;

    public static RoomOperationDialogFragment getInstance() {
        return new RoomOperationDialogFragment();
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

    @OnClick({R.id.leave, R.id.report, R.id.share, R.id.setting, R.id.cancel, R.id.content})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.leave:
                break;
            case R.id.report:
                break;
            case R.id.share:
                break;
            case R.id.setting:
                break;
            case R.id.cancel:
                break;
            case R.id.content:
                break;
        }
        dismissAllowingStateLoss();
    }
}
