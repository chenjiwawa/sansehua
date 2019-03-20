package com.zl.dappore.voiceroom.model.voiceroomsetting;

public class VoiceRoomLabelSettingRequestBody extends VoiceRoomSettingRequestBody {

    public String tag;

    public VoiceRoomLabelSettingRequestBody(String token, String room_id, String tag) {
        super(token, room_id);
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "VoiceRoomNameSettingRequestBody{" +
                "tag='" + tag + '\'' +
                ", token='" + token + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
