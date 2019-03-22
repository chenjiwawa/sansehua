package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.setting.model.FeedbackRequstBody;

/**
 * 反馈相关的接口请求
 * Created by zhuanggy on 2016/8/8.
 */
public interface FeedbackHttp {

    /**
     * 用户反馈
     */
    @POST("/api/fsa/app/feedback/addFeedback.json")
    BaseModel doFeedback(@Body FeedbackRequstBody body);

}
