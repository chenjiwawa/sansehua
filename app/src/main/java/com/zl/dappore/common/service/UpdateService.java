package com.zl.dappore.common.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author lixiangwei
 * @Date:2014-4-16
 * @Copyright (c) 2014, 方正电子 All Rights Reserved.
 */
@SuppressLint("NewApi")
public class UpdateService extends Service {
    public final static String TAG = UpdateService.class.getSimpleName();

    // 文件存储
    private File updateDir = null;
    private File updateFile = null;

    private final static int DOWNLOAD_COMPLETE = 0;
    private final static int DOWNLOAD_FAIL = 1;

    // 通知栏
    private NotificationManager updateNotificationManager = null;
    private Notification updateNotification = null;
    // 通知栏跳转Intent
    private Intent updateIntent = null;
    private PendingIntent updatePendingIntent = null;

    private String mUrlDownload;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 获取传值
        String filename = intent.getStringExtra("filename") + "";
        mUrlDownload = intent.getStringExtra("urldownload");
        // 创建文件
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            updateDir = new File(Environment.getExternalStorageDirectory(), "");
            updateFile = new File(updateDir.getPath(), filename + ".apk");
        }

        this.updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 设置下载过程中，点击通知栏，回到主界面
        // if(android.os.Build.VERSION.SDK_INT>=11) {
        this.updateNotification = new NotificationCompat.Builder(this).setAutoCancel(false).setOngoing(true).setContentTitle(getString(R.string.app_name)).setTicker(getString(R.string.str_service_start_download)).setContentText("0%").setContentIntent(updatePendingIntent).setSmallIcon(R.mipmap.ic_launcher).setWhen(System.currentTimeMillis()).build();
        // } else {
        // this.updateNotification = new Notification();
        // updateIntent = new Intent(this, SettingsActivity.class);
        // updatePendingIntent = PendingIntent.getActivity(this, 0,
        // updateIntent, 0);
        // // 设置通知栏显示内容
        // updateNotification.icon = R.drawable.ic_launcher;
        // updateNotification.tickerText = "开始下载";
        // updateNotification.setLatestEventInfo(this, "写字先生", "0%",
        // updatePendingIntent);
        // }

        // 发出通知
        updateNotificationManager.notify(0, updateNotification);

        // 开启一个新的线程下载，如果使用Service同步下载，会导致ANR问题，Service本身也会阻塞
        new Thread(new updateRunnable()).start();// 这个是下载的重点，是下载的过程

        return super.onStartCommand(intent, flags, startId);
    }

    class updateRunnable implements Runnable {
        Message message = updateHandler.obtainMessage();

        public void run() {
            message.what = DOWNLOAD_COMPLETE;
            try {
                // 增加权限;
                if (!updateDir.exists()) {
                    updateDir.mkdirs();
                }
                if (!updateFile.exists()) {
                    updateFile.createNewFile();
                }
                // 下载函数，以QQ为例子
                // 增加权限;
                long downloadSize = downloadUpdateFile(mUrlDownload, updateFile);
                if (downloadSize > 0) {
                    // 下载成功
                    updateHandler.sendMessage(message);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                message.what = DOWNLOAD_FAIL;
                // 下载失败
                updateHandler.sendMessage(message);
            }
        }
    }

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_COMPLETE:
                    // 点击安装PendingIntent
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = getUri(installIntent, updateFile);
                    installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                    updatePendingIntent = PendingIntent.getActivity(UpdateService.this, 0, installIntent, 0);

                    // if(android.os.Build.VERSION.SDK_INT>=11) {
                    UpdateService.this.updateNotification = new NotificationCompat.Builder(UpdateService.this).setAutoCancel(false).setOngoing(true).setContentTitle(getString(R.string.app_name)).setTicker("").setContentText(getString(R.string.str_service_download_finish)).setContentIntent(updatePendingIntent).setSmallIcon(R.mipmap.ic_launcher).setWhen(System.currentTimeMillis()).build();
                    // } else {
                    // updateNotification.defaults =
                    // Notification.DEFAULT_SOUND;// 铃声提醒
                    // updateNotification.setLatestEventInfo(UpdateService.this,
                    // "写字先生", "下载完成,点击安装。", updatePendingIntent);
                    // }
                    updateNotificationManager.notify(0, updateNotification);
                    openFile(updateFile);
                    // 停止服务
                    stopSelf();
                    break;
                case DOWNLOAD_FAIL:
                    // 下载失败
                    // if(android.os.Build.VERSION.SDK_INT>=11) {
                    UpdateService.this.updateNotification = new NotificationCompat.Builder(UpdateService.this).setAutoCancel(false).setContentTitle(getString(R.string.app_name)).setTicker("").setContentText(getString(R.string.str_service_download_error)).setContentIntent(updatePendingIntent).setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true).setWhen(System.currentTimeMillis()).build();
                    // } else {
                    // updateNotification.setLatestEventInfo(UpdateService.this,
                    // "写字先生", "下载失败", updatePendingIntent);
                    // }

                    updateNotificationManager.notify(0, updateNotification);
                    break;
                default:
                    stopSelf();
            }
        }
    };

    public long downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
        // 这样的下载代码很多，我就不做过多的说明
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;

        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
            if (currentSize > 0) {
                httpConnection.setRequestProperty("RANGE", "bytes=" + currentSize + "-");
            }
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[4096];
            int readsize = 0;
            while ((readsize = is.read(buffer)) > 0) {
                fos.write(buffer, 0, readsize);
                totalSize += readsize;
                // 为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
                if ((downloadCount == 0) || (int) (totalSize * 100 / updateTotalSize) - 10 > downloadCount) {
                    downloadCount += 10;
                    int progress = (int) totalSize * 100 / updateTotalSize;
                    if (progress > 0) {
                        // if(android.os.Build.VERSION.SDK_INT>=11) {
                        UpdateService.this.updateNotification = new NotificationCompat.Builder(UpdateService.this).setAutoCancel(false).setContentTitle(getString(R.string.str_service_downloading)).setTicker("").setOngoing(true).setContentText(progress + "%").setContentIntent(updatePendingIntent).setSmallIcon(R.mipmap.ic_launcher).setWhen(System.currentTimeMillis()).build();
                        // } else {
                        // updateNotification.setLatestEventInfo(UpdateService.this,
                        // "正在下载", (int) totalSize * 100 / updateTotalSize +
                        // "%", updatePendingIntent);
                        // }

                        updateNotificationManager.notify(0, updateNotification);
                    }

                }
            }
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
            if (is != null) {
                is.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        return totalSize;
    }

    private void openFile(File file) {
        try {
            if (file == null || !file.exists()) {
                return;
            }
            L.e(TAG, "OpenFile=" + file.getName());
            Intent intent = new Intent();
            Uri uri = getUri(intent, file);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            // 显示打开方式
            Intent intent = new Intent();
            Uri uri = getUri(intent, file);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "*/*");
            startActivity(intent);
        }
    }

    private Uri getUri(Intent intent, File file) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Uri uri = FileProvider.getUriForFile(QsHelper.getInstance(), QsHelper.getInstance().getPackageName()+".provider", file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            return uri;
//        } else {
        return Uri.fromFile(file);
//        }
    }
}
