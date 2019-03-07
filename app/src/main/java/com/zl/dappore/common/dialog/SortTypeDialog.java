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
import android.widget.TextView;

import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;

public class SortTypeDialog extends Dialog implements View.OnClickListener {
    private OnSortTypeSelectedListener mListener;
    TextView tv_sort_type_new;
    TextView tv_sort_type_hot;
    TextView tv_sort_type_score;
    private String type = "";
    private Context context;

    public SortTypeDialog(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static SortTypeDialog getInstance(Context context) {
        return new SortTypeDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.menu_sort_type, null);
        setContentView(view);
        setView(view);

    }

    public void setSortType(String type) {
        this.type = type;
    }

    private void setView(View view) {
        if (context == null)
            return;

        tv_sort_type_new = (TextView) view.findViewById(R.id.tv_sort_type_new);
        tv_sort_type_hot = (TextView) view.findViewById(R.id.tv_sort_type_hot);
        tv_sort_type_score = (TextView) view.findViewById(R.id.tv_sort_type_score);

        tv_sort_type_new.getPaint().setFakeBoldText(false);
        tv_sort_type_hot.getPaint().setFakeBoldText(false);
        tv_sort_type_score.getPaint().setFakeBoldText(false);

        if (type.equals(context.getResources().getString(R.string.sort_type_new))) {
            tv_sort_type_new.getPaint().setFakeBoldText(true);
        } else if (type.equals(context.getResources().getString(R.string.sort_type_hot))) {
            tv_sort_type_hot.getPaint().setFakeBoldText(true);
        } else {
            tv_sort_type_score.getPaint().setFakeBoldText(true);
        }

        tv_sort_type_new.setOnClickListener(this);
        tv_sort_type_hot.setOnClickListener(this);
        tv_sort_type_score.setOnClickListener(this);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = CommonUtils.dp2px(10);
        params.y = CommonUtils.dp2px(108);
        getWindow().setAttributes(params);

        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mListener != null) {
                    mListener.onDismiss(dialog);
                }

                type = "";
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sort_type_new:
                if (mListener != null) {
                    mListener.onSortTypeSelected(v);
                }
                break;
            case R.id.tv_sort_type_hot:
                if (mListener != null) {
                    mListener.onSortTypeSelected(v);
                }
                break;
            case R.id.tv_sort_type_score:
                if (mListener != null) {
                    mListener.onSortTypeSelected(v);
                }
                break;
        }
        dismiss();
    }

    public interface OnSortTypeSelectedListener {
        void onSortTypeSelected(View view);

        void onDismiss(DialogInterface dialog);
    }

    public void setOnSortTypeSelectedListener(OnSortTypeSelectedListener listener) {
        this.mListener = listener;
    }
}
