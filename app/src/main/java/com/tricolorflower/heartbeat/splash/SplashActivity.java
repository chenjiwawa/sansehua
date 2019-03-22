package com.tricolorflower.heartbeat.splash;

import android.Manifest;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.ApplicationUtil;
import com.tricolorflower.heartbeat.common.utils.CacheUtils;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.MD5Util;
import com.tricolorflower.heartbeat.common.utils.image.ImageLoadTask;
import com.tricolorflower.heartbeat.common.utils.image.ModelImage;
import com.tricolorflower.heartbeat.common.utils.image.ModelImageGroup;
import com.tricolorflower.heartbeat.common.utils.image.ModelLocalImageGroupCache;
import com.tricolorflower.heartbeat.common.utils.image.OnTaskResultListener;
import com.tricolorflower.heartbeat.common.utils.image.TaskUtil;
import com.tricolorflower.heartbeat.common.utils.permission.PermissionUtil;
import com.tricolorflower.heartbeat.home.HomeActivity;
import com.tricolorflower.heartbeat.home.model.HomeConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/9  下午4:17
 * @Description 欢迎页
 */
public class SplashActivity extends QsActivity {

    @Bind(R.id.iv_mask)
    ImageView iv_mask;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.iv_002)
    ImageView iv_002;
    @Bind(R.id.iv_003)
    ImageView iv_003;
    @Bind(R.id.iv_004)
    ImageView iv_004;
    @Bind(R.id.iv_005)
    ImageView iv_005;
    @Bind(R.id.iv_006)
    ImageView iv_006;

    private final int ANIMATION_DURATION = 2500;

    private Random random;
    private boolean intentFlag;
    private int maxDuration;
    private long startTime;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            executeAnimation(msg.what);
        }
    };

    @Override
    public int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        startTime = System.currentTimeMillis();
        random = new Random();
        if (checkCanDisplayAnimation()) {
            displayPetalAnimation();
        }
        displayLogoAnimation();
        String str = getString(R.string.app_name) + getString(R.string.splash_tips, ApplicationUtil.getAppClientVersion());
        tv_name.setText(str);
    }

//    @OnClick(R.id.tv_name) public void onItemViewClick() {
//        displayPetalAnimation();
//        displayLogoAnimation();
//    }

    /**
     * 执行花瓣动画
     */
    private void displayPetalAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 7; i >= 0; i--) {
                    handler.sendEmptyMessage(i);
                    SystemClock.sleep(100);
                }
            }
        }).start();
    }

    /**
     * 播放logo动画
     */
    private void displayLogoAnimation() {
        final int screenHeight = CommonUtils.getScreenHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, screenHeight).setDuration(ANIMATION_DURATION);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ViewCompat.setTranslationY(iv_mask, value);
                if (value == screenHeight && !intentFlag) {
                    intentFlag = true;
                    L.i(initTag(), "logo动画执行完毕  duration:" + (System.currentTimeMillis() - startTime));
                    getLocalPhotoList();
                }
            }
        });
        valueAnimator.start();
    }

    private void executeAnimation(int index) {
        switch (index) {
            case 0:
                iv_002.startAnimation(getAnimationSet(index));
                break;
            case 1:
                iv_003.startAnimation(getAnimationSet(index));
                break;
            case 2:
                iv_004.startAnimation(getAnimationSet(index));
                break;
            case 3:
                iv_005.startAnimation(getAnimationSet(index));
                break;
            case 4:
                iv_006.startAnimation(getAnimationSet(index));
                break;
        }
    }

    @NonNull
    private AnimationSet getAnimationSet(int index) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, random.nextInt(500) + 260, Animation.RELATIVE_TO_PARENT, random.nextFloat() / 20 + .05f, Animation.RELATIVE_TO_PARENT, random.nextFloat() / 20 + .05f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, (random.nextFloat() * 1.5f - .5f), Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, random.nextFloat() / 2 + .4f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0f);

        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        int duration = random.nextInt(1500) + 1000;
        maxDuration = Math.max(duration + index * 100, maxDuration);
        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }


    private boolean checkCanDisplayAnimation() {
        return iv_002 != null//
                && iv_003 != null//
                && iv_004 != null//
                && iv_005 != null//
                && iv_006 != null;
    }


    private void getLocalPhotoList() {
        PermissionUtil.getInstance()//
                .setActivity(QsHelper.getInstance().getScreenHelper().currentActivity())//
                .setShowCustomDialog(false)//
                .addWantPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
                .setListener(new PermissionUtil.PermissionListener() {
                    @Override
                    public void onPermissionCallback(final int requestCode, boolean isGrantedAll) {
                        if (isGrantedAll) {
                            preReadLocalPhoto();
                        }
                        QsHelper.getInstance().getThreadHelper().getWorkThreadPoll().execute(new Runnable() {
                            @Override
                            public void run() {
                                L.i(initTag(), "Logo动画 duration：" + ANIMATION_DURATION + "   花瓣动画 duration:" + maxDuration);
                                long realDuration = System.currentTimeMillis() - startTime;
                                if (realDuration + 50 < maxDuration) {
                                    L.i(initTag(), "同步时差 延迟执行  duration:" + (maxDuration - realDuration - 50));
                                    SystemClock.sleep(maxDuration - realDuration - 50);
                                }
                                L.i(initTag(), "开始跳转Activity  duration:" + (System.currentTimeMillis() - startTime));
                                intent2Activity(HomeActivity.class);
                                SystemClock.sleep(5000);
                                activityFinish();
                            }
                        });
                    }
                }).startRequest();
    }


    /**
     * 预读本地图片文件，过滤相同图片
     */
    private void preReadLocalPhoto() {
        final long startTime = System.currentTimeMillis();
        ImageLoadTask mLoadTask = new ImageLoadTask(this, new OnTaskResultListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResult(boolean success, String error, final Object result) {
                if (success && result != null && result instanceof ArrayList) {
                    QsHelper.getInstance().getThreadHelper().getWorkThreadPoll().execute(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ModelImageGroup> allPhotoList = new ArrayList<>();
                            allPhotoList.addAll((Collection<? extends ModelImageGroup>) result);
                            for (ModelImageGroup group : allPhotoList) {
                                ArrayList<ModelImage> tempImageList = new ArrayList<>();
                                for (ModelImage localImage : group.getImages()) {
                                    localImage.md5Str = MD5Util.getMd5Code(new File(localImage.path));
                                    if (!tempImageList.contains(localImage)) {
                                        tempImageList.add(localImage);
                                    }
                                }
                                group.setImages(tempImageList);
                            }
                            saveLocalPhotoData(new ModelLocalImageGroupCache(allPhotoList));
                            L.i(initTag(), "*********预读  获取本地图片 + 过滤相同图片 + 序列化到本地，总耗时：" + (System.currentTimeMillis() - startTime));
                        }
                    });
                }
            }
        });
        TaskUtil.execute(mLoadTask);
    }


    /**
     * 序列化到本地
     */
    private void saveLocalPhotoData(ModelLocalImageGroupCache model) {
        if (model != null && model.localImageList != null && model.localImageList.size() > 0) {
            CacheUtils<ModelLocalImageGroupCache> cacheUtils = new CacheUtils<>();
            cacheUtils.saveObject2File(model, HomeConstants.CACHE_LOCAL_IMAGE_LIST);
        }
    }

    @Override
    public boolean onKeyDown(KeyEvent event, int keyCode) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}
