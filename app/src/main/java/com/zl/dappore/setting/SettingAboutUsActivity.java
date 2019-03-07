package com.zl.dappore.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.setting.presenter.SettingAboutUsPresenter;

/**
 * @CreatedBy zhuanggy
 * @Date 16/8/3
 * @Description 关于我们
 */
public class SettingAboutUsActivity extends QsABActivity<SettingAboutUsPresenter> {


    @Bind(R.id.iv_icon_aboutus)
    ImageView ivIconAboutus;
    @Bind(R.id.tv_name_aboutus)
    TextView tvNameAboutus;
    @Bind(R.id.iv_qrcode_aboutus)
    ImageView ivQrcodeAboutus;
    @Bind(R.id.btn_contact_aboutus)
    Button btnContactAboutus;
    @Bind(R.id.btn_update_aboutus)
    Button btnUpdateAboutus;
    @Bind(R.id.tv_protocal_aboutus)
    TextView tvProtocalAboutus;
    @Bind(R.id.tv_privacy_aboutus)
    TextView tvPrivacyAboutus;
    @Bind(android.R.id.title)
    TextView title;

    @Override
    public void initData(Bundle savedInstanceState) {
        title.setText(QsHelper.getInstance().getString(R.string.setting_aboutus_title));
    }

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_setting_aboutus;
    }



    @OnClick({R.id.layout_title_back,R.id.btn_contact_aboutus, R.id.btn_update_aboutus, R.id.tv_protocal_aboutus, R.id.tv_privacy_aboutus})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.layout_title_back:
                activityFinish();
                break;
            case R.id.btn_contact_aboutus:
                break;
            case R.id.btn_update_aboutus:
                getPresenter().checkUpdate(true);
                break;
            case R.id.tv_protocal_aboutus:
                break;
            case R.id.tv_privacy_aboutus:
                break;
        }
    }
}
