package com.tricolorflower.heartbeat.userinfo.presenter;

import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.common.http.UserInfoHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.userinfo.fragment.ChangeNickFragment;
import com.tricolorflower.heartbeat.userinfo.model.UserEditRequstBody;


public class ChangeNicknamePresenter extends DapporePresenter<ChangeNickFragment> {
    /**
     * 提交更改昵称
     */
    @ThreadPoint(ThreadType.HTTP)
    public void requestChangeNick(String account, String nick) {
        if (TextUtils.isEmpty(nick)) {
            QsToast.show("昵称为空！");
            return;
        }
        getView().loading(true);
        UserInfoHttp http = createHttpRequest(UserInfoHttp.class);
        UserEditRequstBody body = new UserEditRequstBody(nick, null, null, null, null);
        BaseModel result = http.requestUserEdit(account, body);
        getView().loadingClose();
        if (isSuccess(result)) {
            L.i(initTag(), "requestChangeNick  success" + result.toString());
            QsToast.show("修改成功！");
            UserConfig.getInstance().setName(nick);
            getView().requestBackPress();
        } else {
            QsToast.show("修改失败！");
        }
    }
}
