package com.tricolorflower.heartbeat.voiceroom.model.voiceroom;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class VoiceRoom extends BaseModel implements Serializable {

    public static final int CODE_SUCESS = 200;

    /*房间*/
    @SerializedName("room_id")
    public String voiceRoomId;
    @SerializedName("room_name")
    public String voiceRoomName;
    @SerializedName("room_pic")
    public String voiceRoomLogo;
    @SerializedName("room_type")
    public RoomType voiceRoomType;
    @SerializedName("room_member")
    public String voiceRoomOnlines;
    @SerializedName("room_announce")
    public String voiceRoomAnnounce;
    @SerializedName("room_notice")
    public String voiceRoomGreeting;
    @SerializedName("isLock")
    public boolean isLock;
    @SerializedName("room_password")
    public String voiceRoomPwd;
    @SerializedName("is_screen")
    public boolean isOpenChatRoom;
    @SerializedName("room_tag")
    public List<RoomTag> roomTagList;

    public static class RoomType {
        // 房间类型    1,聊天交友   2,相亲速配   3,游戏开黑
        public static final int CHAT_MAKE_FRIENDS= 1;
        public static final int DATE_MATCH_MAKING = 2;
        public static final int GAME_MATCH_COMMUNICATE = 3;

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;

    }

    public static class RoomTag {

        @SerializedName("id")
        public int id;
        @SerializedName("tag_name")
        public String name;

    }

}
