package com.tricolorflower.heartbeat.addblacklist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.addblacklist.fragment.AddBlackListFragment;
import com.tricolorflower.heartbeat.common.http.BlackListHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoleListHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleRequestBody;


public class AddBlackListPresenter extends DapporePresenter<AddBlackListFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void addBlack(String token, String uid) {
        BlackListHttp http = createHttpRequest(BlackListHttp.class);
        BaseModel response = http.addBlack(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {

        } else {

        }
    }

}