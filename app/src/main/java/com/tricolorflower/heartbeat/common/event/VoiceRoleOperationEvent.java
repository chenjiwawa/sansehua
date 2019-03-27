package com.tricolorflower.heartbeat.common.event;

import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class VoiceRoleOperationEvent {

    public State state;

    public VoiceRoleOperationEvent(State state) {
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

    public static class OnLetRoleLoginEvent {
        public VoiceRole data;

        public OnLetRoleLoginEvent(VoiceRole data) {
            this.data = data;
        }

    }

    public static class OnAddAdminEvent {
        public VoiceRole data;

        public OnAddAdminEvent(VoiceRole data) {
            this.data = data;
        }

    }

    public static class OnRemoveAdminEvent {
        public VoiceRole data;

        public OnRemoveAdminEvent(VoiceRole data) {
            this.data = data;
        }

    }

    public static class OnAddBlackEvent {
        public VoiceRole data;

        public OnAddBlackEvent(VoiceRole data) {
            this.data = data;
        }

    }

    public static class OnRemoveBlackEvent {
        public VoiceRole data;

        public OnRemoveBlackEvent(VoiceRole data) {
            this.data = data;
        }

    }


}
