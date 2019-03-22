package com.tricolorflower.heartbeat.comment;

import com.tricolorflower.heartbeat.comment.model.Comment;

public interface UserCommentI {
    void onPraise(int pos, Comment data);
}