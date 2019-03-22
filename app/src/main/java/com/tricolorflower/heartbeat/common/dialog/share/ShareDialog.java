package com.tricolorflower.heartbeat.common.dialog.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.StringUtil;
import com.tricolorflower.heartbeat.common.utils.share.ShareContent;
import com.tricolorflower.heartbeat.common.utils.share.ShareUtils;

import java.util.Map;


public class ShareDialog extends Dialog implements View.OnClickListener {
    @Bind(R.id.tv_sort_type_new)
    TextView tvSortTypeNew;
    @Bind(R.id.tv_weixincircle_share)
    TextView tvWeixincircleShare;
    @Bind(R.id.tv_weixin_share)
    TextView tvWeixinShare;
    @Bind(R.id.tv_qqzone_share)
    TextView tvQqzoneShare;
    @Bind(R.id.tv_qq_share)
    TextView tvQqShare;
    @Bind(R.id.tv_sina_share)
    TextView tvSinaShare;
    @Bind(R.id.tv_report_share)
    TextView tvReportShare;
    @Bind(R.id.tv_copyurl_share)
    TextView tvCopyurlShare;
    @Bind(R.id.tv_cancel_share)
    TextView tvCancelShare;
    @Bind(R.id.layout_popmenu_sort)
    LinearLayout layoutPopmenuSort;
    private String TAG = this.getClass().getSimpleName();

    private ShareResultI shareResultI;
    private ShareI shareI;
    private Context context;

    public ShareDialog(Context context) {
        super(context, R.style.evaluate_dialog_style);
        this.context = context;
    }

    public static ShareDialog getInstance(Context context) {
        return new ShareDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        ViewBindHelper.bindView(this, view);
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
            }
        });
    }

    public void weixinCircle() {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
                , shareResultI);
    }

    public void weiXin() {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN
                , shareResultI);
    }

    public void Qzone() {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QZONE
                , shareResultI);
    }

    public void qq() {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QQ
                , shareResultI);
    }

    public void sina() {
        ShareUtils.shareWeb((Activity) context, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.SINA
                , shareResultI);
    }

    public void qqLogin() {
        authorization(SHARE_MEDIA.QQ);
    }

    public void weiXinLogin() {
        authorization(SHARE_MEDIA.WEIXIN);
    }

    public void sinaLogin() {
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


    @OnClick({R.id.tv_weixincircle_share, R.id.tv_weixin_share, R.id.tv_qqzone_share, R.id.tv_qq_share, R.id.tv_sina_share, R.id.tv_report_share, R.id.tv_copyurl_share, R.id.tv_cancel_share})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_weixincircle_share:
                L.i(TAG, "tv_weixincircle_share");
                weixinCircle();
                break;
            case R.id.tv_weixin_share:
                L.i(TAG, "tv_weixin_share");
                weiXin();
                break;
            case R.id.tv_qqzone_share:
                Qzone();
                break;
            case R.id.tv_qq_share:
                qq();
                break;
            case R.id.tv_sina_share:
                sina();
                break;
            case R.id.tv_report_share:
                break;
            case R.id.tv_copyurl_share:
                StringUtil.CopyToClipboard(context, ShareContent.url);
                break;
            case R.id.tv_cancel_share:
                break;
        }
        dismiss();
    }

    @Override
    public void onClick(View v) {

    }

    public void setShareResultI(ShareResultI shareResultI) {
        this.shareResultI = shareResultI;
    }

    public void setShareI(ShareI shareI) {
        this.shareI = shareI;
    }
}
