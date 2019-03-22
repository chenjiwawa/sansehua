package com.tricolorflower.heartbeat.common.event;

import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class VoiceRoomProductEvent {

    public State state;

    public VoiceRoomProductEvent(State state) {
        this.state = state;
    }

    public enum State {
        STATE_FRESH
    }

    public static class OnChoiceEvent {
        public State state;
        public ProductList.Product data;

        public OnChoiceEvent(VoiceRoomProductEvent.OnChoiceEvent.State state, ProductList.Product data) {
            this.state = state;
            this.data = data;
        }

        public enum State {
            SELECT
        }
    }


}
