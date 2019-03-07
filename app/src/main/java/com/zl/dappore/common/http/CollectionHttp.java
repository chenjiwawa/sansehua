package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.DELETE;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.model.BaseRequstBody;

public interface CollectionHttp {
    public static final String path = "/stars";
    public static final String TYPE_APP = "App/";
    public static final String TYPE_VIDEO = "Video/";

    @POST("/api/")
    BaseModel requestComfirmCollections(@Path String[] starred_type, @Path String[] starred_id, @Path String[] stars, @Body BaseRequstBody body);

    @DELETE("/api/")
    BaseModel requestCancelCollections(@Path String[] starred_type, @Path String[] starred_id, @Path String[] stars, @Body BaseRequstBody body);

}
