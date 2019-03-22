package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

public class VoiceRoomNameSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String room_name;

    public VoiceRoomNameSettingRequestBody(String token, String room_id, String room_name) {
        super(token, room_id);
        this.room_name = room_name;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "room_name='" + room_name + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
