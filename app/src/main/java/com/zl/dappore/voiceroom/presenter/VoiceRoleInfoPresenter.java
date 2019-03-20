package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.VoiceRoleHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRoleRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoleInfoPresenter extends DapporePresenter<VoiceRoleInfoFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String token, String uid) {
        VoiceRoleHttp http = createHttpRequest(VoiceRoleHttp.class);
        VoiceRoleList.VoiceRole response = http.requestVoiceRoleInfo(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setVoiceRoleInfoView(response);
        } else {
        }
    }
}
