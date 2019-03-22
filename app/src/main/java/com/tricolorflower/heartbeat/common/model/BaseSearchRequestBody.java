package com.tricolorflower.heartbeat.common.model;

public class BaseSearchRequestBody extends BaseRequstBody {

    public String namestr;

    public BaseSearchRequestBody(String token, String namestr) {
        super(token);
        this.namestr = namestr;
    }


    @Override
    public String toString() {
        return "BaseVoiceRoleRequestBody{" +
                "namestr='" + namestr + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
