package com.zl.dappore.voiceroom.model.voiceroomsetting;

public class VoiceRoomPwdSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String password;

    public VoiceRoomPwdSettingRequestBody(String token, String room_id, String password) {
        super(token, room_id);
        this.password = password;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
