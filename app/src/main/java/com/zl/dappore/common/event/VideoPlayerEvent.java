package com.zl.dappore.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/8  下午1:20
 * @Description
 */
public class VideoPlayerEvent {

    public VideoPlayerEvent.State state;

    public VideoPlayerEvent(State state) {
        this.state = state;
    }

    public enum State {
        STATE_STOP
    }
}