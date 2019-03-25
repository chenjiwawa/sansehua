package com.tricolorflower.heartbeat.voiceroom.model;

import com.tricolorflower.heartbeat.R;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/3 9:37
 * @Description
 */

public class VoiceRoomConstants {

    public static final String BUNDLE_KEY_REQUEST_VOICE_ROOM = "bundle_key_request_voice_room";
    public static final String BUNDLE_KEY_REQUEST_VOICE_ROLE = "bundle_key_request_voice_role";
    public static final String BUNDLE_KEY_REQUEST_VOICE_ROOM_ID = "bundle_key_request_voice_room_id";
    public static final String BUNDLE_KEY_REQUEST_USER_ID = "bundle_key_request_user_id";//登录注册Id
    public static final String BUNDLE_KEY_REQUEST_VOICE_HOLDER = "bundle_key_request_voice_holder";
    public static final String BUNDLE_KEY_REQUEST_VOICE_ROLE_USER = "bundle_key_request_voice_role_user";//当前语音房间用户
    public static final String BUNDLE_KEY_REQUEST_VOICE_CLIENTS = "bundle_key_request_voice_clients";
    public static final String BUNDLE_KEY_REQUEST_VOICE_ROOM_RESPONSE = "bundle_key_request_voice_room_response";
    public static final String BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR = "bundle_key_request_voice_client_or_auditor";//当前语音房间麦上、麦下用户（非房主）
    public static final String BUNDLE_KEY_REQUEST_ID = "bundle_key_request_id";

    /*产品*/
    public static final String BUNDLE_KEY_PRODUCTLIST_REQUEST_PAGE_NO = "bundle_key_productlist_request_page_no";

    /*产品*/
    public static final String BUNDLE_KEY_EMOJILIST_REQUEST_PAGE_NO = "bundle_key_emojilist_request_page_no";

    /*声网语音直播*/
    public static final String BUNDLE_KEY_REQUEST_AGORA_CHANNEL_NAME = "bundle_key_request_agora_channel_Name";
    public static final String BUNDLE_KEY_REQUEST_AGORA_UID = "bundle_key_request_agora_uid";

    public static final String AGORA_CHANNEL_NAME_PREFIX = "";
    public static final String AGORA_UID_PREFIX = "";

    public static String getAgoraChannelName(String id) {
        return AGORA_CHANNEL_NAME_PREFIX + id;
    }

    public static String getAgoraUid(String id) {
        return AGORA_UID_PREFIX + id;
    }


    /*融云IM*/
    public static final String BUNDLE_KEY_REQUEST_RONG_CHAT_ROOM_ID = "bundle_key_request_rong_Chat_Room_Id";
    public static final String BUNDLE_KEY_REQUEST_RONG_CHAT_ROOM_TYPE = "bundle_key_request_rong_Chat_Room_Type";

    public static final String chatroonId = 1 + "";

    public static final String voiceHolderId = 1 + "";

    public static final String voiceClientId = 11 + "";


    public static final int[] NAME_TABS = new int[]{R.string.search_app_title, R.string.search_media_title};
    public static final String[] TITLE_TABS = {"应用", "视频"};

    public static final int INDEX_APP = 0;
    public static final int INDEX_MEDIA = 1;
}
