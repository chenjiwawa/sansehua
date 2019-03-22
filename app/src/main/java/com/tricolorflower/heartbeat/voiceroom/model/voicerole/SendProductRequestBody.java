package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class SendProductRequestBody extends BaseVoiceRoomRequestBody {

    public String gift_id;
    public int number;
    public String muid;

    public SendProductRequestBody() {
        super();
    }

    public SendProductRequestBody(String token, String room_id, String gift_id, int number, String muid) {
        super(token, room_id);
        this.gift_id = gift_id;
        this.number = number;
        this.muid = muid;
    }

    @Override
    public String toString() {
        return "SendProductRequestBody{" +
                "gift_id='" + gift_id + '\'' +
                ", number=" + number +
                ", muid='" + muid + '\'' +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
