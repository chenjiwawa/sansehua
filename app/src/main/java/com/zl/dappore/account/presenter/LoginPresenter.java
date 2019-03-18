package com.zl.dappore.account.presenter;

import android.os.Bundle;

import com.zl.dappore.account.LoginActivity;
import com.zl.dappore.account.model.CheckCodeRequstBody;
import com.zl.dappore.account.model.LoginConstant;
import com.zl.dappore.account.model.LoginRequstBody;
import com.zl.dappore.account.model.RegisteRequstBody;
import com.zl.dappore.account.model.UserResponse;
import com.zl.dappore.common.event.LoginEvent;
import com.zl.dappore.common.http.UserInfoHttp;
import com.zl.dappore.common.model.AppConfig;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.config.PropertyCallback;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;


public class LoginPresenter extends DapporePresenter<LoginActivity> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestLogin(String id, String psd, Bundle arguments) {
        final int requestCode = arguments.getInt(LoginConstant.LoginActivity_RequestCode);
        UserInfoHttp userInfoHttp = createHttpRequest(UserInfoHttp.class);
        UserResponse userResponse = userInfoHttp.requestLogin(new LoginRequstBody(id, psd));

        L.i(initTag(), "requestLogin userResponse " + userResponse);

        if (isSuccess(userResponse) && userResponse.user != null) {
            UserConfig.getInstance().logout(new PropertyCallback() {
                @Override
                public void onSuccess() {
                    AppConfig.getInstance().updateCurrentUserId(userResponse.user.id, new PropertyCallback() {
                        @Override
                        public void onSuccess() {
                            UserConfig.getInstance().updateUserInfo(userResponse.user);
                            postLoginEvent(LoginEvent.LoginState.STATE_SUCCESS, requestCode);
                        }
                    });
                }
            });//切记置空

            getView().setLoginSuceessView();
        } else {
            if (userResponse == null) {
                QsToast.show("登录失败！");
            } else {
                showFailMsg(userResponse);
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestRegiste(String id, String code, String psd, Bundle arguments) {
        final int requestCode = arguments.getInt(LoginConstant.LoginActivity_RequestCode);
        UserInfoHttp userInfoHttp = createHttpRequest(UserInfoHttp.class);
        UserResponse userResponse = userInfoHttp.requestRegiste(new RegisteRequstBody(id, psd, code, null));

        L.i(initTag(), "requestRegiste userResponse " + userResponse);

        if (isSuccess(userResponse) && userResponse.user != null) {
            UserConfig.getInstance().logout(new PropertyCallback() {
                @Override
                public void onSuccess() {
                    AppConfig.getInstance().updateCurrentUserId(userResponse.user.id, new PropertyCallback() {
                        @Override
                        public void onSuccess() {

                            L.i(initTag(), "requestRegiste onSuccess ");
                            UserConfig.getInstance().updateUserInfo(userResponse.user);
                            postLoginEvent(LoginEvent.LoginState.STATE_SUCCESS, requestCode);
                        }
                    });
                }
            });//切记置空

            getView().setRegisteSuceessView();
        } else {
            if (userResponse == null) {
                QsToast.show("注册失败！");
            } else {
                showFailMsg(userResponse);
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestCheckCode(String id) {

        UserInfoHttp userInfoHttp = createHttpRequest(UserInfoHttp.class);
        BaseModel baseModel = userInfoHttp.requestCheckCode(new CheckCodeRequstBody(id));

        L.i(initTag(), "requestCheckCode baseModel " + baseModel);

        if (isSuccess(baseModel)) {
        } else {
            if (baseModel == null) {
                QsToast.show("获取验证码失败！");
            } else {
                showFailMsg(baseModel);
            }
        }
    }

    private void postLoginEvent(LoginEvent.LoginState state, int requestCode) {
        L.i(initTag(), "postLoginEvent state " + state + " requestCode " + requestCode);
        QsHelper.getInstance().eventPost(new LoginEvent.onLogin(state, requestCode));
    }

}