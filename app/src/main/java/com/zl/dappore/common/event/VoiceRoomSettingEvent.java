package com.zl.dappore.common.event;

import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoom;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class VoiceRoomSettingEvent {

    public State state;

    public VoiceRoomSettingEvent(State state) {
        this.state = state;
    }

    public enum State {
        STATE_FRESH_AFTER_SETTING
    }

    public static class OnVoiceRoomEditEvent {
        public VoiceRoom data;

        public OnVoiceRoomEditEvent(VoiceRoom data) {
            this.data = data;
        }
    }


}
