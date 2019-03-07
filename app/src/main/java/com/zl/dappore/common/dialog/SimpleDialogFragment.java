package com.zl.dappore.common.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;

import butterknife.OnClick;

public class SimpleDialogFragment extends QsDialogFragment {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.image_header)
    ImageView imageHeader;
    private OnDialogListener mListener;
    private String mTitle = "提示";
    private String mMessage = "";
    private String mConfirm = QsHelper.getInstance().getString(R.string.ok);
    private String mCancel = QsHelper.getInstance().getString(R.string.cancel);
    private int mIconId = R.mipmap.icon_my_font;

    public static SimpleDialogFragment getInstance() {
        return new SimpleDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_fragment_simple, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        title.setText(mTitle);
        message.setText(mMessage);
        confirm.setText(mConfirm);
        cancel.setText(mCancel);
        imageHeader.setImageResource(mIconId);
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setMessage(String message) {
        this.mTitle = message;
    }

    public void setCancel(String cancel) {
        this.mCancel = cancel;
    }

    public void setConfirm(String confirm) {
        this.mConfirm = confirm;
    }

    @Override
    public void onStart() {
        super.onStart();
        L.i(initTag(), " onStart ");

        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.cancel:
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            case R.id.confirm:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    public interface OnDialogListener {
        void onConfirm();

        void onCancel();
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
