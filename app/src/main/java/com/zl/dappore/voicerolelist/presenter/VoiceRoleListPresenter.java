package com.zl.dappore.voicerolelist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.VoiceRoleListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voicerolelist.fragment.VoiceRoleListFragment;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voicerolelist.model.VoiceRoleRequestBody;


public class VoiceRoleListPresenter extends DapporePresenter<VoiceRoleListFragment> {

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