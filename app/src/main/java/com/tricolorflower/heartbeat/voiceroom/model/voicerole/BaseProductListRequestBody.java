package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;

public class BaseProductListRequestBody extends BaseRequstBody {

    public int page;

    public BaseProductListRequestBody(String token, int page) {
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
