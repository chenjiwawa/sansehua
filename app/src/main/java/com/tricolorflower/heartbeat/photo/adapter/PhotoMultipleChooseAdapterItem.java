package com.tricolorflower.heartbeat.photo.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.common.utils.image.LocalImageLoader;
import com.tricolorflower.heartbeat.common.utils.zoom.ZoomHelper;
import com.tricolorflower.heartbeat.common.utils.zoom.ZoomListener;
import com.tricolorflower.heartbeat.photo.fragment.PhotoMultipleChooseFragment;
import com.tricolorflower.heartbeat.photo.model.ModelMultiplePhoto;


/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:34
 * @Description
 */
public class PhotoMultipleChooseAdapterItem extends QsListAdapterItem<ModelMultiplePhoto[]> {

    @Bind(R.id.ll_camera)
    ViewGroup ll_camera;
    @Bind(R.id.iv_photo_0)
    ImageView iv_photo_0;
    @Bind(R.id.iv_photo_1)
    ImageView iv_photo_1;
    @Bind(R.id.iv_photo_2)
    ImageView iv_photo_2;
    @Bind(R.id.rb_0)
    RadioButton rb_0;
    @Bind(R.id.rb_1)
    RadioButton rb_1;
    @Bind(R.id.rb_2)
    RadioButton rb_2;
    private ModelMultiplePhoto[] mData;
    private int currentPosition;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Activity activity;
    private final PhotoMultipleChooseFragment.OnItemViewSelectedListener mListener;

    public PhotoMultipleChooseAdapterItem(int mBitmapWidth, int mBitmapHeight, Activity activity, PhotoMultipleChooseFragment.OnItemViewSelectedListener listener) {
        this.activity = activity;
        this.mBitmapWidth = mBitmapWidth;
        this.mBitmapHeight = mBitmapHeight;
        this.mListener = listener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_photo_multiple_choose;
    }

    @Override
    public void init(View contentView) {
        super.init(contentView);
        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_0).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                gotoPhotoCut(0);
            }

            @Override
            public void onViewStartedZooming(View view) {

            }

            @Override
            public void onViewEndedZooming(View view) {

            }
        });

        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_1).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                gotoPhotoCut(1);
            }

            @Override
            public void onViewStartedZooming(View view) {

            }

            @Override
            public void onViewEndedZooming(View view) {

            }
        });

        ZoomHelper.getInstance().createBuilder(activity).target(iv_photo_2).setZoomListener(new ZoomListener() {
            @Override
            public void onClick(View v) {
                gotoPhotoCut(2);
            }

            @Override
            public void onViewStartedZooming(View view) {

            }

            @Override
            public void onViewEndedZooming(View view) {

            }
        });
//        new Zoomy.Builder(activity).target(iv_photo_2).tapListener(new TapListener() {
//            @Override
//            public void onTap(View v) {
//                gotoPhotoCut(2);
//            }
//        }).register();
    }

    @Override
    public void bindData(ModelMultiplePhoto[] data, int position, int count) {
        this.mData = data;
        this.currentPosition = position;
        if (position == 0 && data[0] == null) {
            ll_camera.setVisibility(View.VISIBLE);
            rb_0.setVisibility(View.GONE);
        } else {
            ll_camera.setVisibility(View.GONE);
            rb_0.setVisibility(View.VISIBLE);
        }
        setImageBitmap(iv_photo_0, rb_0, 0);
        setImageBitmap(iv_photo_1, rb_1, 1);
        setImageBitmap(iv_photo_2, rb_2, 2);
    }

    private void setImageBitmap(final ImageView imageView, RadioButton radioButton, final int position) {
        if (mData != null && mData.length > position && imageView != null && radioButton != null) {
            if (mData[position] != null && mData[position].photoFile != null) {
                ModelMultiplePhoto model = mData[position];
                radioButton.setChecked(model.isSelected);
                imageView.setTag(model.photoFile.getPath());
                Bitmap bitmap = LocalImageLoader.getInstance().loadImage(model.photoFile.getPath(), new Point(mBitmapWidth, mBitmapHeight), new LocalImageLoader.ImageCallBack() {
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


    @OnClick({R.id.rl_item_0})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_0:
                gotoPhotoCut(0);
                break;
        }
    }

    private void gotoPhotoCut(int position) {
        if (mData != null && mData.length > position) {
            if (mData[position] != null && mData[position].photoFile != null && !TextUtils.isEmpty(mData[position].photoFile.getAbsolutePath())) {
                ModelMultiplePhoto model = mData[position];
                if (mListener != null) {
                    switch (position) {
                        case 0:
                            mListener.onItemSelected(rb_0, model);
                            break;
                        case 1:
                            mListener.onItemSelected(rb_1, model);
                            break;
                        case 2:
                            mListener.onItemSelected(rb_2, model);
                            break;
                    }
                }

            } else if (currentPosition == 0 && position == 0 && mData[position] == null) {
                QsHelper.getInstance().eventPost(new UserInfoEvent.OnAlbumCameraClick());
            }
        }
    }
}
