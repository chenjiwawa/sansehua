package com.tricolorflower.heartbeat.common.model;

public class BaseVoicePositionRequestBody extends BaseVoiceRoomRequestBody {

    public int seat_position;

    public BaseVoicePositionRequestBody(String token, String room_id, int seat_position) {
        super(token, room_id);
        this.seat_position = seat_position;
    }

    @Override
    public String toString() {
        return "BaseVoicePositionRequestBody{" +
                "seat_position=" + seat_position +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
