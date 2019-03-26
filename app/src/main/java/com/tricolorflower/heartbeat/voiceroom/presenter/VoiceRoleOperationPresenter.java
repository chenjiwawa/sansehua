package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.http.VoicePositionHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.SendProductFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionClientRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMicrophoneRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMusicPermissionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;

import static com.tricolorflower.heartbeat.common.event.VoiceRoomEvent.State.FRESH;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoleOperationPresenter extends DapporePresenter<VoiceRoleOperationFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData() {
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePosition(VoicePositionRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePositionMusicPermission(VoicePositionMusicPermissionRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePositionMusicPermission(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePositionMicrophone(VoicePositionMicrophoneRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePositionMicrophone(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void loginVoicePosition(VoicePositionClientRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.loginVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void logoutVoicePosition(VoicePositionClientRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.logoutVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void changeVoicePosition(VoicePositionClientRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.logoutVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            response = http.loginVoicePosition(requestBody);
            showFailMsg(response);
            if (isSuccess(response)) {
                eventPostSuccess();
            } else {
            }
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void kickOutVoicePosition(BaseVoiceRoleRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.kickOutVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            eventPostSuccess();
        } else {
        }
    }

    public void eventPostSuccess() {
        QsHelper.getInstance().eventPost(new VoiceRoleOperationEvent.OnDialogFragment(VoiceRoleOperationEvent.OnDialogFragment.State.DIDMISS));
        QsHelper.getInstance().eventPost(new VoiceRoomEvent(FRESH));
    }

}
