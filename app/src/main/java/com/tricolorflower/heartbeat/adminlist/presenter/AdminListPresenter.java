package com.tricolorflower.heartbeat.adminlist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.adminlist.fragment.AdminListFragment;
import com.tricolorflower.heartbeat.common.http.VoiceRoleListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleRequestBody;


public class AdminListPresenter extends DapporePresenter<AdminListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestVoiceRoleList(boolean isLoadingMore, String token, String uid) {
        VoiceRoleListHttp http = createHttpRequest(VoiceRoleListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VoiceRoleList response = http.requestVoiceRoleList(new VoiceRoleRequestBody(token, uid, page));
            showFailMsg(response);
            if (isSuccess(response) && response.data != null) {
                page++;
                getView().addData(response.data);
                paging(response);
            }
        } else {
            page = 1;
            VoiceRoleList response = http.requestVoiceRoleList(new VoiceRoleRequestBody(token, uid, page));
            showFailMsg(response);
            if (isSuccess(response) && response.data != null) {
                page = 2;
                getView().setData(response.data);
                paging(response);
            } else {
                getView().showErrorView();
            }
        }
    }

}