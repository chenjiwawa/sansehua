package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.ChatRoomHttp;
import com.tricolorflower.heartbeat.common.http.VoiceRoomHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.RoomOperationFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.chatroom.ChatRoomInputMessageFragment;
import com.tricolorflower.heartbeat.voiceroom.model.chatroom.ChatRoomInputMessageRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class ChatRoomInputMessagePresenter extends DapporePresenter<ChatRoomInputMessageFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData() {
    }

    @ThreadPoint(ThreadType.HTTP)
    public void sendMessage(ChatRoomInputMessageRequestBody requestBody) {
        ChatRoomHttp http = createHttpRequest(ChatRoomHttp.class);
        BaseModel response = http.sendMessage(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setSuccessView();
        } else {
        }
    }

}
