package com.tricolorflower.heartbeat.seal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

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


public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

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

    @Override
    protected void attachBaseContext(Context base) {
        Context context = RongConfigurationManager.getInstance().getConfigurationContext(base);
        super.attachBaseContext(context);
    }

}

