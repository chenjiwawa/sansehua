package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.home.model.VideoTaxonsList;


public interface VideoCategoryHttp {

    @GET("/api/video_taxons")
    VideoTaxonsList requestVideoTaxons();

}
