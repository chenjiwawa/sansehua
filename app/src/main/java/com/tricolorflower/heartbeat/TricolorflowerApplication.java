package com.tricolorflower.heartbeat;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.view.View;

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
import com.tricolorflower.heartbeat.seal.SealAppContext;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.tricolorflower.heartbeat.common.agora.AgoraHelper;
import com.tricolorflower.heartbeat.common.dialog.CommonLoadingDialog;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.utils.UrlUtils;

import io.rong.contactcard.ContactCardExtensionModule;
import io.rong.contactcard.IContactCardClickListener;
import io.rong.contactcard.IContactCardInfoProvider;
import io.rong.contactcard.message.ContactMessage;
import io.rong.imkit.RongConfigurationManager;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.ipc.RongExceptionHandler;
import io.rong.push.RongPushClient;
import io.rong.push.pushconfig.PushConfig;
import io.rong.recognizer.RecognizeExtensionModule;
import io.rong.sight.SightExtensionModule;
import okhttp3.Response;

public class TricolorflowerApplication extends QsApplication {
    public final static String TAG = TricolorflowerApplication.class.getSimpleName();

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
        Context context = RongConfigurationManager.getInstance().getConfigurationContext(base);
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        AgoraHelper.getInstance().init(this);
        UMShareAPI.get(this);
        initDownloader();
        initRongCloudIM();
    }

    @Override
    public boolean isLogOpen() {
        return true;
    }


    @Override
    public void initHttpAdapter(HttpBuilder httpBuilder) {
        if (httpBuilder == null)
            return;

        httpBuilder.setTerminal(UrlUtils.getCurrentUrl());
        httpBuilder.addHeader("Content-Type", "application/json");
        httpBuilder.addHeader("Accept", "application/json");
    }

    @Override
    public void onCommonHttpResponse(Response response) {
        if (response == null)
            return;
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

    private void initRongCloudIM() {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

//            LeakCanary.install(this);//内存泄露检测
            PushConfig config = new PushConfig
                    .Builder()
                    .enableHWPush(true)
                    .enableMiPush("2882303761517473625", "5451747338625")
                    .enableMeiZuPush("112988", "2fa951a802ac4bd5843d694517307896")
                    .enableVivoPush(true)
                    .enableFCM(true)
                    .build();
            RongPushClient.setPushConfig(config);
            /**
             * 注意：
             *
             * IMKit SDK调用第一步 初始化
             *
             * context上下文
             *
             * 只有两个进程需要初始化，主进程和 push 进程
             */
            RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");
            RongIM.init(this);
            SealAppContext.init(this);
            Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));

            try {


            } catch (Exception e) {
                e.printStackTrace();
            }

//            RongExtensionManager.getInstance().registerExtensionModule(new PTTExtensionModule(this, true, 1000 * 60));
            RongExtensionManager.getInstance().registerExtensionModule(new ContactCardExtensionModule(new IContactCardInfoProvider() {
                @Override
                public void getContactAllInfoProvider(final IContactCardInfoCallback contactInfoCallback) {
                }

                @Override
                public void getContactAppointedInfoProvider(String userId, String name, String portrait, final IContactCardInfoCallback contactInfoCallback) {
                }

            }, new IContactCardClickListener() {
                @Override
                public void onContactCardClick(View view, ContactMessage content) {
                }
            }));
            RongExtensionManager.getInstance().registerExtensionModule(new RecognizeExtensionModule());
            //小视频
            RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());
        }

    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}