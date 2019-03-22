package com.tricolorflower.heartbeat.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoEditEvent {
    public State mState;

    public VideoEditEvent(State state) {
        this.mState = state;
    }

    public enum State {
        STATE_PUBLISH,
        STATE_SUCCESS,
        STATE_FAIL,
    }
}
