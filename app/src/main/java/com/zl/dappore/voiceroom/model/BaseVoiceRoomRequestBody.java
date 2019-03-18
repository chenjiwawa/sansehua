package com.zl.dappore.voiceroom.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoomRequestBody extends QsModel {

    protected String token;

    public BaseVoiceRoomRequestBody(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BaseVoiceRoomRequestBody{" +
                "token='" + token + '\'' +
                '}';
    }
}
