package com.zl.dappore.voiceroom.model;

public class VoiceRoomTypeRequestBody extends VoiceRoomRequestBody {

    public String room_type;

    public VoiceRoomTypeRequestBody(String token, String room_id, String room_type) {
        super(token, room_id);
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "room_type='" + room_type + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
