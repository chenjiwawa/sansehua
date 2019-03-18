package com.zl.dappore.voiceroom.model;

public class VoiceRoomGreetingRequestBody extends VoiceRoomRequestBody {

    public String notice;

    public VoiceRoomGreetingRequestBody(String token, String room_id, String notice) {
        super(token, room_id);
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "notice='" + notice + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
