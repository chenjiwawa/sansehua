package com.zl.dappore.common.http;

import com.zl.dappore.home.model.PosterList;
import com.qsmaxmin.qsbase.common.aspect.GET;


public interface PosterHttp {

    @GET("/api/posters")
    PosterList requestPosterList();

}
