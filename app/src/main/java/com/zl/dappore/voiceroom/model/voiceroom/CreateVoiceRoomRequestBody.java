package com.zl.dappore.voiceroom.model.voiceroom;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class CreateVoiceRoomRequestBody extends QsModel {

    public String token;
    public String room_type;

    public CreateVoiceRoomRequestBody(String token, String room_type) {
        this.token = token;
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        return "CreateVoiceRoomRequestBody{" +
                "token='" + token + '\'' +
                ", room_type='" + room_type + '\'' +
                '}';
    }
}
