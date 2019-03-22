package com.tricolorflower.heartbeat.common.dialog.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.StringUtil;
import com.tricolorflower.heartbeat.common.utils.share.ShareContent;
import com.tricolorflower.heartbeat.common.utils.share.ShareUtils;

import java.util.Map;


public interface ShareResultI {
    public void onSuccess();
    public void onFail();
}
