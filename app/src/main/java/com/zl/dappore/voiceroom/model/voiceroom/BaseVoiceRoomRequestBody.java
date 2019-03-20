package com.zl.dappore.voiceroom.model.voiceroom;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoomRequestBody extends QsModel {

    public String token;
    public String room_id;

    public BaseVoiceRoomRequestBody(String token, String room_id) {
        this.token = token;
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "CreateVoiceRoomRequestBody{" +
                "token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
