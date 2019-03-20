package com.zl.dappore.voiceroom.model.voiceroomsetting;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseVoiceRoomSettingRequestBody extends QsModel {

    protected String token;

    public BaseVoiceRoomSettingRequestBody(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BaseVoiceRoomSettingRequestBody{" +
                "token='" + token + '\'' +
                '}';
    }
}
