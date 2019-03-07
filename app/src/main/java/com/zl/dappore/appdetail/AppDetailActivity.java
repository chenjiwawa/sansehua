package com.zl.dappore.appdetail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.fragment.AppDetailFragment;
import com.zl.dappore.common.dialog.share.ShareDialog;
import com.zl.dappore.common.dialog.share.ShareI;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.utils.KeyboardHelper;
import com.zl.dappore.common.utils.share.ShareContent;
import com.zl.dappore.common.utils.share.ShareUtils;
import com.zl.dappore.common.widget.BeautyRatingBar;
import com.zl.dappore.home.model.HomeConstants;

import java.util.Map;

/**
 * @CreatedBy zhuanggy
 * @Date 16/8/3
 * @Description 关于我们
 */
public class AppDetailActivity extends QsActivity<DapporePresenter> implements AppdetailI, BeautyRatingBar.OnRatingChangeListener {
    private String TAG = this.getClass().getSimpleName();

    @Bind(R.id.btn_back)
    Button btnBack;
    @Bind(R.id.iv_img_title_appdetail)
    ImageView ivImgTitleAppdetail;
    @Bind(R.id.btn_collection)
    Button btnCollection;
    @Bind(R.id.btn_share)
    Button btnShare;
    @Bind(R.id.rl_title_appdetail)
    RelativeLayout rlTitleAppdetail;
    @Bind(R.id.rl_container)
    RelativeLayout rlContainer;
    @Bind(R.id.btn_download)
    Button btnDownload;

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.rb_score_input)
    BeautyRatingBar rbScoreInput;
    @Bind(R.id.tv_score_input)
    TextView tvScoreInput;
    @Bind(R.id.ll_score_input)
    LinearLayout llScoreInput;
    @Bind(R.id.iv_icon_input)
    ImageView ivIconInput;
    @Bind(R.id.et_content_input)
    EditText etContentInput;
    @Bind(R.id.rl_content_input)
    RelativeLayout rlContentInput;
    @Bind(R.id.tv_coment_input)
    TextView tvComentInput;
    @Bind(R.id.rl_comment_input)
    RelativeLayout rlCommentInput;

    AppDetailFragment appDetailFragment;

    @Override
    public int layoutId() {
        return R.layout.activity_appdetail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        appDetailFragment = AppDetailFragment.getInstance(extras == null ? new Bundle() : extras);
        commitFragment(R.id.rl_container, appDetailFragment);

        btnDownload.setVisibility(View.GONE);
        rlCommentInput.setVisibility(View.GONE);
        rbScoreInput.setOnRatingChangeListener(this);
        etContentInput.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                int keyboardHeight = screenHeight - rect.bottom;
                if (keyboardHeight < screenHeight / 4) {
                    rlCommentInput.setVisibility(View.GONE);
                } else {
                    rlCommentInput.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @OnClick({R.id.btn_back, R.id.btn_collection, R.id.btn_share, R.id.tv_coment_input})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.btn_back:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive(etContentInput)) {
                    KeyboardHelper.hideSoftInputFromWindow(this);
                } else {
                    activityFinish();
                }
                break;
            case R.id.btn_collection:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_COLLECTION)) {
                    if (!enableCollection) {
                        QsToast.show("请稍候...");
                        return;
                    }

                    if (appDetailFragment != null) {
                        appDetailFragment.requestCollections();
                    }
                }
                break;
            case R.id.btn_share:
                if (appDetailFragment != null) {
                    appDetailFragment.setShareContent();
                }
                ShareDialog shareDialog = ShareDialog.getInstance(getContext());
                shareDialog.setShareI(new ShareI() {

                    @Override
                    public void onWXCircleShare(View view) {

                    }

                    @Override
                    public void onWXShare(View view) {

                    }

                    @Override
                    public void onQQZoneShare(View view) {

                    }

                    @Override
                    public void onQQShare(View view) {

                    }

                    @Override
                    public void onSinaShare(View view) {

                    }

                    @Override
                    public void onReport(View view) {

                    }

                    @Override
                    public void onCopyUrl(View view) {

                    }

                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                shareDialog.show();
                break;
            case R.id.tv_coment_input:
                if (TextUtils.isEmpty(etContentInput.getText().toString())) {
                    QsToast.show("请输入评论！");
                    return;
                }

                if (appDetailFragment != null) {
                    appDetailFragment.requestCommentList(etContentInput.getText().toString(), rbScoreInput.getRating()+ "");
                }
                break;
        }
    }

    @Override
    public void onRatingChange(float RatingCount) {
        tvScoreInput.setText((RatingCount * 2) + "分");
    }

    @Override
    public void onLogoShow(String url, boolean isVisible) {
        if (TextUtils.isEmpty(url))
            return;

        if (isVisible) {
            btnDownload.setVisibility(View.VISIBLE);
            ivImgTitleAppdetail.setVisibility(View.VISIBLE);
            QsHelper.getInstance().getImageHelper().createRequest().load(url).roundedCorners(CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10), CommonUtils.dp2px(10)).into(ivImgTitleAppdetail);
        } else {
            btnDownload.setVisibility(View.GONE);
            ivImgTitleAppdetail.setVisibility(View.GONE);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void showComment() {
        rlCommentInput.setVisibility(View.VISIBLE);
        KeyboardHelper.showSoftInputDelay(etContentInput);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void hideComment() {
        KeyboardHelper.hideSoftInputFromWindow(this);
        rlCommentInput.setVisibility(View.GONE);
    }

    public int getTitleBarHeight() {
        return rlTitleAppdetail.getHeight();
    }

    private boolean enableCollection = true;

    public void enableCollection(boolean enable) {
        enableCollection = enable;
    }

    public void setCollectionView(boolean isCollection) {
        if (isCollection) {
            btnCollection.setBackgroundResource(R.mipmap.ic_favor_select);
        } else {
            btnCollection.setBackgroundResource(R.mipmap.ic_favor_normal);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    public void qq(View view) {
        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QQ
        );
    }

    public void weiXin(View view) {
        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN
        );
    }

    public void weixinCircle(View view) {
        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
        );
    }

    public void sina(View view) {
        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
                , ShareContent.text, ShareContent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.SINA
        );
    }

    public void Qzone(View view) {
        ShareUtils.shareWeb(this, ShareContent.url, ShareContent.title
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
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
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

                Toast.makeText(getApplicationContext(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();

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
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
