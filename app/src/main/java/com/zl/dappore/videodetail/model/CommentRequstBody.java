package com.zl.dappore.videodetail.model;

import com.zl.dappore.common.model.BaseRequstBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class CommentRequstBody extends BaseRequstBody {

    public String content;

    public CommentRequstBody(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentRequstBody{" +
                "content='" + content + '\'' +
                '}';
    }
}
