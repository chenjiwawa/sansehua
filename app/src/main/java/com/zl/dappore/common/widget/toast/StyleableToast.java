package com.zl.dappore.common.widget.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.utils.QsHelper;

public class StyleableToast {

    private static IconToast mToast = null;

    /**
     * 简单Toast 消息弹出
     */
    public static void show(final String message, final String note, final Drawable drawable) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        // 判断是否在主线程
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            QsHelper.getInstance().getThreadHelper().getMainThread().execute(new Runnable() {
                @Override
                public void run() {
                    showToast(QsHelper.getInstance().getApplication(), message, note, drawable, Toast.LENGTH_SHORT);
                }
            });
        } else {
            showToast(QsHelper.getInstance().getApplication(), message, note, drawable, Toast.LENGTH_SHORT);
        }
    }

    public static void show(@StringRes int messageId, @StringRes int noteId, @DrawableRes int drawableId) {
        if (messageId != 0 && noteId != 0 && drawableId != 0) {
            show(QsHelper.getInstance().getString(messageId), QsHelper.getInstance().getString(noteId), QsHelper.getInstance().getDrawable(drawableId));
        }
    }

    /**
     * 弹出提示
     */
    private static void showToast(Context context, String message, String note, Drawable drawable, int duruation) {
        if (mToast == null) {
            mToast = IconToast.getInstance(context);
            mToast.show(message, note, drawable, duruation);
        } else {
            mToast.show(message, note, drawable, duruation);
        }
    }
}
