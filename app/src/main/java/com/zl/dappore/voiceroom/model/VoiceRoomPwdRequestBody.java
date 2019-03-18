package com.zl.dappore.voiceroom.model;

public class VoiceRoomPwdRequestBody extends VoiceRoomRequestBody {

    public String password;

    public VoiceRoomPwdRequestBody(String token, String room_id, String password) {
        super(token, room_id);
        this.password = password;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
