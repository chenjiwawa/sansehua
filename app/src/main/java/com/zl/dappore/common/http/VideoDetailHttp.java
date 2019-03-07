package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.PATCH;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.home.model.VideoTaxonsList;
import com.zl.dappore.videodetail.model.VideoResponse;

public interface VideoDetailHttp {
    public static final String path_viewed = "/viewed";

    @GET("/api/videos/")
    VideoResponse requestVideoDetail(@Path String[] id);

    @GET("/api/video_taxons/good")
    VideoTaxonsList requestVideoTaxonsList();

    @PATCH("/videos/")
    BaseModel requestVideoDetailViewed(@Path String[] id, @Path String[] viewed);
}
