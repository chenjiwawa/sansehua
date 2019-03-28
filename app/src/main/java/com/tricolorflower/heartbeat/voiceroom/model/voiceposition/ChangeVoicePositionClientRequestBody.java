package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class ChangeVoicePositionClientRequestBody extends BaseVoiceRoomRequestBody {

    public int old_seat_position;
    public int new_seat_position;

    public ChangeVoicePositionClientRequestBody(String token, String room_id, int old_seat_position, int new_seat_position) {
        super(token, room_id);
        this.old_seat_position = old_seat_position;
        this.new_seat_position = new_seat_position;
    }

    @Override
    public String toString() {
        return "ChangeVoicePositionClientRequestBody{" +
                "old_seat_position=" + old_seat_position +
                ", new_seat_position=" + new_seat_position +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
