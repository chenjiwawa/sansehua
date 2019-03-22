package com.tricolorflower.heartbeat.web.presenter;

import android.os.Bundle;

import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.web.WebViewActivity;
import com.tricolorflower.heartbeat.web.fragment.WebFragment;
import com.tricolorflower.heartbeat.web.model.WebConstants;


/**
 * @CreateBy qsmaxmin
 * @Date 16/8/24 下午4:41
 * @Description 网页视图
 */
public class WebViewPresenter extends DapporePresenter<WebViewActivity> {

    private WebFragment webFragment;
    private String title        = "";    // 标题
    private String url          = "";    // 地址
    private String shareUrl     = "";    // 分享地址
    private String shareTitle   = "";    // 分享 标题
    private String shareContent = "";    // 分享次标题
    private String shareIconUrl = "";    // 分享图标

    /**
     * 读数据
     */
    public void readData(Bundle bundle) {
        if (bundle == null) {
//            QsToast.show(getString(R.string.data_error));
            getView().activityFinish();
            return;
        }
        title = bundle.getString(WebConstants.BUNDLE_TITLE_KEY);
        url = bundle.getString(WebConstants.BUNDLE_URL_KEY);
        shareUrl = bundle.getString(WebConstants.BUNDLE_SHARE_URL_KEY);
        shareTitle = bundle.getString(WebConstants.BUNDLE_SHARE_TITLE_KEY);
        shareContent = bundle.getString(WebConstants.BUNDLE_SHARE_CONTENT_KEY);
        shareIconUrl = bundle.getString(WebConstants.BUNDLE_SHARE_ICON_KEY);
        webFragment = WebFragment.getInstance(bundle);
        getView().commitFragment(webFragment, WebFragment.class.getSimpleName());
    }

    public WebFragment getWebFragment() {
        return webFragment;
    }
}
