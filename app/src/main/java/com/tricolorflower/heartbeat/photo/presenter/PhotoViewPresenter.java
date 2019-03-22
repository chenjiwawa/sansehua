package com.tricolorflower.heartbeat.photo.presenter;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.common.utils.ImageUtils;
import com.tricolorflower.heartbeat.photo.PhotoViewActivity;

import java.io.File;


/**
 * @CreateBy qsmaxmin
 * @Date 2017/3/3 13:35
 * @Description
 */
public class PhotoViewPresenter extends DapporePresenter<PhotoViewActivity>{

    @ThreadPoint(ThreadType.WORK)
    public void storeFile(BitmapDrawable drawable, String fileName) {
        getView().loading(getString(R.string.save_ing), true);
        File file = ImageUtils.bitmapToFile(drawable.getBitmap(), Environment.DIRECTORY_DCIM + "/Camera/", fileName, false);
        if (file != null && file.exists()) {
            L.i(initTag(),"save bitmap to file:" + file.getAbsolutePath());
            QsHelper.getInstance().getApplication().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            QsToast.show(getString(R.string.save_photo_success));
        }
        getView().loadingClose();
    }
}
