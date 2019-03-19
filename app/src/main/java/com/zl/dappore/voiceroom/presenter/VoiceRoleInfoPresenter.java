package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.VoiceRoleHttp;
import com.zl.dappore.common.http.VoiceRoomSettingHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voicerolelist.model.VoiceRoleRequestBody;
import com.zl.dappore.voiceroom.VoiceRoomSettingActivity;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceRoleInfoFragment;
import com.zl.dappore.voiceroom.model.BaseVoiceRoleRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomGreetingSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLabelSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLogoSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomNameSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomPwdSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomTypeSettingRequestBody;

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
