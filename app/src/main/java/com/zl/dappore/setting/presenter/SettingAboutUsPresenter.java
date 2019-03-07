package com.zl.dappore.setting.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.zl.dappore.R;
import com.zl.dappore.common.http.SettingHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.common.utils.AppUpdateUtil;
import com.zl.dappore.setting.SettingAboutUsActivity;
import com.zl.dappore.setting.model.CheckUpdateResponse;


/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class SettingAboutUsPresenter extends DapporePresenter<SettingAboutUsActivity>{

    private SettingHttp http;

    @ThreadPoint(ThreadType.HTTP)
    public void checkUpdate(boolean isShowDialog) {
        if (isShowDialog)
            getView().loading(QsHelper.getInstance().getString(R.string.update_checking), true);
        if (http == null) {
            http = createHttpRequest(SettingHttp.class);
        }
        BaseModel body = new BaseModel();
        CheckUpdateResponse response = http.checkUpdate(body);
        getView().loadingClose();

        if (isSuccess(response)) {
            L.i(initTag(),"check update success");
            AppUpdateUtil.checkUpdateResult(response, isShowDialog);
        } else {
            L.i(initTag(),"check update failed");
            QsToast.show(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_connect_fail));
        }
    }
}
