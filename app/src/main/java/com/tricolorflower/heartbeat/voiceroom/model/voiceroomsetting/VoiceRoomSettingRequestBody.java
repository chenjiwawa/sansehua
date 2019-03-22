package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;

public class VoiceRoomSettingRequestBody extends BaseVoiceRoomSettingRequestBody {

    protected String room_id;

    public VoiceRoomSettingRequestBody(String token, String room_id) {
        super(token);
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "VoiceRoomSettingRequestBody{" +
                "room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
