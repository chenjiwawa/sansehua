package com.tricolorflower.heartbeat.blacklist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.blacklist.fragment.BlackListFragment;
import com.tricolorflower.heartbeat.common.http.BlackListHttp;
import com.tricolorflower.heartbeat.common.http.BlackListHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoleListHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseSearchRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleRequestBody;


public class BlackListPresenter extends DapporePresenter<BlackListFragment> {


    @ThreadPoint(ThreadType.HTTP)
    public void requestBlackList(String token) {
        BlackListHttp http = createHttpRequest(BlackListHttp.class);
        VoiceRoleList response = http.requestBlackList(new BaseRequstBody(token));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setData(response.data);
        } else {
            getView().showErrorView();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void deleteBlack(String token, String uid) {
        BlackListHttp http = createHttpRequest(BlackListHttp.class);
        BaseModel response = http.deleteBlack(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {

        } else {

        }
    }

}