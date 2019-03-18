package com.zl.dappore;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.qsmaxmin.qsbase.QsApplication;
import com.qsmaxmin.qsbase.common.http.HttpBuilder;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.dialog.QsProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zl.dappore.common.agora.AgoraHelper;
import com.zl.dappore.common.dialog.CommonLoadingDialog;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.common.utils.UrlUtils;

import io.rong.imkit.RongIM;
import okhttp3.Response;

public class DapporeApplication extends QsApplication {
    public final static String TAG = DapporeApplication.class.getSimpleName();

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    //各个平台的配置
    {
        //微信
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com/sina2/callback");
        //QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initRongIM();
        AgoraHelper.getInstance().init(this);
        UMShareAPI.get(this);
        initDownloader();
    }

    @Override
    public boolean isLogOpen() {
        return true;
    }


    @Override
    public void initHttpAdapter(HttpBuilder httpBuilder) {
        if (httpBuilder == null)
            return;
        L.i(TAG, " onCommonHttpResponse UserConfig -1" + UserConfig.getInstance().isLogin() + " " + UserConfig.getInstance().getAuthToken());

        httpBuilder.setTerminal(UrlUtils.getCurrentUrl());
        httpBuilder.addHeader("Content-Type", "application/json");
        httpBuilder.addHeader("Accept", "application/json");
        if (!TextUtils.isEmpty(UserConfig.getInstance().getAuthToken())) {
            httpBuilder.addHeader("Auth-Token", UserConfig.getInstance().getAuthToken());
        }
    }

    @Override
    public void onCommonHttpResponse(Response response) {
        if (response == null)
            return;
//        AgoraLog.i(TAG, " onCommonHttpResponse response " + response + " " + response.headers());
        L.i(TAG, " onCommonHttpResponse UserConfig +1" + UserConfig.getInstance().isLogin() + " " + UserConfig.getInstance().getAuthToken());
        L.i(TAG, " onCommonHttpResponse UserConfig +2" + UserConfig.getInstance().isLogin() + " " + response.headers().get("Auth-Token"));


        if (response.headers() != null && !TextUtils.isEmpty(response.headers().get("Auth-Token"))) {
            UserConfig.getInstance().setAuthToken(response.headers().get("Auth-Token"));
            L.i(TAG, " onCommonHttpResponse UserConfig +3" + UserConfig.getInstance().isLogin() + " " + UserConfig.getInstance().getAuthToken());
        }
    }


    @Override
    public QsProgressDialog getLoadingDialog() {
        return new CommonLoadingDialog();
    }

    /**
     * 页面为空的布局
     */
    @Override
    public int emptyLayoutId() {
        return R.layout.fragment_common_empty;
    }

    /**
     * 页面出现错误的布局
     */
    @Override
    public int errorLayoutId() {
        return R.layout.fragment_common_httperror;
    }

    /**
     * 页面正在加载时的布局
     */
    @Override
    public int loadingLayoutId() {
        return R.layout.fragment_common_loading;
    }


    private void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(5);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }

    private void initRongIM() {
        RongIM.init(this);
    }


}