package com.tricolorflower.heartbeat.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.log.L;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.share.ShareContent;
import com.tricolorflower.heartbeat.common.utils.share.ShareUtils;

import java.util.Map;

public class ShareDialog2 extends Dialog implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();

    private OnShareListener mListener;
    private Context context;

    public ShareDialog2(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static ShareDialog2 getInstance(Context context) {
        return new ShareDialog2(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        setContentView(view);
        setView(view);

    }

    private void setView(View view) {
        if (context == null)
            return;

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mListener != null) {
                    mListener.onDismiss(dialog);
                }
            }
        });
    }


    public void qq(View view) {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QQ
        );
    }

    public void weiXin(View view) {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN
        );
    }

    public void weixinCircle(View view) {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
        );
    }

    public void sina(View view) {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.SINA
        );
    }

    public void Qzone(View view) {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QZONE
        );
    }


    public void qqLogin(View view) {
        authorization(SHARE_MEDIA.QQ);
    }

    public void weiXinLogin(View view) {
        authorization(SHARE_MEDIA.WEIXIN);
    }

    public void sinaLogin(View view) {
        authorization(SHARE_MEDIA.SINA);
    }

    //授权
    private void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(getContext()).getPlatformInfo((Activity) context, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                L.d(TAG, "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                L.d(TAG, "onComplete " + "授权完成");

                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");

                Toast.makeText(getContext(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();

                //拿到信息去请求登录接口。。。
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                L.d(TAG, "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                L.d(TAG, "onCancel " + "授权取消");
            }
        });
    }


    @Override
    public void onClick(View v) {
        dismiss();
    }

    public interface OnShareListener {
        void onWXCircleShare(View view);

        void onWXShare(View view);

        void onQQZoneShare(View view);

        void onQQShare(View view);

        void onSinaShare(View view);

        void onReport(View view);

        void onCopyUrl(View view);

        void onDismiss(DialogInterface dialog);
    }

    public void setOnShareListener(OnShareListener listener) {
        this.mListener = listener;
    }
}
