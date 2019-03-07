package com.zl.dappore.photo;

import android.Manifest;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.common.event.UserInfoEvent;
import com.zl.dappore.common.utils.CacheUtils;
import com.zl.dappore.common.utils.MD5Util;
import com.zl.dappore.common.utils.image.ImageLoadTask;
import com.zl.dappore.common.utils.image.ModelImage;
import com.zl.dappore.common.utils.image.ModelImageGroup;
import com.zl.dappore.common.utils.image.ModelLocalImageGroupCache;
import com.zl.dappore.common.utils.image.OnTaskResultListener;
import com.zl.dappore.common.utils.image.TaskUtil;
import com.zl.dappore.common.utils.permission.PermissionUtil;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.photo.fragment.PhotoDirListFragment;
import com.zl.dappore.photo.fragment.PhotoListFragment;
import com.zl.dappore.photo.fragment.PhotoMultipleChooseFragment;
import com.zl.dappore.photo.model.PhotoConstants;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import butterknife.OnClick;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:23
 * @Description 展示本地所有图片的依赖
 * 1，如果没有传入任何数据，则展示本地所有的图片，并按照时间顺序排列
 * 2，
 */

public class PhotoViewLocalActivity extends QsABActivity {
    @Bind(android.R.id.title)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tv_right;
    private int mState;
    private Bundle bundle;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_right_text;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        if (mState == PhotoConstants.KEY_TO_All_PHOTO_LIST) {
        title.setText(getString(R.string.image));
//        }
        bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
            mState = PhotoConstants.KEY_TO_All_PHOTO_LIST;
            bundle.putInt(PhotoConstants.BUNDLE_KEY_STATE, PhotoConstants.KEY_TO_All_PHOTO_LIST);
            updateUIByCacheData();
        } else {
            mState = bundle.getInt(PhotoConstants.BUNDLE_KEY_STATE, PhotoConstants.KEY_TO_All_PHOTO_LIST);
            ArrayList<ModelImageGroup> modelImageGroups = (ArrayList<ModelImageGroup>) bundle.getSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST);
            if (modelImageGroups != null) {
                commitFragmentOnUIThread(bundle);
            } else {
                updateUIByCacheData();
            }
        }

    }

    @Override
    public void setActivityTitle(Object value, int code) {
        super.setActivityTitle(value, code);
        title.setText(String.valueOf(value));
        switch (code) {
            case PhotoConstants.KEY_TO_PHOTO_DIR_LIST:
            case PhotoConstants.KEY_TO_SINGLE_PHOTO_LIST:
                tv_right.setVisibility(View.GONE);
                break;
            case PhotoConstants.KEY_TO_All_PHOTO_LIST:
                tv_right.setVisibility(View.VISIBLE);
                tv_right.setText(getString(R.string.dir));
                break;
            case PhotoConstants.KEY_TO_PHOTO_CHOOSE_LIST:
                tv_right.setVisibility(View.VISIBLE);
                tv_right.setText(QsHelper.getInstance().getString(R.string.ok_d, 0));
                break;
        }
    }


    @Override
    @OnClick({R.id.layout_title_back, R.id.tv_right})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.layout_title_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                L.i(initTag(), " PhotoViewLocalActivity tv_right " + mState);
                if (mState == PhotoConstants.KEY_TO_PHOTO_CHOOSE_LIST) {
                    //getFManager()
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(PhotoMultipleChooseFragment.class.getSimpleName());
                    if (fragment instanceof PhotoMultipleChooseFragment) {
                        ((PhotoMultipleChooseFragment) fragment).onChoosePhotoComplete();
                    }
                } else {
                    commitBackStackFragment(PhotoDirListFragment.getInstance(bundle), PhotoDirListFragment.class.getSimpleName());
                }
                break;
        }
    }


    private void commitFragmentOnUIThread(final Bundle bundle) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            commitFragment(bundle);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    commitFragment(bundle);
                }
            });
        }
    }

    private void commitFragment(Bundle bundle) {
        switch (mState) {
            case PhotoConstants.KEY_TO_PHOTO_DIR_LIST:
                commitFragment(PhotoDirListFragment.getInstance(bundle));
                break;

            case PhotoConstants.KEY_TO_All_PHOTO_LIST:
                commitFragment(PhotoListFragment.getInstance(bundle));
                break;

            case PhotoConstants.KEY_TO_SINGLE_PHOTO_LIST:
                commitFragment(PhotoListFragment.getInstance(bundle));
                break;
            case PhotoConstants.KEY_TO_PHOTO_CHOOSE_LIST:
                commitFragment(PhotoMultipleChooseFragment.getInstance(bundle));
                break;
        }
    }

    public void setOnChoosePhotoFragmentSelectedPhoto(int count) {
        tv_right.setText(QsHelper.getInstance().getString(R.string.ok_d, count));
    }

    /**
     * 读取本地图片文件，过滤相同图片
     */
    public void updateLocalPhotoData(final boolean shouldCommitFragment) {
        final long startTime = System.currentTimeMillis();
        L.i(initTag(), "**************开始获取图片列表");
        PermissionUtil.getInstance()//
                .setActivity(QsHelper.getInstance().getScreenHelper().currentActivity())//
                .setShowCustomDialog(true)//
                .addWantPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
                .setListener(new PermissionUtil.PermissionListener() {
                    @Override
                    public void onPermissionCallback(final int requestCode, boolean isGrantedAll) {
                        if (isGrantedAll) {
                            ImageLoadTask mLoadTask = new ImageLoadTask(PhotoViewLocalActivity.this, new OnTaskResultListener() {
                                @SuppressWarnings("unchecked")
                                @Override
                                public void onResult(boolean success, String error, final Object result) {
                                    if (success && result != null && result instanceof ArrayList) {
                                        L.i(initTag(), "************获取图片列表成功,耗时：" + (System.currentTimeMillis() - startTime));
                                        QsHelper.getInstance().getThreadHelper().getWorkThreadPoll().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                ArrayList<ModelImageGroup> allPhotoList = new ArrayList<>();
                                                allPhotoList.addAll((Collection<? extends ModelImageGroup>) result);
                                                for (ModelImageGroup group : allPhotoList) {
                                                    L.i(initTag(), "*********图片目录：" + group.getDirName() + "  过滤前大小：" + group.getImages().size());
                                                    ArrayList<ModelImage> tempImageList = new ArrayList<>();
                                                    for (ModelImage localImage : group.getImages()) {
                                                        File file = new File(localImage.path);
                                                        if (file.exists()) {
                                                            localImage.md5Str = MD5Util.getMd5Code(file);
                                                            if (!tempImageList.contains(localImage)) {
                                                                tempImageList.add(localImage);
                                                            }
                                                        }
                                                    }
                                                    L.i(initTag(), "*********图片目录：" + group.getDirName() + "  过滤后大小：" + tempImageList.size());
                                                    group.setImages(tempImageList);
                                                }
                                                L.i(initTag(), "*********获取图片 + 过滤相同图片总耗时：" + (System.currentTimeMillis() - startTime) + "  图片数目：" + allPhotoList.size());
                                                if (shouldCommitFragment) {
                                                    bundle.putSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST, allPhotoList);
                                                    commitFragmentOnUIThread(bundle);
                                                }
                                                saveLocalPhotoData(new ModelLocalImageGroupCache(allPhotoList));
                                                L.i(initTag(), "*********序列化到本地，总耗时：" + (System.currentTimeMillis() - startTime));
                                            }
                                        });
                                    }
                                }
                            });
                            TaskUtil.execute(mLoadTask);
                        }
                    }
                }).startRequest();
    }

    private void saveLocalPhotoData(ModelLocalImageGroupCache model) {
        if (model != null && model.localImageList != null && model.localImageList.size() > 0) {
            CacheUtils<ModelLocalImageGroupCache> cacheUtils = new CacheUtils<>();
            cacheUtils.saveObject2File(model, HomeConstants.CACHE_LOCAL_IMAGE_LIST);
        }
    }

    private void updateUIByCacheData() {
        final long startTime = System.currentTimeMillis();
        QsHelper.getInstance().getThreadHelper().getWorkThreadPoll().execute(new Runnable() {
            @Override
            public void run() {
                CacheUtils<ModelLocalImageGroupCache> headerCache = new CacheUtils<>();
                ModelLocalImageGroupCache modelLocalImageGroupCache = headerCache.getObjectFromFile(HomeConstants.CACHE_LOCAL_IMAGE_LIST, ModelLocalImageGroupCache.class);
                if (modelLocalImageGroupCache != null && modelLocalImageGroupCache.localImageList != null) {
                    bundle.putSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST, modelLocalImageGroupCache.localImageList);
                    commitFragmentOnUIThread(bundle);
                    updateLocalPhotoData(false);
                } else {
                    updateLocalPhotoData(true);
                }
                L.i(initTag(), "*************获取本地图片路径的缓存数据耗时：" + (System.currentTimeMillis() - startTime));
            }
        });
    }

    @Subscribe
    public void onEvent(UserInfoEvent.OnPhotoCutEvent event) {
        activityFinish();
    }

    @Subscribe
    public void onEvent(UserInfoEvent.OnAlbumCameraClick event) {
        if (mState != PhotoConstants.KEY_TO_PHOTO_CHOOSE_LIST) {
            activityFinish();
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}
