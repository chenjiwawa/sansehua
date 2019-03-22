package com.tricolorflower.heartbeat.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class CommentEvent {
    public CommentEvent.CommentState state;

    public CommentEvent(CommentEvent.CommentState state) {
        this.state = state;
    }

    public enum CommentState {
        STATE_SUCCESS,
        STATE_FAIL,
    }
}
