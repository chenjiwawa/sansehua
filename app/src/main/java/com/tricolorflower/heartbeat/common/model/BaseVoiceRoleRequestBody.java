package com.tricolorflower.heartbeat.common.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoleRequestBody extends BaseRequstBody {

    public String uid;

    public BaseVoiceRoleRequestBody(String token, String uid) {
        super(token);
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "BaseVoiceRoleRequestBody{" +
                "uid='" + uid + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
