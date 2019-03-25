package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoleHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoleInfoPresenter extends DapporePresenter<VoiceRoleInfoFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData2(String token, String uid) {
        VoiceRoleHttp http = createHttpRequest(VoiceRoleHttp.class);
        VoiceRoleList.VoiceRole response = http.requestVoiceRoleInfo(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setVoiceRoleInfoView(response);
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String token, String uid) {

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            getView().setVoiceRoleInfoView(null);
        }

    }
}
