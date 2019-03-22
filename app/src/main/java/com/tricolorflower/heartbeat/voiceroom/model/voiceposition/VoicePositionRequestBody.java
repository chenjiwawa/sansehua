package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class VoicePositionRequestBody extends BaseVoicePositionRequestBody {

    public int is_lock;

    public VoicePositionRequestBody(String token, String room_id, int seat_position, int is_lock) {
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
