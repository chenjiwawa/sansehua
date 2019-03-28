package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoomHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.RoomOperationFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.VoiceRoomFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.CreateVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.EnterVoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class RoomOperationPresenter extends DapporePresenter<RoomOperationFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData() {
    }

    @ThreadPoint(ThreadType.HTTP)
    public void leaveVoiceRoom(String token, String room_id) {
        VoiceRoomHttp http = createHttpRequest(VoiceRoomHttp.class);
        BaseModel response = http.leaveVoiceRoom(new BaseVoiceRoomRequestBody(token, room_id));
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccessView();
        } else {
        }
    }

}
