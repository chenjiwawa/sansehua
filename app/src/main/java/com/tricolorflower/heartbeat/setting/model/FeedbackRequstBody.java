package com.tricolorflower.heartbeat.setting.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class FeedbackRequstBody extends BaseModel {

    public FeedbackRequstBody.ParMap parMap;

    public static class ParMap extends BaseModel {
        public String userId;//":"100000",
        public String link;//":"122132323",
        public String feedback;//":"zhognguoggg"
    }
}
