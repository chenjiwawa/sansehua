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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentLinearLayout;
import com.zl.dappore.R;


public class UpdateDialog extends Dialog {
    @Bind(R.id.rl_frame_update)
    RelativeLayout rlFrameCommon;
    @Bind(R.id.tv_verson_name_update)
    TextView tvVersonNameUpdate;
    @Bind(R.id.tv_verson_des_update)
    TextView tvVersonDesUpdate;
    @Bind(R.id.ll_content_update)
    LinearLayout llContentUpdate;
    @Bind(R.id.v_divider_update)
    View vDividerUpdate;
    @Bind(R.id.ll_confirm_update)
    PercentLinearLayout llConfirmUpdate;
    @Bind(R.id.ll_cancel_update)
    PercentLinearLayout llCancelUpdate;
    @Bind(R.id.tv_title_update)
    TextView tvTitleUpdate;
    private String TAG = this.getClass().getSimpleName();

    private OnDialogListener mListener;
    private Context context;

    public UpdateDialog(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static UpdateDialog getInstance(Context context) {
        return new UpdateDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        ViewBindHelper.bindView(this, view);
        setContentView(view);
        setView(view);

    }

    public void setTitle(String title) {
        tvTitleUpdate.setText(title);
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

    @OnClick({R.id.ll_confirm_update, R.id.ll_cancel_update, R.id.rl_frame_update})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_confirm_update:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                break;
            case R.id.ll_cancel_update:
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            case R.id.rl_frame_update:
                break;
        }
        dismiss();
    }

    public interface OnDialogListener {

        void onConfirm();

        void onCancel();

        void onDismiss(DialogInterface dialog);
    }

    public void setOnShareListener(OnDialogListener listener) {
        this.mListener = listener;
    }
}
