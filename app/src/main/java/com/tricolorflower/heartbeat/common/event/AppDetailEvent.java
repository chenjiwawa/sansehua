package com.tricolorflower.heartbeat.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/8  下午1:20
 * @Description
 */
public class AppDetailEvent {

    public AppDetailEvent.AppDetailState state;

    public AppDetailEvent(AppDetailEvent.AppDetailState state) {
        this.state = state;
    }

    public enum AppDetailState {
        STATE_REFRESH
    }
}