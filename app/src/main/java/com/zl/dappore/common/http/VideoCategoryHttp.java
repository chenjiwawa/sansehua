package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.zl.dappore.home.model.VideoTaxonsList;


public interface VideoCategoryHttp {

    @GET("/api/video_taxons")
    VideoTaxonsList requestVideoTaxons();

}
