package com.zl.dappore.voiceroom.model.voicerole;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class SendProductRequestBody extends QsModel {

    public String token;
    public String gift_id;
    public int number;
    public String room_id;
    public String muid;

    public SendProductRequestBody() {
    }

    public SendProductRequestBody(String token, String gift_id, int number, String room_id, String muid) {
        this.token = token;
        this.gift_id = gift_id;
        this.number = number;
        this.room_id = room_id;
        this.muid = muid;
    }

    @Override
    public String toString() {
        return "SendProductRequestBody{" +
                "token='" + token + '\'' +
                ", gift_id='" + gift_id + '\'' +
                ", number=" + number +
                ", room_id='" + room_id + '\'' +
                ", muid='" + muid + '\'' +
                '}';
    }
}
