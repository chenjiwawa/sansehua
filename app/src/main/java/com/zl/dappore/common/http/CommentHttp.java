package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.comment.model.CommentList;
import com.zl.dappore.comment.model.CommentResponse;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.model.BaseRequstBody;
import com.zl.dappore.videodetail.model.VideoCommentResponse;

public interface CommentHttp {
    public static final String path = "/comments";
    public static final String TYPE_APP = "App/";
    public static final String TYPE_VIDEO = "Video/";

    @GET("/api/")
    CommentList requestComments(@Path String[] commentable_type, @Path String[] commentable_id, @Path String[] comments, @Query("per") int per, @Query("page") int page);

    @POST("/api/")
    CommentResponse requestComments(@Path String[] commentable_type, @Path String[] commentable_id, @Path String[] comments, @Body BaseRequstBody body);

}
