package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.home.model.VideoList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface FavoriteListHttp {
    public static final String PARAM_APP = "App";
    public static final String PARAM_VIDEO = "Video";

    @GET("/api/stars")
    AppList requestFavoriteAppList(@Query("starred_type") String starred_type, @Query("per") int per, @Query("page") int page);

    @GET("/api/stars")
    VideoList requestFavoriteVideoList(@Query("starred_type") String starred_type, @Query("per") int per, @Query("page") int page);

}