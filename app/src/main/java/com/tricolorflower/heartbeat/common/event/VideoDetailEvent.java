package com.tricolorflower.heartbeat.common.event;

import com.tricolorflower.heartbeat.videodetail.model.RewardResponse;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/8  下午1:20
 * @Description
 */
public class VideoDetailEvent {

    public VideoDetailEvent.State state;

    public static class onPageSelectEvent {
        public int pos;

        public onPageSelectEvent(int pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "onPageSelectEvent{" +
                    "pos=" + pos +
                    '}';
        }
    }

    public static class onUserEvent {
        public State state = State.NONE;
        public RewardResponse.Reward reward;

        public enum State {
            NONE,
            COMMENT
        }

        public onUserEvent(State state) {
            this.state = state;
        }

        public onUserEvent(State state, RewardResponse.Reward reward) {
            this.state = state;
            this.reward = reward;
        }

        @Override
        public String toString() {
            return "onUserEvent{" +
                    "state=" + state +
                    ", reward=" + reward +
                    '}';
        }
    }

    public VideoDetailEvent(State state) {
        this.state = state;
    }

    public enum State {
        STATE_REFRESH
    }
}