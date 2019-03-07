package com.zl.dappore.common.utils.image;

import android.os.AsyncTask;
import android.os.Build;

/**
 * @CreateBy qsmaxmin
 * @Date 16/10/26
 * @Description AsyncTask执行工具类
 */
public class TaskUtil {

    /**
     * 执行异步任务
     * android 2.3 及一下使用execute()方法
     * android 3.0 及以上使用executeOnExecutor方法
     */
    @SafeVarargs
    public static <Params, Progress, Result> void execute(AsyncTask<Params, Progress, Result> task, Params... params) {
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }
}
