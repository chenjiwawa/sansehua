package com.tricolorflower.heartbeat.home.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class VideoTaxonsList extends BaseModel {

    @SerializedName("video_taxons")
    public List<Video.VideoTaxon> videoTaxons;

    @Override
    public boolean isLastPage() {
        return videoTaxons != null && videoTaxons.size() > 0;
    }
}
