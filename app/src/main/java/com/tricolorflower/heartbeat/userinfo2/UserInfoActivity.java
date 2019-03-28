package com.tricolorflower.heartbeat.userinfo2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.account.presenter.LoginPresenter;
import com.tricolorflower.heartbeat.common.event.LoginEvent;
import com.tricolorflower.heartbeat.common.model.AppConfig;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.qsmaxmin.qsbase.common.config.PropertyCallback;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * @CreatedBy zhuanggy
 * @Date 16/8/3
 * @Description 关于我们
 */
public class UserInfoActivity extends QsActivity<LoginPresenter> {

    @Bind(R.id.iv_logo_userinfo)
    ImageView ivLogoUserinfo;
    @Bind(R.id.tv_name_userinfo)
    TextView tvNameUserinfo;
    @Bind(R.id.rl_content_userinfo)
    RelativeLayout rlContentUserinfo;
    @Bind(R.id.btn_logout_userinfo)
    Button btnLogoutUserinfo;

    @Override
    public int layoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initData(Bundle bundle) {

        setUserInfoView();
    }

    public void setUserInfoView() {
        QsHelper.getInstance().getImageHelper().createRequest().load(UserConfig.getInstance().getAvatarUrl()).circleCrop().into(ivLogoUserinfo);
        tvNameUserinfo.setText(UserConfig.getInstance().getName());
    }

    @OnClick({R.id.btn_logout_userinfo})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.btn_logout_userinfo:

                AppConfig.getInstance().updateCurrentUserId(0, new PropertyCallback() {
                    @Override public void onSuccess() {
                        UserConfig.getInstance().logout(new PropertyCallback() {
                            @Override public void onSuccess() {
                                QsHelper.getInstance().eventPost(new LoginEvent.onLogin(LoginEvent.LoginState.STATE_LOGOUT, 0));
                                QsToast.show("退出成功");
                                activityFinish();
                            }
                        });
                    }
                });
                break;
        }
    }

    @Subscribe
    public void onEvent(LoginEvent.onLogin event) {
        if (event.mLoginState == LoginEvent.LoginState.STATE_SUCCESS) {
            setUserInfoView();
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}
