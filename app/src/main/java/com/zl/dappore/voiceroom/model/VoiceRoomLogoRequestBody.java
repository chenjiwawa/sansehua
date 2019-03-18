package com.zl.dappore.voiceroom.model;

public class VoiceRoomLogoRequestBody extends VoiceRoomRequestBody {

    public String image;

    public VoiceRoomLogoRequestBody(String token, String room_id, String image) {
        super(token, room_id);
        this.image = image;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameRequestBody{" +
                "image='" + image + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
