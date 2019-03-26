package com.tricolorflower.heartbeat.common.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoleRequestBody extends BaseRequstBody {

    public int uid;

    public BaseVoiceRoleRequestBody(String token, int uid) {
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
