package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;

public class VoicePositionMusicPermissionRequestBody extends BaseVoicePositionRequestBody {

    public int turn_music;

    public VoicePositionMusicPermissionRequestBody(String token, String room_id, int seat_position, int turn_music) {
        super(token, room_id, seat_position);
        this.turn_music = turn_music;
    }

    @Override
    public String toString() {
        return "VoicePositionMusicPermissionRequestBody{" +
                "turn_music=" + turn_music +
                ", seat_position=" + seat_position +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
