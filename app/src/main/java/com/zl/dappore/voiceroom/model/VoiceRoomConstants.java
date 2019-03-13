package com.zl.dappore.voiceroom.model;

import com.zl.dappore.R;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/3 9:37
 * @Description
 */

public class VoiceRoomConstants {

    public static final String BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID = "bundle_key_favorite_request_channel_id";
    public static final String BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE = "bundle_key_favorite_request_voice_role";
    public static final String BUNDLE_KEY_FAVORITE_REQUEST_ID = "bundle_key_favorite_request_id";

    public static final int[] NAME_TABS = new int[]{R.string.search_app_title, R.string.search_media_title};
    public static final String[] TITLE_TABS = {"应用", "视频"};

    public static final int INDEX_APP = 0;
    public static final int INDEX_MEDIA = 1;


    public static final String CHANNEL_PREFIX = "";
    public static final String UID_PREFIX = "";

    public static String getChannelName(String id) {
        return CHANNEL_PREFIX + id;
    }

    public static String getUid(String id) {
        return UID_PREFIX + id;
    }

}
