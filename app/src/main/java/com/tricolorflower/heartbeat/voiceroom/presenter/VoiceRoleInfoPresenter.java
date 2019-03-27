package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoleHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoFragment;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRoleResponse;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoleInfoPresenter extends DapporePresenter<VoiceRoleInfoFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String token, int uid) {
        VoiceRoleHttp http = createHttpRequest(VoiceRoleHttp.class);
        VoiceRoleResponse response = http.requestVoiceRoleInfo(new BaseVoiceRoleRequestBody(token, uid));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setVoiceRoleInfoView(response.data);
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requstData2(String token, int uid) {

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            getView().setVoiceRoleInfoView(null);
        }

    }
}
