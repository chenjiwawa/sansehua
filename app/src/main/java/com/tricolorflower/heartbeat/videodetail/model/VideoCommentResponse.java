package com.tricolorflower.heartbeat.videodetail.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class VideoCommentResponse extends BaseModel {

    public RewardResponse.Reward reward;

    @Override
    public String toString() {
        return "VideoCommentResponse{" +
                "reward=" + reward +
                '}';
    }
}
