package com.tricolorflower.heartbeat.common.model;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class BaseRequstBody {

    public String token;

    public BaseRequstBody() {
    }

    public BaseRequstBody(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BaseRequstBody{" +
                "token='" + token + '\'' +
                '}';
    }
}
