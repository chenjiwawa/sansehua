package com.tricolorflower.heartbeat.voicerolelist.model;

import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;

public class VoiceRoleRequestBody extends BaseVoiceRoleRequestBody {

    public int page;

    public VoiceRoleRequestBody(String token, int uid, int page) {
        super(token, uid);
        this.page = page;
    }

    @Override
    public String toString() {
        return "VoiceRoleRequestBody{" +
                "page='" + page + '\'' +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
