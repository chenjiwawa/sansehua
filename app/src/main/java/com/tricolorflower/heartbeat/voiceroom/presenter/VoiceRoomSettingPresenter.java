package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoomHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoomSettingHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.VoiceRoomSettingActivity;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.EnterVoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomGreetingSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomLabelSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomLogoSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomNameSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomPwdSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomTypeSettingRequestBody;

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
            getView().setFailView();
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
            getView().setFailView();
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void setVoiceRoomType(String token, String roomId, int type) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        BaseModel response = http.setVoiceRoomType(new VoiceRoomTypeSettingRequestBody(token, roomId, type));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccesView();
        } else {
            getView().setFailView();
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
            getView().setFailView();
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
            getView().setFailView();
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
            getView().setFailView();
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String token, String room_id) {
        VoiceRoomHttp http = createHttpRequest(VoiceRoomHttp.class);
        VoiceRoomResponse response = http.getVoiceRoomInfo(new BaseVoiceRoomRequestBody(token, room_id));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
        } else {
            getView().showErrorView();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {

    }
}
