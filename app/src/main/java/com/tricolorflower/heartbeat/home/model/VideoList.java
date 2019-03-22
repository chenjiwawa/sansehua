package com.tricolorflower.heartbeat.home.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class VideoList extends BaseModel {

    public List<Video> videos;

    @Override
    public boolean isLastPage() {
        return !(videos != null && videos.size() > 0);
    }
}
