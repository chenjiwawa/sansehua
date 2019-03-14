package com.zl.dappore.common.agora;

import android.text.TextUtils;
import android.util.Log;

public class AgoraLog {
    private static boolean enable = false;

    private AgoraLog() {
    }

    public static void init(boolean isLogOpen) {
        enable = isLogOpen;
    }

    public static void i(String tag, String message) {
        println(4, tag, message);
    }

    public static void v(String tag, String message) {
        println(2, tag, message);
    }

    public static void d(String tag, String message) {
        println(3, tag, message);
    }

    public static void w(String tag, String message) {
        println(5, tag, message);
    }

    public static void e(String tag, String message) {
        println(6, tag, message);
    }

    private static void println(int priority, String tag, String message) {
        if (enable && !TextUtils.isEmpty(message)) {
            Log.println(priority, tag, message);
        }

    }
}
