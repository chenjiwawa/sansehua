package com.zl.dappore.voiceroom.model.voiceroomsetting;

public class VoiceRoomGreetingSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String notice;

    public VoiceRoomGreetingSettingRequestBody(String token, String room_id, String notice) {
        super(token, room_id);
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "notice='" + notice + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
