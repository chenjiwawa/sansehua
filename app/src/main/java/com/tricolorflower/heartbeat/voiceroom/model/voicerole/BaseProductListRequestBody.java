package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseProductListRequestBody extends QsModel {

    public String token;
    public int page;

    public BaseProductListRequestBody(String token, int page) {
        this.token = token;
        this.page = page;
    }

    @Override
    public String toString() {
        return "CreateVoiceRoomRequestBody{" +
                "token='" + token + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
