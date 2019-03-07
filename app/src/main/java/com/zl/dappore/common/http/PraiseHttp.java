package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.DELETE;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.model.BaseRequstBody;

public interface PraiseHttp {
    public static final String praisepath = "/attitudes/like";
//    public static final String nopraisepath = "/attitudes/dislike";
    public static final String nopraisepath = "/attitudes/cancel";
    public static final String TYPE_COMMENT = "Comment/";
    public static final String TYPE_VIDEO = "Video/";

    @POST("/api/")
    BaseModel requestPraise(@Path String[] attitudinal_type, @Path String[] attitudinal_id, @Path String[] favorpath, @Body BaseRequstBody body);

    @POST("/api/")
    BaseModel requestNoPraise(@Path String[] attitudinal_type, @Path String[] attitudinal_id, @Path String[] nofavorpath, @Body BaseRequstBody body);

}
