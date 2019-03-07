package com.zl.dappore.common.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.qsmaxmin.qsbase.common.widget.dialog.QsProgressDialog;
import com.zl.dappore.R;

public class CommonLoadingDialog extends QsProgressDialog {
    @Override
    protected int getDialogTheme() {
        return R.style.Qs_Dialog_Simple;
    }

    @Override
    protected void setAttribute(WindowManager.LayoutParams params) {
        super.setAttribute(params);
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.dialog_common_loading, viewGroup);
    }
}
