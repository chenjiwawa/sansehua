package com.zl.dappore.voiceroom.model;

public class VoiceRoomLabelRequestBody extends VoiceRoomRequestBody {

    public String tag;

    public VoiceRoomLabelRequestBody(String token, String room_id, String tag) {
        super(token, room_id);
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "tag='" + tag + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
