package com.zl.dappore.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentLinearLayout;
import com.zl.dappore.R;

public class SimpleDialog extends Dialog {

    @Bind(R.id.tv_title_simple)
    TextView tvTitleSimple;
    @Bind(R.id.ll_confirm_simple)
    PercentLinearLayout llConfirmSimple;
    @Bind(R.id.ll_cancel_simple)
    PercentLinearLayout llCancelSimple;
    @Bind(R.id.rl_frame_simple)
    RelativeLayout rlFrameSimple;
    private String TAG = this.getClass().getSimpleName();

    private OnDialogListener mListener;
    private Context context;

    public SimpleDialog(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static UpdateDialog getInstance(Context context) {
        return new UpdateDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple, null);
        ViewBindHelper.bindView(this, view);
        setContentView(view);
        setView(view);

    }

    public void setTitle(String title) {
        tvTitleSimple.setText(title);
    }

    public void setListener(OnDialogListener mListener) {
        this.mListener = mListener;
    }

    private void setView(View view) {
        if (context == null)
            return;

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mListener != null) {
                    mListener.onDismiss(dialog);
                }
            }
        });
    }

    @OnClick({R.id.ll_confirm_simple, R.id.ll_cancel_simple, R.id.rl_frame_simple})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_confirm_simple:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                break;
            case R.id.ll_cancel_simple:
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            case R.id.rl_frame_simple:
                break;
        }
        dismiss();
    }

    public interface OnDialogListener {

        void onConfirm();

        void onCancel();

        void onDismiss(DialogInterface dialog);
    }
}
