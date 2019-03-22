package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;

public class VoicePositionClientRequestBody extends BaseVoicePositionRequestBody {

    public String uid;

    public VoicePositionClientRequestBody(String token, String room_id, int seat_position, String uid) {
        super(token, room_id, seat_position);
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "VoicePositionClientRequestBody{" +
                "uid='" + uid + '\'' +
                ", seat_position=" + seat_position +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
