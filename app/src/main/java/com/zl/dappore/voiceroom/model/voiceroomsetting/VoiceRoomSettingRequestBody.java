package com.zl.dappore.voiceroom.model.voiceroomsetting;

import com.zl.dappore.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;

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
