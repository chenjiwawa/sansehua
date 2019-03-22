package com.tricolorflower.heartbeat.common.widget.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tricolorflower.heartbeat.R;

public class IconToast extends Toast {

    private static Context mContext;
    private static IconToast toast;
    private static TextView msg;
    private static TextView note;
    private static ImageView icon;

    public synchronized static IconToast getInstance(Context context) {
        if (toast == null) {
            mContext = context;
            toast = new IconToast(mContext);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        return toast;
    }

    private IconToast(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        if (mContext == null)
            return;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast_money, null);
        msg = view.findViewById(R.id.tv_msg_toast_money);
        note = view.findViewById(R.id.tv_note_toast_money);
        icon = view.findViewById(R.id.iv_note_toast_money);
        setView(view);
    }

    public static void show(String message, String text, Drawable drawable, int duruation) {
        if (msg != null) {
            msg.setText(message);
        }
        if (note != null) {
            note.setText(text);
        }
        if (icon != null && drawable != null) {
            icon.setImageDrawable(drawable);
        }
        toast.setDuration(duruation);
        toast.show();
    }

}

