package com.zl.dappore.common.utils.share;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.log.L;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zl.dappore.common.dialog.share.ShareResultI;

/**
 * Author     wildma
 * DATE       2017/07/16
 * Des	      ${友盟分享工具类}
 */
public class ShareUtils {

    /**
     * 分享链接
     */
    public static void shareWeb(final Activity activity, String webUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {
        shareWeb(activity, webUrl, title, description, imageUrl, imageID, platform, null);
    }

    public static void shareWeb(final Activity activity, String webUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform, ShareResultI shareResultI) {
        UMWeb web = new UMWeb(webUrl);//连接地址
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        if (TextUtils.isEmpty(imageUrl)) {
            web.setThumb(new UMImage(activity, imageID));  //本地缩略图
        } else {
            web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
        }
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                    Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        if (shareResultI != null) {
                            shareResultI.onSuccess();
                        }
                    }

                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            L.d("throw", "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();

                            }
                        });

                        if (shareResultI != null) {
                            shareResultI.onFail();
                        }
                    }

                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                            }
                        });

                        if (shareResultI != null) {
                            shareResultI.onFail();
                        }
                    }
                })
                .share();

        //新浪微博中图文+链接
        /*new ShareAction(activity)
                .setPlatform(platform)
                .withText(description + " " + WebUrl)
                .withMedia(new UMImage(activity,imageID))
                .share();*/
    }
}
