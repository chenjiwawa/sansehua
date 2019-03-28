package com.tricolorflower.heartbeat.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class ChatRoomEvent {

    public State state;

    public ChatRoomEvent(State state) {
        this.state = state;
    }

    public enum State {
        FRESH
    }

    public static class OnDialogFragment {
        public State state;

        public OnDialogFragment(State state) {
            this.state = state;
        }

        public enum State {
            DIDMISS
        }
    }

}
