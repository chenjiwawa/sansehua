package com.tricolorflower.heartbeat.common.model;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.umeng.socialize.media.Base;

public class BaseVoiceRoomRequestBody extends BaseRequstBody {

    public String room_id;

    public BaseVoiceRoomRequestBody() {
        super();
    }

    public BaseVoiceRoomRequestBody(String token, String room_id) {
        super(token);
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "BaseVoiceRoomRequestBody{" +
                "room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
