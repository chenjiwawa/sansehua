package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;

public class VoicePositionMicrophoneRequestBody extends BaseVoicePositionRequestBody {

    public int is_lock;

    public VoicePositionMicrophoneRequestBody(String token, String room_id, int seat_position, int is_lock) {
        super(token, room_id, seat_position);
        this.is_lock = is_lock;
    }

    @Override
    public String toString() {
        return "VoicePositionRequestBody{" +
                "is_lock=" + is_lock +
                ", seat_position=" + seat_position +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
