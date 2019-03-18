package com.zl.dappore.voiceroom.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class VoiceRoomNameRequestBody extends VoiceRoomRequestBody {

    public String room_name;

    public VoiceRoomNameRequestBody(String token, String room_id, String room_name) {
        super(token, room_id);
        this.room_name = room_name;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "room_name='" + room_name + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
