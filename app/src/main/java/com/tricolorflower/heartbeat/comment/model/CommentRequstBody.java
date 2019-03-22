package com.tricolorflower.heartbeat.comment.model;

import com.tricolorflower.heartbeat.common.model.BaseRequstBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class CommentRequstBody extends BaseRequstBody {

    public String content;
    public String star_count;

    public CommentRequstBody(String content, String star_count) {
        this.content = content;
        this.star_count = star_count;
    }

    @Override
    public String toString() {
        return "CommentRequstBody{" +
                "content='" + content + '\'' +
                ", star_count='" + star_count + '\'' +
                '}';
    }
}
