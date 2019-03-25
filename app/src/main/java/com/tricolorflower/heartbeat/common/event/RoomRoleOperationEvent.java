package com.tricolorflower.heartbeat.common.event;

import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class RoomRoleOperationEvent {

    public State state;

    public RoomRoleOperationEvent(State state) {
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
