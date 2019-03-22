package com.tricolorflower.heartbeat.photo.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.common.utils.image.LocalImageLoader;
import com.tricolorflower.heartbeat.common.utils.zoom.ZoomHelper;
import com.tricolorflower.heartbeat.common.utils.zoom.ZoomListener;
import com.tricolorflower.heartbeat.photo.PhotoCutActivity;
import com.tricolorflower.heartbeat.photo.model.PhotoConstants;

import java.io.File;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:34
 * @Description
 */
public class PhotoListAdapterItem extends QsListAdapterItem<File[]> {
    @Bind(R.id.ll_camera)
    ViewGroup ll_camera;
    @Bind(R.id.iv_photo_0)
    ImageView iv_photo_0;
    @Bind(R.id.iv_photo_1)
    ImageView iv_photo_1;
    @Bind(R.id.iv_photo_2)
    ImageView iv_photo_2;
    private File[] mData;
    private int currentPosition;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Activity activity;

    public PhotoListAdapterItem(int mBitmapWidth, int mBitmapHeight, Activity activity) {
        this.activity = activity;
        this.mBitmapWidth = mBitmapWidth;
        this.mBitmapHeight = mBitmapHeight;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_photo_list;
    }

    @Override
    public void init(View contentView) {
        super.init(contentView);

        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_0).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_0 onClick ");
                gotoPhotoCut(0, v);
            }

            @Override
            public void onViewStartedZooming(View view) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_0 onViewStartedZooming ");
            }

            @Override
            public void onViewEndedZooming(View view) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_0 onViewEndedZooming ");
            }
        });

        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_1).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_1 onClick ");
                gotoPhotoCut(1, v);
            }

            @Override
            public void onViewStartedZooming(View view) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_1 onViewStartedZooming ");
            }

            @Override
            public void onViewEndedZooming(View view) {
                L.i(initTag()," PhotoListAdapterItem iv_photo_1 onViewEndedZooming ");
            }
        });

        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_2).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                gotoPhotoCut(2, v);
            }

            @Override
            public void onViewStartedZooming(View view) {

            }

            @Override
            public void onViewEndedZooming(View view) {

            }
        });
//        new Zoomy.Builder(activity).target(iv_photo_0).tapListener(new TapListener() {
//            @Override
//            public void onTap(View v) {
//                gotoPhotoCut(0, v);
//            }
//        }).register();
//        new Zoomy.Builder(activity).target(iv_photo_1).tapListener(new TapListener() {
//            @Override
//            public void onTap(View v) {
//                gotoPhotoCut(1, v);
//            }
//        }).register();
//        new Zoomy.Builder(activity).target(iv_photo_2).tapListener(new TapListener() {
//            @Override
//            public void onTap(View v) {
//                gotoPhotoCut(2, v);
//            }
//        }).register();
    }

    @Override
    public void bindData(File[] data, int position, int count) {
        this.mData = data;
        this.currentPosition = position;
        if (position == 0 && data[0] == null) {
            ll_camera.setVisibility(View.VISIBLE);
        } else {
            ll_camera.setVisibility(View.GONE);
        }
        setImageBitmap(iv_photo_0, 0);
        setImageBitmap(iv_photo_1, 1);
        setImageBitmap(iv_photo_2, 2);
    }

    private void setImageBitmap(final ImageView imageView, final int position) {
        if (mData != null && mData.length > position && imageView != null) {
            if (mData[position] != null) {
                imageView.setTag(mData[position].getPath());
                Bitmap bitmap = LocalImageLoader.getInstance().loadImage(mData[position].getPath(), new Point(mBitmapWidth, mBitmapHeight), new LocalImageLoader.ImageCallBack() {
                    @Override
                    public void onImageLoader(Bitmap bitmap, String path) {
                        if (bitmap != null) {
                            if (imageView.getTag() != null && imageView.getTag().equals(path)) {
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    }
                });
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    imageView.setImageResource(R.color.color_dark_gray);
                }
            } else {
                if (currentPosition == 0 && position == 0 && mData[position] == null) {
                    imageView.setImageResource(R.color.color_dark_gray);
                } else {
                    imageView.setImageBitmap(null);
                }
            }
        }
    }


//    @OnClick({R.id.iv_photo_0, R.id.iv_photo_1, R.id.iv_photo_2, R.id.rl_item_0}) public void onItemViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_photo_0:
//                gotoPhotoCut(0, view);
//                break;
//            case R.id.iv_photo_1:
//                gotoPhotoCut(1, view);
//                break;
//            case R.id.iv_photo_2:
//                gotoPhotoCut(2, view);
//                break;
//            case R.id.rl_item_0:
//                gotoPhotoCut(0, view);
//                break;
//        }
//    }

    @OnClick({R.id.rl_item_0,R.id.rl_item_1,R.id.rl_item_2})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_0:
                gotoPhotoCut(0, view);
                break;
            case R.id.rl_item_1:
                gotoPhotoCut(1, view);
                break;
            case R.id.rl_item_2:
                gotoPhotoCut(2, view);
                break;
        }
    }

    private void gotoPhotoCut(int position, View view) {
        if (mData != null && mData.length > position) {
            if (mData[position] != null && !TextUtils.isEmpty(mData[position].getAbsolutePath())) {
                Bundle bundle = new Bundle();
                bundle.putString(PhotoConstants.BUNDLE_KEY_PHOTO_PATH, mData[position].getAbsolutePath());
                ActivityOptionsCompat compact = ActivityOptionsCompat.makeSceneTransitionAnimation(QsHelper.getInstance().getScreenHelper().currentActivity(), view, QsHelper.getInstance().getString(R.string.activity_options_compat_photo_cut));
                QsHelper.getInstance().intent2Activity(PhotoCutActivity.class, bundle, compact);
            } else if (currentPosition == 0 && position == 0 && mData[position] == null) {
                QsHelper.getInstance().eventPost(new UserInfoEvent.OnAlbumCameraClick());
            }
        }
    }
}
