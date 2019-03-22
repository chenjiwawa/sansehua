package com.tricolorflower.heartbeat.addadminlist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.addadminlist.fragment.AddAdminListFragment;
import com.tricolorflower.heartbeat.common.http.AdminListHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoleListHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseSearchRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleRequestBody;


public class AddAdminListPresenter extends DapporePresenter<AddAdminListFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void addAdmin(String token, String uid) {
        AdminListHttp http = createHttpRequest(AdminListHttp.class);
        BaseModel response = http.addAdmin(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {

        } else {

        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void searchAdmin(String token, String namestr) {
        AdminListHttp http = createHttpRequest(AdminListHttp.class);
        VoiceRoleList response = http.searchAdmin(new BaseSearchRequestBody(token, namestr));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setData(response.data);
        } else {
            getView().showErrorView();
        }
    }
}