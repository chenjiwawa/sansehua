package com.tricolorflower.heartbeat.userinfo.model;

import com.tricolorflower.heartbeat.R;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class PersonalConstants {

    /**
     * 搜索页面-Bundle传递输入框输入的文字时的Key
     * 如果携带该字段则跳转搜索结果页
     * 如果Bundle为null或者Bundle没有携带该字段，则跳转搜索默认页
     */
    public static final String BUNDLE_KEY_SEARCH_MODEL = "bundle_key_search_model";
    /**
     * 搜索默认页
     */
    public static final int KEY_TO_SEARCH_DEFAULT = 1 << 3;
    /**
     * 搜索结果页
     */
    public static final int KEY_TO_SEARCH_RESULT = 2 << 3;

    /**
     * 搜索历史缓存
     */
    public static final String CACHE_SEARCH_HISTORY = "cache_search_history";

//    public static final int[] NAME_TABS_SEARCH = new int[]{R.string.search_app_title, R.string.search_find_title, R.string.search_media_title};
//    public static final String[] TITLE_TABS_SEARCH = {"应用", "帖子", "视频"};
//
//    public static final int INDEX_APP_SEARCH = 0;
//    public static final int INDEX_FIND_SEARCH = 1;
//    public static final int INDEX_MEDIA_SEARCH = 2;

    public static final int[] NAME_TABS_PERSONAL = new int[]{R.string.personal_media_title, R.string.personal_topic_title};
    public static final String[] TITLE_TABS_PERSONAL = {"视频", "帖子"};

    public static final int INDEX_MEDIA_PERSONAL = 0;
    public static final int INDEX_TOPIC_PERSONAL = 1;

    public static final String BUNDLE_KEY_PERSONAL_REQUEST_ID = "bundle_key_personal_request_id";
    public static final String BUNDLE_KEY_PERSONAL_REQUEST_AUTHOR = "bundle_key_personal_request_author";
    public static final String BUNDLE_KEY_PERSONAL_REQUEST_IS_USER = "bundle_key_personal_request_is_user";

}
