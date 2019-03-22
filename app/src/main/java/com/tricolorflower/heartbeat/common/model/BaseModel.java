package com.tricolorflower.heartbeat.common.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

public class BaseModel extends QsModel {
    public static final int CODE_SUCESS = 200;

    public int code = CODE_SUCESS;
    public String message = "";

    public boolean isLastPage;

    @Override
    public boolean isResponseOk() {
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isLastPage() {
        return isLastPage;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", isLastPage=" + isLastPage +
                '}';
    }
}
