package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.VoiceRoomSettingHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voiceroom.VoiceRoomSettingActivity;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceOperationFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomGreetingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLabelRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLogoRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomNameRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomPwdRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomTypeRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomSettingPresenter extends DapporePresenter<VoiceRoomSettingActivity> {

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomName(String token, String roomId, String name) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomName(new VoiceRoomNameRequestBody(token, roomId, name));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomLogo(String token, String roomId, String logo) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomLogo(new VoiceRoomLogoRequestBody(token, roomId, logo));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomType(String token, String roomId, String type) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomType(new VoiceRoomTypeRequestBody(token, roomId, type));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomPwd(String token, String roomId, String pwd) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomPwd(new VoiceRoomPwdRequestBody(token, roomId, pwd));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomGreeting(String token, String roomId, String notice) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomGreeting(new VoiceRoomGreetingRequestBody(token, roomId, notice));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomLabel(String token, String roomId, String tag) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomLabel(new VoiceRoomLabelRequestBody(token, roomId, tag));
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }

}
