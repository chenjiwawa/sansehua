package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.tricolorflower.heartbeat.common.model.BaseRequstBody;

public class BaseEmojiListRequestBody extends BaseRequstBody {

    public int page;

    public BaseEmojiListRequestBody(String token, int page) {
        super(token);
        this.page = page;
    }

    @Override
    public String toString() {
        return "BaseProductListRequestBody{" +
                "page=" + page +
                ", token='" + token + '\'' +
                '}';
    }
}
