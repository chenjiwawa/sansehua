package com.zl.dappore.common.utils.image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Media;

import com.qsmaxmin.qsbase.common.log.L;

import java.io.File;
import java.util.ArrayList;


/**
 * @CreateBy qsmaxmin
 * @Date 16/10/26
 * @Description 使用contentProvider扫描图片异步任务
 */
public class ImageLoadTask extends AsyncTask<Void, Void, Boolean> {
    public final static String TAG = ImageLoadTask.class.getSimpleName();

    private Context mContext = null;
    private ArrayList<ModelImageGroup> mGroupList;
    private OnTaskResultListener resultListener = null;
    private boolean interrupt = false;

    public ImageLoadTask(Context context) {
        super();
        mContext = context;
        mGroupList = new ArrayList<>();
    }

    public ImageLoadTask(Context context, OnTaskResultListener listener) {
        super();
        mContext = context;
        mGroupList = new ArrayList<>();
        resultListener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Uri mImageUri = Media.EXTERNAL_CONTENT_URI;
        if (mContext == null) return false;
        ContentResolver mContentResolver = mContext.getContentResolver();
        // 构建查询条件，且只查询jpeg和png的图片
        StringBuilder selection = new StringBuilder();
        selection.append(Media.MIME_TYPE).append("=?");
        selection.append(" or ");
        selection.append(Media.MIME_TYPE).append("=?");

        Cursor mCursor = null;
        try {
            mCursor = mContentResolver.query(mImageUri, null, selection.toString(), new String[]{"image/jpeg", "image/png"}, Media.DATE_TAKEN);
            while (mCursor != null && mCursor.moveToNext()) {
                // 获取图片的路径
                String path = mCursor.getString(mCursor.getColumnIndex(Media.DATA));
                // 获取该图片的所在文件夹的路径
                File file = new File(path);
                String parentName;
                if (file.getParentFile() != null) {
                    parentName = file.getParentFile().getName();
                } else {
                    parentName = file.getName();
                }
                // 构建一个imageGroup对象
                ModelImageGroup item = new ModelImageGroup();
                // 设置imageGroup的文件夹名称
                item.setDirName(parentName);
                // 寻找该imageGroup是否是其所在的文件夹中的第一张图片
                int searchIdx = mGroupList.indexOf(item);
                ModelImage modelImage = new ModelImage();
                modelImage.path = path;
                if (searchIdx >= 0) {
                    // 如果是，该组的图片数量+1
                    mGroupList.get(searchIdx).addImage(modelImage);
                } else {
                    // 否则，将该对象加入到groupList中
                    item.addImage(modelImage);
                    mGroupList.add(item);
                }
            }
        } catch (Exception e) {
            L.e(TAG, e.getMessage());
            return false;
        } finally {
            if (mCursor != null && !mCursor.isClosed()) {
                mCursor.close();
            }
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (!interrupt && resultListener != null) {
            resultListener.onResult(success, "", mGroupList);
        }
    }

    /**
     * 中断异步任务
     */
    public void cancel() {
        super.cancel(true);
        interrupt = true;
    }
}
