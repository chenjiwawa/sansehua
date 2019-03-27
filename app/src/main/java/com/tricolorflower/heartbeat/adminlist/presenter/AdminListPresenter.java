package com.tricolorflower.heartbeat.adminlist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.adminlist.fragment.AdminListFragment;
import com.tricolorflower.heartbeat.common.http.AdminListHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoleListHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseSearchRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleRequestBody;


public class AdminListPresenter extends DapporePresenter<AdminListFragment> {


    @ThreadPoint(ThreadType.HTTP)
    public void requestAdminList(String token) {
        AdminListHttp http = createHttpRequest(AdminListHttp.class);
        VoiceRoleList response = http.requestAdminList(new BaseRequstBody(token));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setData(response.data);
        } else {
            getView().showErrorView();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void deleteAdmin(String token, int uid) {
        AdminListHttp http = createHttpRequest(AdminListHttp.class);
        BaseModel response = http.deleteAdmin(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {
            requestAdminList(token);
        } else {

        }
    }

}