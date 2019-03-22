package com.tricolorflower.heartbeat.common.http;

import com.tricolorflower.heartbeat.home.model.PosterList;
import com.qsmaxmin.qsbase.common.aspect.GET;


public interface PosterHttp {

    @GET("/api/posters")
    PosterList requestPosterList();

}
