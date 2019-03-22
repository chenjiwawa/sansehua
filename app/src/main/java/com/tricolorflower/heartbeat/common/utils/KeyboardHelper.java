package com.tricolorflower.heartbeat.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardHelper {
    public KeyboardHelper() {
    }

    public static void hideSoftInput(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService("input_method");
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getApplicationWindowToken(), 2);
            }

        }
    }

    public static void hideSoftInputFromWindow(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void showSoftInput(EditText et) {
        if (et != null) {
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService("input_method");
            if (imm != null) {
                imm.showSoftInput(et, 0);
            }

        }
    }

    public static void showSoftInputDelay(final EditText et) {
        if (et != null) {
            et.postDelayed(new Runnable() {
                public void run() {
                    KeyboardHelper.showSoftInput(et);
                }
            }, 300L);
        }
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && v instanceof EditText && event != null) {
            int[] l = new int[]{0, 0};
            v.getLocationInWindow(l);
            int left = l[0];
            int top = l[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return event.getX() <= (float) left || event.getX() >= (float) right || event.getY() <= (float) top || event.getY() >= (float) bottom;
        } else {
            return true;
        }
    }
}
