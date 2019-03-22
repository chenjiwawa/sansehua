package com.tricolorflower.heartbeat.common.event;

import com.tricolorflower.heartbeat.videodetail.model.Author;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/27 17:43
 * @Description
 */

public class UserInfoEvent {

    /**
     * 个人中心，修改头像成功,修改昵称成功
     */
    public static class OnUserInfoDataChanged {
    }

    /**
     * 个人中心，图片裁切完成事件回调
     */
    public static class OnPhotoCutEvent {
        public final byte[] photoBytes;

        public OnPhotoCutEvent(byte[] bytes) {
            this.photoBytes = bytes;
        }
    }

    /**
     * 当相册的摄像头被点击了
     */
    public static class OnAlbumCameraClick {
    }


    public static class OnPersonalInfoEvent {
        public static final int STATE_REFRESH = 1 << 1;
        public int state;
        public int pos;
        public Author author;

        public OnPersonalInfoEvent(int state, Author author) {
            this.state = state;
            this.author = author;
        }

        public OnPersonalInfoEvent(int state, int pos, Author author) {
            this.state = state;
            this.pos = pos;
            this.author = author;
        }

        @Override
        public String toString() {
            return "OnPersonalInfoEvent{" +
                    "state=" + state +
                    ", pos=" + pos +
                    ", author=" + author +
                    '}';
        }
    }
}
