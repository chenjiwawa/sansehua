package com.zl.dappore.home.model;


import com.zl.dappore.R;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/3 9:37
 * @Description
 */

public class HomeConstants {
    /**
     * tab标题
     */
    public static final int[] NAME_TABS = new int[]{R.string.homepage_title, R.string.find_title, R.string.media_title, R.string.mine_title};
    /**
     * tab-选中和非选中图片
     */
    public static final int[] ICON_TABS_DEFAULT = new int[]{R.mipmap.icon_main_unselected, R.mipmap.icon_find_unselected, R.mipmap.icon_media_unselected, R.mipmap.icon_mine_unselected};
    public static final int[] ICON_TABS_SELECTED = new int[]{R.mipmap.icon_main_selected, R.mipmap.icon_find_selected, R.mipmap.icon_media_selected, R.mipmap.icon_mine_selected};
    /**
     * 首页viewpager child的index
     */
    public static final int INDEX_HOME = 0;
    public static final int INDEX_FIND = 1;
    public static final int INDEX_MEDIA = 2;
    public static final int INDEX_MINE = 3;


    public static final int[] NAME_TABS_MAIN = new int[]{R.string.recommend_title, R.string.rank_title, R.string.category_title};

    public static final int INDEX_RECOMMEND = 0;
    public static final int INDEX_RANK = 1;
    public static final int INDEX_CATEGORY = 2;

    /**
     * 排行
     */
    public static final int[] NAME_TABS_RANK = new int[]{R.string.hot_title, R.string.new_title, R.string.dapp_title};
    public static final String[] TITLE_TABS_RANK = {"热门榜", "新品榜", "dapp榜"};

    public static final int INDEX_HOT = 0;
    public static final int INDEX_NEW = 1;
    public static final int INDEX_DAPP = 2;

    public static final String CATEGORY_TYPE_DEFAULT = "1";

    public static final String BUNDLE_KEY_RANK_REQUEST_INDEX = "bundle_key_rank_request_index";

    /**
     * 本地图片路径缓存
     */
    public static final String CACHE_LOCAL_IMAGE_LIST = "cache_local_image_list";
    /**
     * 搜索历史缓存
     */
    public static final String CACHE_SEARCH_HISTORY = "cache_search_history";

    /**
     * 首页 类别
     */
    public static final String BUNDLE_KEY_CATEGORY_REQUEST_ID = "bundle_key_Category_request_id";
    public static final String BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE = "bundle_key_category_request_sort_type";

    public static final int REQUESTCODE_LOGIN_COLLECTION = 1 << 3;
    public static final int REQUESTCODE_LOGIN_FAVORITE = 2 << 3;
    public static final int REQUESTCODE_LOGIN_USERINFO = 3 << 3;
    public static final int REQUESTCODE_LOGIN_COMMENT = 4 << 3;
    public static final int REQUESTCODE_LOGIN_VIDEOEDIT = 5 << 3;

    /**
     * 视频
     */
    public static final String BUNDLE_KEY_VIDEO_CATEGORY_REQUEST_ID = "bundle_key_Video_Category_request_id";

}
