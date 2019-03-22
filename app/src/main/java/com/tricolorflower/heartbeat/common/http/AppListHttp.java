package com.tricolorflower.heartbeat.common.http;

import com.tricolorflower.heartbeat.home.model.AppList;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Query;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface AppListHttp {
    @GET("/api/apps")
    AppList requestRankAppList(@Query("app_taxon_id") String app_taxon_id, @Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestRankAppList(@Query("app_taxon_id") String app_taxon_id, @Query("created_at-desc") String created_at_desc);

    @GET("/api/apps")
    AppList requestAppListByScore(@Query("app_taxon_id") String app_taxon_id, @Query("score-desc") String score_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestAppListByNew(@Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestAppListByNew(@Query("app_taxon_id") String app_taxon_id, @Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);


    @GET("/api/apps")
    AppList requestAppListByHot(@Query("app_taxon_id") String app_taxon_id, @Query("access_count-desc") String access_count_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestAppListBySimilar(@Query("app_taxon_id") String app_taxon_id, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestRankAppListByPosition(@Query("position-asc") String position_asc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestRankAppListByNew(@Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestRankAppListByScore(@Query("score-desc") String score_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestAppListByHotWord(@Query("hot-word") String hot_word, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestFavoriteAppList(@Query("hot-word") String hot_word, @Query("per") int per, @Query("page") int page);

    @GET("/api/apps")
    AppList requestAppList(@Query("per") int per, @Query("page") int page);

}