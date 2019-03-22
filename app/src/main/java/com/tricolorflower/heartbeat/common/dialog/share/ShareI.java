package com.tricolorflower.heartbeat.common.dialog.share;


import android.content.DialogInterface;
import android.view.View;

public interface ShareI {
    void onWXCircleShare(View view);

    void onWXShare(View view);

    void onQQZoneShare(View view);

    void onQQShare(View view);

    void onSinaShare(View view);

    void onReport(View view);

    void onCopyUrl(View view);

    void onDismiss(DialogInterface dialog);
}