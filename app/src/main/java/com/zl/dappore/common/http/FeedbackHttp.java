package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.setting.model.FeedbackRequstBody;

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
