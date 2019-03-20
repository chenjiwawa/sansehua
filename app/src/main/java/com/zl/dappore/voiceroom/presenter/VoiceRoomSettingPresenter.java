package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.VoiceRoomSettingHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.VoiceRoomSettingActivity;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomGreetingSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomLabelSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomLogoSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomNameSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomPwdSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomTypeSettingRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomSettingPresenter extends DapporePresenter<VoiceRoomSettingActivity> {

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomName(String token, String roomId, String name) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomName(new VoiceRoomNameSettingRequestBody(token, roomId, name));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomLogo(String token, String roomId, String logo) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomLogo(new VoiceRoomLogoSettingRequestBody(token, roomId, logo));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomType(String token, String roomId, String type) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomType(new VoiceRoomTypeSettingRequestBody(token, roomId, type));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomPwd(String token, String roomId, String pwd) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomPwd(new VoiceRoomPwdSettingRequestBody(token, roomId, pwd));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomGreeting(String token, String roomId, String notice) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomGreeting(new VoiceRoomGreetingSettingRequestBody(token, roomId, notice));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomLabel(String token, String roomId, String tag) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomLabel(new VoiceRoomLabelSettingRequestBody(token, roomId, tag));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }
}
