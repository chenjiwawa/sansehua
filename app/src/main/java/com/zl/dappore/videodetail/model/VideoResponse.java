package com.zl.dappore.videodetail.model;

import com.zl.dappore.common.model.BaseModel;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class VideoResponse extends BaseModel {

    public Video video;

    @Override
    public String toString() {
        return "VideoResponse{" +
                "video=" + video +
                '}';
    }
}
