package com.zl.dappore.photo;

import android.Manifest;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.ImageHelper;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.utils.permission.PermissionUtil;
import com.zl.dappore.common.widget.photoview.PhotoView;
import com.zl.dappore.common.widget.photoview.PhotoViewAttacher;
import com.zl.dappore.photo.model.ModelPhoto;
import com.zl.dappore.photo.model.PhotoConstants;
import com.zl.dappore.photo.presenter.PhotoViewPresenter;

import java.util.List;


/**
 * @CreateBy qsmaxmin
 * @Date 16/11/28  下午4:32
 * @Description
 */
public class PhotoViewActivity extends QsActivity<PhotoViewPresenter> implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tv_index)
    TextView tv_index;
    @Bind(R.id.tv_save)
    TextView tv_save;
    @Bind(R.id.pager)
    ViewPager pager;

    private List<ModelPhoto> imageUrlList;
    private int              mCurrentPosition;
    private int              index;
    private PhotoPageAdapter adapter;

    @Override
    public int layoutId() {
        return R.layout.activity_photo;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        imageUrlList = (List<ModelPhoto>) bundle.getSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST);
        if (imageUrlList == null || imageUrlList.size() < 1) {
            QsToast.show(getString(R.string.data_error_please_try_again));
            close();
            return;
        }
        tv_index.setVisibility(imageUrlList.size() > 1 ? View.VISIBLE : View.GONE);
        index = bundle.getInt(PhotoConstants.BUNDLE_KEY_SHOW_INDEX, -1);
        int currentIndex = index >= imageUrlList.size() ? (imageUrlList.size() - 1) : index;
        initViewPager(currentIndex);
        tv_index.setText(currentIndex < 0 ? "" : currentIndex + 1 + "/" + imageUrlList.size());
    }

    private void initViewPager(int currentIndex) {
        adapter = new PhotoPageAdapter();
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(1);
        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(currentIndex < 0 ? 0 : currentIndex);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.mCurrentPosition = position;
        tv_index.setText(String.valueOf((position + 1) + "/" + imageUrlList.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.tv_save)
    public void onViewClick(View view) {
        super.onViewClick(view);
        PermissionUtil.getInstance()//
                .setActivity(this)//
                .addWantPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
                .setShowCustomDialog(true)//
                .setListener(new PermissionUtil.PermissionListener() {
                    @Override
                    public void onPermissionCallback(int requestCode, boolean isGrantedAll) {
                        if (isGrantedAll) {
                            if (adapter == null || imageUrlList == null || mCurrentPosition >= imageUrlList.size()) return;
                            PhotoView childView = adapter.getPrimaryItem();
                            if (childView != null && childView.getDrawable() != null) {
                                BitmapDrawable drawable = (BitmapDrawable) childView.getDrawable();
                                String fileName = Uri.parse(imageUrlList.get(mCurrentPosition).pic).getPath().replace("/", "_");
                                getPresenter().storeFile(drawable, fileName);
                            }
                        }
                    }
                }).startRequest();
    }

    private class PhotoPageAdapter extends PagerAdapter {
        PhotoView mCurrentView;

        @Override
        public int getCount() {
            return imageUrlList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            QsHelper.getInstance().getImageHelper().createRequest().load(imageUrlList.get(position).pic).resize(CommonUtils.getScreenWidth() / 2, CommonUtils.getScreenHeight() / 2).centerInside().into(photoView, new ImageHelper.ImageRequestListener() {
                @Override
                public void onLoadFailed(String s) {
                    tv_save.setVisibility(View.GONE);
                }

                @Override
                public void onSuccess(Drawable drawable, Object o) {
                    tv_save.setVisibility(View.VISIBLE);
                }
            });
            if (photoView.getParent() == null) {
                container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        close();
                    }
                });
            }
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentView = (PhotoView) object;
        }

        PhotoView getPrimaryItem() {
            return mCurrentView;
        }
    }

    @Override
    public boolean onKeyDown(KeyEvent event, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            close();
        }
        return super.onKeyDown(event, keyCode);
    }

    private void close() {
        if (mCurrentPosition == index) {
            activityFinish(true);
        } else {
            activityFinish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    /**
     * private List<ModelPhoto> imageUrlList;
     * private PhotoPageAdapter adapter;
     * SparseArray<PhotoView> imgHolder;
     * private int              mCurrentPosition;
     * private int              index;
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageUrlList.clear();
        adapter = null;
    }
}
