package com.tricolorflower.heartbeat.setting.presenter;

import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.common.http.FeedbackHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.setting.SettingFeedbackActivity;
import com.tricolorflower.heartbeat.setting.model.FeedbackRequstBody;

/**
 * @CreateBy zhaunggy
 * @Date 16/8/3
 * @Description
 */
public class SettingFeedbackPresenter extends DapporePresenter<SettingFeedbackActivity> {

    private FeedbackHttp http;

    @ThreadPoint(ThreadType.HTTP)
    public void doFeedback(String info, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        getView().loading(true);
        if (http == null) {
            http = createHttpRequest(FeedbackHttp.class);
        }
        FeedbackRequstBody req = new FeedbackRequstBody();
        req.parMap = new FeedbackRequstBody.ParMap();
        req.parMap.feedback = content;
        req.parMap.link = info;
        req.parMap.userId = UserConfig.getInstance().getId() + "";


        BaseModel result = http.doFeedback(req);
        getView().loadingClose();

        if (isSuccess(result)) {
            QsToast.show("感谢您的反馈!");
            getView().activityFinish();
        } else {
            QsToast.show("提交失败");
        }
    }

}
