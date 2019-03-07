package com.zl.dappore.photo.adapter;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.image.LocalImageLoader;
import com.zl.dappore.common.utils.image.ModelImageGroup;
import com.zl.dappore.photo.PhotoViewLocalActivity;
import com.zl.dappore.photo.model.PhotoConstants;

import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:34
 * @Description
 */
public class PhotoDirListAdapterItem extends QsListAdapterItem<ModelImageGroup> {

    @Bind(R.id.iv_header)
    ImageView iv_header;
    @Bind(R.id.tv_name)
    TextView tv_name;

    private ModelImageGroup modelImageGroup;
    private final int photoWidth;
    private final int photoHeight;

    @Override
    public int getItemLayout() {
        return R.layout.item_photo_dir_list;
    }

    public PhotoDirListAdapterItem(int photoWidth, int photoHeight) {
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
    }

    @Override
    public void bindData(ModelImageGroup modelImageGroup, int position, int count) {
        this.modelImageGroup = modelImageGroup;
        if (modelImageGroup != null && !TextUtils.isEmpty(modelImageGroup.getFirstImgPath())) {
            tv_name.setText(modelImageGroup.getDirName());
            final String imagePath = modelImageGroup.getFirstImgPath();
            if (TextUtils.isEmpty(imagePath)) {
                return;
            }
            iv_header.setTag(imagePath);
            Bitmap bitmap = LocalImageLoader.getInstance().loadImage(imagePath, new Point(photoWidth, photoHeight), new LocalImageLoader.ImageCallBack() {
                @Override
                public void onImageLoader(Bitmap bitmap, String path) {
                    if (bitmap != null && imagePath.equals(path)) {
                        iv_header.setImageBitmap(bitmap);
                    }
                }
            });
            if (bitmap != null) {
                iv_header.setImageBitmap(bitmap);
            } else {
                iv_header.setImageBitmap(null);
            }
        }
    }


    @OnClick({R.id.ll_main})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.ll_main:
                gotoPhotoList();
                break;
        }
    }

    private void gotoPhotoList() {
        if (modelImageGroup != null) {
            ArrayList<ModelImageGroup> list = new ArrayList<>();
            list.add(modelImageGroup);
            Bundle bundle = new Bundle();
            bundle.putSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST, list);
            bundle.putInt(PhotoConstants.BUNDLE_KEY_STATE, PhotoConstants.KEY_TO_SINGLE_PHOTO_LIST);
            QsHelper.getInstance().intent2Activity(PhotoViewLocalActivity.class, bundle);
        }
    }
}
