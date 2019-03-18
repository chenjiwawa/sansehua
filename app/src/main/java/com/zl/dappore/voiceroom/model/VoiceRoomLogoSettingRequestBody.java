package com.zl.dappore.voiceroom.model;

public class VoiceRoomLogoSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String image;

    public VoiceRoomLogoSettingRequestBody(String token, String room_id, String image) {
        super(token, room_id);
        this.image = image;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "image='" + image + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
