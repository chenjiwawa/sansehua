package com.tricolorflower.heartbeat.common.event;


import com.tricolorflower.heartbeat.voiceroom.model.message.BaseMessageExtra;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class VoiceRoomEvent {

    public State state;

    public VoiceRoomEvent(State state) {
        this.state = state;
    }

    public enum State {
        FRESH
    }

    public static class OnEntry {
        public BaseMessageExtra data;

        public OnEntry(BaseMessageExtra data) {
            this.data = data;
        }
    }

    public static class OnLeave {
        public BaseMessageExtra data;

        public OnLeave(BaseMessageExtra data) {
            this.data = data;
        }

        public OnLeave() {
        }

    }

    public static class OnProductReceived {
        public BaseMessageExtra data;

        public OnProductReceived(BaseMessageExtra data) {
            this.data = data;
        }
    }

}
