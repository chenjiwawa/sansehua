package com.tricolorflower.heartbeat.photo.model;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/11/28 16:45
 * @Description
 */

public class PhotoConstants {
    /**
     * 相册查看带过来的数据Key,value是一个ModelPhoto的集合
     * {@link ModelPhoto}
     */
    public static final String BUNDLE_KEY_PHOTO_LIST       = "bundle_key_photo_list";
    /**
     * 展示第几页的标示
     */
    public static final String BUNDLE_KEY_SHOW_INDEX       = "bundle_key_show_index";
    /**
     * 跳转key
     */
    public static final String BUNDLE_KEY_STATE            = "BUNDLE_KEY_STATE";
    /**
     * 跳转类型，所有图片列表
     */
    public static final int    KEY_TO_All_PHOTO_LIST       = 1 << 3;
    /**
     * 跳转类型，单个文件夹图片列表
     */
    public static final int    KEY_TO_SINGLE_PHOTO_LIST    = 2 << 3;
    /**
     * 跳转类型，所有图片文件夹
     */
    public static final int    KEY_TO_PHOTO_DIR_LIST       = 3 << 3;
    /**
     * 跳转类型，图片多选择页面
     */
    public static final int    KEY_TO_PHOTO_CHOOSE_LIST    = 4 << 3;
    /**
     * 选择拍照的requestCode
     */
    public static final int    REQUESTCODE_OPEN_CAMERA     = 0xa;
    /**
     * 选择相册的requestCode
     */
    public static final int    REQUESTCODE_OPEN_PHOTO      = 0xb;
    /**
     * 打开图片剪切页面的requestCode
     */
    public static final int    REQUESTCODE_OPEN_PHOTO_CUT  = 0xc;
    /**
     * 跳转剪切图片页面带过去的图片path键
     */
    public static final String BUNDLE_KEY_PHOTO_PATH       = "BUNDLE_KEY_PHOTO_PATH";
    /**
     * 图片裁切带回来的bitmap字节数组
     */
    public static final String BUNDLE_KEY_BITMAP_BYTEARRAY = "bundle_key_bitmap_bytearray";
    /**
     * 图片多选带回来的图片path集合
     */
    public static final String BUNDLE_KEY_PHOTO_CHOOSE_LIST = "bundle_key_photo_choose_list";
}
