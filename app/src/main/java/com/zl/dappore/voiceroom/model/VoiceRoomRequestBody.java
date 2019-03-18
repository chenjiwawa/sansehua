package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;
import com.qsmaxmin.qsbase.common.model.QsModel;

public class VoiceRoomRequestBody extends BaseVoiceRoomRequestBody {

    protected String room_id;

    public VoiceRoomRequestBody(String token, String room_id) {
        super(token);
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "VoiceRoomRequestBody{" +
                "room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
