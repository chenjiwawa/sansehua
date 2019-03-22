package com.tricolorflower.heartbeat.setting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.KeyboardHelper;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.setting.presenter.SettingFeedbackPresenter;

/**
 * @CreatedBy zhuanggy
 * @Date 16/8/3
 * @Description 设置页
 */
public class SettingFeedbackActivity extends QsABActivity<SettingFeedbackPresenter> implements TextWatcher {

    @Bind(android.R.id.title)         TextView tv_header;
    @Bind(R.id.edit_feedback_content)
    EditText et_content;
    @Bind(R.id.btn_feedback_commit)
    Button btn_feedback_commit;


    @Override
    public void initData(Bundle savedInstanceState) {
        et_content.addTextChangedListener(this);
        tv_header.setText(QsHelper.getInstance().getString(R.string.setting_feedback_title));
        KeyboardHelper.showSoftInputDelay(et_content);

    }

    @Override
    public int layoutId() {
        return R.layout.activity_setting_feedback;
    }


    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.layout_title_back, R.id.btn_feedback_commit}) public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.layout_title_back:
                activityFinish();
                break;
            case R.id.btn_feedback_commit:
                if (TextUtils.isEmpty(et_content.getText())) {
                    QsToast.show("请输入反馈内容");
                    return;
                }
                getPresenter().doFeedback("", et_content.getText().toString());
                break;
            default:
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (et_content.length() > 1000) {
            QsToast.show(getString(R.string.alert_maxlength));
        }
        btn_feedback_commit.setEnabled(!TextUtils.isEmpty(et_content.getText()));
    }
}
