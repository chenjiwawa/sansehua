package com.tricolorflower.heartbeat.userinfo.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.common.model.AppConstants;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.userinfo.fragment.UserInfoFragment;

import java.io.File;
import java.io.IOException;

/**
 * @CreateBy qsmaxmin
 * @Date 16/9/18  下午4:09
 * @Description
 */
public class UserInfoFragmentPresenter extends DapporePresenter<UserInfoFragment>{

    /**
     * 打开摄像头来选取一张图片
     */
    @ThreadPoint(ThreadType.HTTP)
    public void requestOpenCamera() {
        if (isSdCardAvailable()) {
            File directory = new File(Environment.getExternalStorageDirectory(), AppConstants.PATH_IMG);
            if (!directory.exists()) directory.mkdirs();
            File noMediaFile = new File(directory, ".Nomedia");
            if (!noMediaFile.exists()) {
                try {
                    noMediaFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File imageFile;
            imageFile = new File(directory, System.currentTimeMillis() + ".jpg");
            L.i(initTag(),"requestOpenCamera  file=" + imageFile.getAbsolutePath());
            if (imageFile.exists()) {
                boolean isDelete = imageFile.delete();
            }
            try {
                boolean isCreate = imageFile.createNewFile();
                if (isCreate) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri mCameraUri = getUri(intent, imageFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraUri);
                    getView().openCamera(intent, imageFile, mCameraUri);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            QsToast.show("您的sdcard不能读写，请确认您的sdcard是否是可写状态");
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
