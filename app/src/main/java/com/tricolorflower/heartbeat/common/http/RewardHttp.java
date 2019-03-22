package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.DELETE;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.videodetail.model.RewardResponse;

public interface RewardHttp {
    public static final String rewardpath = "/aim_logs";
    public static final String QUERY_PARAM_SHARE_APP = "share_app";
    public static final String QUERY_PARAM_LOAD_APP = "load_app";
    public static final String QUERY_PARAM_SHARE_VIDEO = "share_video";
    public static final String TYPE_APP = "App/";
    public static final String TYPE_VIDEO = "Video/";

    @POST("/api/")
    RewardResponse requestReward(@Path String[] entity_type, @Path String[] entity_id, @Path String[] rewardpath, @Query("code") String code, @Body BaseRequstBody body);

}
