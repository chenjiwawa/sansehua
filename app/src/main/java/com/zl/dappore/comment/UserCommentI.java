package com.zl.dappore.comment;

import com.zl.dappore.comment.model.Comment;

public interface UserCommentI {
    void onPraise(int pos, Comment data);
}