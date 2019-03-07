package com.zl.dappore.home.model;

import com.google.gson.annotations.SerializedName;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.videodetail.model.Video;

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
