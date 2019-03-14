//package com.zl.dappore;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.qsmaxmin.qsbase.common.log.AgoraLog;
//import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
//import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
//import com.qsmaxmin.qsbase.mvp.QsActivity;
//import com.umeng.socialize.UMAuthListener;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.zl.dappore.R;
//import com.zl.dappore.common.utils.share.ShareContent;
//import com.zl.dappore.common.utils.share.ShareUtils;
//
//import java.util.Map;
//
//
//public class MainActivity extends QsActivity {
//    @Bind(R.id.btnweiXin)
//    Button btnweiXin;
//    @Bind(R.id.btnweixinCircle)
//    Button btnweixinCircle;
//    private String TAG = this.getClass().getSimpleName();
//
//    @Override
//    public int layoutId() {
//        return R.layout.activity_shortvideo_main;
//    }
//
//    @Override
//    public void initData(Bundle bundle) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        /** attention to this below ,must add this**/
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
//
//
//    public void weiXin(View view) {
//        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
//                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN
//        );
//    }
//
//    public void weixinCircle(View view) {
//        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
//                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
//        );
//    }
//
//
//    public void weiXinLogin(View view) {
//        authorization(SHARE_MEDIA.WEIXIN);
//    }
//
//
//    //授权
//    private void authorization(SHARE_MEDIA share_media) {
//        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//                AgoraLog.d(TAG, "onStart " + "授权开始");
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                AgoraLog.d(TAG, "onComplete " + "授权完成");
//
//                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
//                String uid = map.get("uid");
//                String openid = map.get("openid");//微博没有
//                String unionid = map.get("unionid");//微博没有
//                String access_token = map.get("access_token");
//                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
//                String expires_in = map.get("expires_in");
//                String name = map.get("name");
//                String gender = map.get("gender");
//                String iconurl = map.get("iconurl");
//
//                Toast.makeText(getApplicationContext(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();
//
//                //拿到信息去请求登录接口。。。
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                AgoraLog.d(TAG, "onError " + "授权失败");
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media, int i) {
//                AgoraLog.d(TAG, "onCancel " + "授权取消");
//            }
//        });
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        UMShareAPI.get(this).release();
//    }
//
//    @OnClick({R.id.btnweiXin, R.id.btnweixinCircle})
//    public void onViewClick(View view) {
//        super.onViewClick(view);
//        switch (view.getId()) {
//            case R.id.btnweiXin:
//                AgoraLog.i(initTag(), "btnweiXin");
//                weiXin(null);
//                break;
//            case R.id.btnweixinCircle:
//                AgoraLog.i(initTag(), "btnweixinCircle");
//                weixinCircle(null);
//                break;
//        }
//    }
//}
