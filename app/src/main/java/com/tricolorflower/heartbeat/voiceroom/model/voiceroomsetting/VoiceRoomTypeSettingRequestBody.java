package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomSettingRequestBody;

public class VoiceRoomTypeSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String room_type;

    public VoiceRoomTypeSettingRequestBody(String token, String room_id, String room_type) {
        super(token, room_id);
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "room_type='" + room_type + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
