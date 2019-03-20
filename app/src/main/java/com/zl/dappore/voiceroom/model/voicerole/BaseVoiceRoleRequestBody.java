package com.zl.dappore.voiceroom.model.voicerole;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoleRequestBody extends QsModel {

    public String token;
    public String uid;

    public BaseVoiceRoleRequestBody(String token, String uid) {
        this.token = token;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CreateVoiceRoomRequestBody{" +
                "token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
