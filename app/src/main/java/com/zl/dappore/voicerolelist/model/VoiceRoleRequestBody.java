package com.zl.dappore.voicerolelist.model;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.zl.dappore.voiceroom.model.BaseVoiceRoleRequestBody;

public class VoiceRoleRequestBody extends BaseVoiceRoleRequestBody {

    public int page;

    public VoiceRoleRequestBody(String token, String uid, int page) {
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
