package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class KickoutRequestBody extends BaseVoiceRoomRequestBody {

    public int uid;

    public KickoutRequestBody(String token, String room_id, int uid) {
        super(token, room_id);
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "KickoutRequestBody{" +
                "uid='" + uid + '\'' +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

}
