package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

public class BaseMessageExtraContent {

    public static final String microseat_message = "microseat_message";//麦位各种消息
    public static final String room_message = "room_message";//房间各种消息
    public static final String admin_message = "admin_message";//管理员黑名单消息
    public static final String chat_message = "chat_message";//管理员黑名单消息
    public static final String gift_message = "gift_message";//聊天室发送礼物消息
    public static final String emoji_message = "emoji_message";//聊天室发送表情消息

    public static final String enter_room = "enter_room";//2 用户进入房间
    public static final String send_gift = "send_gift";//3 发送礼物
    public static final String send_message = "send_message";//4 发送聊天
    public static final String out_seat = "out_seat";//5 用户下麦
    public static final String upper_seat = "upper_seat";//6 用户上麦
    public static final String carry_seat = "carry_seat";//7 抱他上麦
    public static final String carry_down_seat = "carry_down_seat";//8 抱他下麦
    public static final String lock_seat = "lock_seat";//9 锁麦
    public static final String unlock_seat = "unlock_seat";//10 解麦
    public static final String close_voice = "close_voice";//11 关闭麦克风
    public static final String on_voice = "on_voice";//12 打开麦克风
    public static final String close_music = "close_music";//13 关闭音乐权限
    public static final String on_music = "on_music";//14 打开音乐权限
    public static final String close_screen = "close_screen";//15 关闭公屏
    public static final String on_screen = "on_screen";//16 打开公屏
    public static final String shot_room = "shot_room";//17 踢出房间
    public static final String member_online = "member_online";//18 在线成员数
    public static final String send_emoji = "send_emoji";//19 发表情
    public static final String add_admin = "add_admin";//20 添加管理员
    public static final String delete_admin = "delete_admin";//21 移除管理员

    @SerializedName("name")
    public String type;
    @SerializedName("voiceRoom")
    public VoiceRoom voiceRoom;
    @SerializedName("user")
    public VoiceRole user;
    @SerializedName("target")
    public VoiceRole target;
    @SerializedName("product")
    public ProductList.Product product;
    @SerializedName("emoji")
    public EmojiList.Emoji emoji;

    public void handleMessageType(String type) {
        switch (type) {
            case enter_room:
                break;
            case send_gift:
                break;
            case send_message:
                break;
            case out_seat:
            case upper_seat:
                break;
            case carry_seat:
            case carry_down_seat:
                break;
            case lock_seat:
            case unlock_seat:
                break;
            case close_voice:
            case on_voice:
                break;
            case close_music:
            case on_music:
                break;
            case close_screen:
            case on_screen:
                break;
            case shot_room:
                break;
            case member_online:
                break;
            case send_emoji:
                break;
            case add_admin:
            case delete_admin:
                break;
        }
    }

    public String getContentFromExtra(String type) {
        switch (type) {
            case enter_room:
                if (user != null) {
                    return user.name + "来了";
                }
                break;
            case send_gift:
                if (user != null && target != null) {
                    return user.name + "给" + target.name + "送了" + "" + "个" + product.giftName;
                }
                break;
            case "guanzhu":
                if (user != null) {
                    return user.name + "关注了房主，不错过下次群聊";
                }
                break;
        }

        return "";
    }


}
