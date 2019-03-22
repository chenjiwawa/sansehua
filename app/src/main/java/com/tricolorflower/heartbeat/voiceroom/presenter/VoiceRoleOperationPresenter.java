package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
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

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoleOperationPresenter extends DapporePresenter<VoiceRoleOperationFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(SendProductRequestBody requestBody) {
        ProductHttp http = createHttpRequest(ProductHttp.class);
        BaseModel response = http.sendProduct(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePosition(VoicePositionRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePositionMusicPermission(VoicePositionMusicPermissionRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePositionMusicPermission(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void setVoicePositionMicrophone(VoicePositionMicrophoneRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.setVoicePositionMicrophone(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void loginVoicePosition(VoicePositionClientRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.loginVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void logoutVoicePosition(VoicePositionClientRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.logoutVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void kickOutVoicePosition(BaseVoiceRoleRequestBody requestBody) {
        VoicePositionHttp http = createHttpRequest(VoicePositionHttp.class);
        BaseModel response = http.kickOutVoicePosition(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
        } else {
        }
    }


}
