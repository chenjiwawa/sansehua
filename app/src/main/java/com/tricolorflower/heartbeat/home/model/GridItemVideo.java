package com.tricolorflower.heartbeat.home.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class GridItemVideo extends BaseModel {

    public List<Video> videos;

    @Override
    public String toString() {
        return "GridItemVideo{" +
                "videos=" + videos +
                '}';
    }
}
