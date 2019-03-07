package com.zl.dappore.common.upload;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qsmaxmin.qsbase.common.log.L;

import org.json.JSONObject;

public class UploadHelper {
    private static final String TAG = UploadHelper.class.getSimpleName();

    public static final int TYPE_COVER = 1;
    public static final int TYPE_VIDEO = 2;

    private static UploadHelper uploadHelper;
    private UploadManager mUploadManager;

    public static UploadHelper getInstance() {
        if (uploadHelper == null) {
            synchronized (UploadHelper.class) {
                if (uploadHelper == null) {
                    uploadHelper = new UploadHelper();
                }
            }
        }
        return uploadHelper;
    }

    private UploadHelper() {
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                //.zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        mUploadManager = new UploadManager(config);
    }

    public void uploadQiNiu(final int contentType, String filePath, String key, String token, UploadListener uploadListener) {

        mUploadManager.put(filePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                L.i(TAG, "info ：" + info + "\n response : " + response);
                if (info != null && info.isOK() && response != null) {
                    if (uploadListener != null) {
                        uploadListener.onSuccess();
                    }
                } else {
                    if (uploadListener != null) {
                        uploadListener.onFail();
                    }
                }
            }
        }, null);
    }

}
