package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.tricolorflower.heartbeat.home.model.VideoList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VideoListHttp {
    @GET("/api/videos")
    VideoList requestRankVideoList(@Query("video_taxon_id") String video_taxon_id, @Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestRankVideoList(@Query("video_taxon_id") String video_taxon_id, @Query("created_at-desc") String created_at_desc);

    @GET("/api/videos")
    VideoList requestVideoListByScore(@Query("video_taxon_id") String video_taxon_id, @Query("score-desc") String score_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoListByNew(@Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoListByNew(@Query("video_taxon_id") String video_taxon_id, @Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);


    @GET("/api/videos")
    VideoList requestVideoListByHot(@Query("video_taxon_id") String video_taxon_id, @Query("access_count-desc") String access_count_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoListBySimilar(@Query("video_taxon_id") String video_taxon_id, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestRankVideoListByPosition(@Query("position-asc") String position_asc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestRankVideoListByNew(@Query("created_at-desc") String created_at_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestRankVideoListByScore(@Query("score-desc") String score_desc, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoListByHotWord(@Query("hot-word") String hot_word, @Query("per") int per, @Query("page") int page);

    @GET("/api/video_tags")
    VideoList requestVideoListByTag(@Query("name-like") String name_like, @Query("per") int per, @Query("page") int page);


    @GET("/api/videos")
    VideoList requestVideoList(@Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoList(@Query("video_taxon_id") String video_taxon_id, @Query("per") int per, @Query("page") int page);

    @GET("/api/videos")
    VideoList requestVideoList(@Query("author_id") String author_id, @Query("video_taxon_id") String video_taxon_id, @Query("per") int per, @Query("page") int page);

}