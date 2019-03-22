package com.tricolorflower.heartbeat.voiceroom.model.voiceposition;

import com.tricolorflower.heartbeat.common.model.BaseVoicePositionRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class KickoutRequestBody extends BaseVoiceRoomRequestBody {

    public String uid;

    public KickoutRequestBody(String token, String room_id, String uid) {
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
