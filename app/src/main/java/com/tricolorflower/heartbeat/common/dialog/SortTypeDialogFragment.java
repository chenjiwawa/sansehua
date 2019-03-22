package com.tricolorflower.heartbeat.common.dialog;

import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;

public class SortTypeDialogFragment extends QsDialogFragment implements View.OnClickListener {
    private OnSortTypeSelectedListener mListener;
    TextView tv_sort_type_new;
    TextView tv_sort_type_hot;
    TextView tv_sort_type_score;
    private String type = "";

    public static SortTypeDialogFragment getInstance() {
        return new SortTypeDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    public void setSortType(String type) {
        this.type = type;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.menu_sort_type, viewGroup);
        setView(view);
        return view;
    }

    private void setView(View view) {
        L.i(initTag(), " setView ");

        tv_sort_type_new = (TextView) view.findViewById(R.id.tv_sort_type_new);
        tv_sort_type_hot = (TextView) view.findViewById(R.id.tv_sort_type_hot);
        tv_sort_type_score = (TextView) view.findViewById(R.id.tv_sort_type_score);

        tv_sort_type_new.getPaint().setFakeBoldText(false);
        tv_sort_type_hot.getPaint().setFakeBoldText(false);
        tv_sort_type_score.getPaint().setFakeBoldText(false);

        L.i(initTag(), "type " + type + " " + getString(R.string.sort_type_new) + " " + getString(R.string.sort_type_hot) + " " + getString(R.string.sort_type_score));
        if (type.equals(getString(R.string.sort_type_new))) {
            tv_sort_type_new.getPaint().setFakeBoldText(true);
        } else if (type.equals(getString(R.string.sort_type_hot))) {
            tv_sort_type_hot.getPaint().setFakeBoldText(true);
        } else {
            tv_sort_type_score.getPaint().setFakeBoldText(true);
        }

        tv_sort_type_new.setOnClickListener(this);
        tv_sort_type_hot.setOnClickListener(this);
        tv_sort_type_score.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mListener != null) {
                    mListener.onDismiss(dialog);
                }
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
//        dismissAllowingStateLoss();
        dismiss();
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

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = CommonUtils.dp2px(10);
        params.y = CommonUtils.dp2px(108);
        getDialog().getWindow().setAttributes(params);
    }

    public interface OnSortTypeSelectedListener {
        void onSortTypeSelected(View view);

        void onDismiss(DialogInterface dialog);
    }

    public void setOnSortTypeSelectedListener(OnSortTypeSelectedListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        type="";
    }
}
