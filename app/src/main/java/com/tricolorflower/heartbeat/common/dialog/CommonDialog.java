package com.tricolorflower.heartbeat.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.tricolorflower.heartbeat.R;


public class CommonDialog extends Dialog {
    @Bind(R.id.tv_title_common)
    TextView tvTitleCommon;
    @Bind(R.id.btn_confirm_common)
    Button btnConfirmCommon;
    @Bind(R.id.btn_cancel_common)
    Button btnCancelCommon;
    @Bind(R.id.rl_frame_common)
    RelativeLayout rlFrameCommon;
    private String TAG = this.getClass().getSimpleName();

    private OnDialogListener mListener;
    private Context context;
    private String title;

    public CommonDialog(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static CommonDialog getInstance(Context context) {
        return new CommonDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
        ViewBindHelper.bindView(this, view);
        setContentView(view);
        setView(view);
    }

    public void setTitle(String title) {
//        this.title = title;
        if (tvTitleCommon != null) {
            tvTitleCommon.setText(title);
        }
    }

    public void setTitleVisible(boolean visible) {
        if (tvTitleCommon != null) {
            tvTitleCommon.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
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

    @OnClick({R.id.btn_confirm_common, R.id.btn_cancel_common, R.id.rl_frame_common})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm_common:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                break;
            case R.id.btn_cancel_common:
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            case R.id.rl_frame_common:
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
