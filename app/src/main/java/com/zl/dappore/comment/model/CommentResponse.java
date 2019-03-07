package com.zl.dappore.comment.model;

import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.videodetail.model.RewardResponse;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class CommentResponse extends BaseModel {

    public RewardResponse.Reward reward;

    @Override
    public String toString() {
        return "CommentResponse{" +
                "reward=" + reward +
                '}';
    }
}
