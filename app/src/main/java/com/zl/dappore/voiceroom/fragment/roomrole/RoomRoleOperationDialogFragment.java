package com.zl.dappore.voiceroom.fragment.roomrole;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;

public class RoomRoleOperationDialogFragment extends QsDialogFragment {
    @Bind(R.id.content)
    RelativeLayout content;

    public static RoomRoleOperationDialogFragment getInstance() {
        return new RoomRoleOperationDialogFragment();
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

    public void showLogoAnimView() {
    }

    public void showAddView() {
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
