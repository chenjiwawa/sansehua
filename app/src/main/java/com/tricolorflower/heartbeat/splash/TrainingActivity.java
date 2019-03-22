package com.tricolorflower.heartbeat.splash;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.animation.VPColorAnimation;
import com.tricolorflower.heartbeat.common.model.AppConfig;
import com.tricolorflower.heartbeat.home.HomeActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/9/19  下午3:02
 * @Description app引导页
 */
public class TrainingActivity extends QsActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.vp_content)
    ViewPager mViewPager;        // 页面控件
    @Bind(R.id.ipi_indicator)
    IconPageIndicator mPageIndicator;    // 小豆子控件
    @Bind(R.id.vpca_color)
    VPColorAnimation vpca_color;
    @Bind(R.id.tv_click_in)
    TextView tv_click_in;
    private List<Fragment> fragmentList;
    private int lastPosition = -1;

    @Override
    public int layoutId() {
        return R.layout.activity_trainning;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        fragmentList = new ArrayList<>();
        TrainingAdapter trainingAdapter = new TrainingAdapter(getSupportFragmentManager());
        fragmentList.add(ItemFragment.getInstance(ItemFragment.ITEM_STATE_RES, R.mipmap.ic_launcher, ""));
        fragmentList.add(ItemFragment.getInstance(ItemFragment.ITEM_STATE_RES, R.mipmap.ic_launcher, ""));
        fragmentList.add(ItemFragment.getInstance(ItemFragment.ITEM_STATE_RES, R.mipmap.ic_launcher, "goHome"));
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(trainingAdapter);
        vpca_color.setmViewPager(mViewPager, fragmentList.size(), Color.parseColor("#4abbe7"), Color.parseColor("#9824e9"), Color.parseColor("#ffc702"));
        mPageIndicator.setViewPager(mViewPager);
        mPageIndicator.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        vpca_color.onAnimationScrolled(fragmentList.size(), position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        if (lastPosition != position) {
            applyAnimation(tv_click_in, position);
            this.lastPosition = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick(R.id.tv_click_in)
    public void onViewClick(View view) {
        super.onViewClick(view);
        AppConfig.getInstance().commit();
        intent2Activity(HomeActivity.class);
        finish();
    }

    @Override
    protected void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    /**
     * 将状态栏背景色置为透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void applyAnimation(View view, int position) {
        AlphaAnimation alphaAnimation = null;
        switch (position) {
            case 0:
            case 1:
            default:
                if (lastPosition == 2) {
                    tv_click_in.setVisibility(View.VISIBLE);
                    alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            tv_click_in.clearAnimation();
                            tv_click_in.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else {
                    tv_click_in.setVisibility(View.GONE);
                }
                break;
            case 2:
                tv_click_in.setVisibility(View.VISIBLE);
                alphaAnimation = new AlphaAnimation(0, 1);
                break;
        }
        if (alphaAnimation != null) {
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }


    /**
     * 适配器
     */
    public class TrainingAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {

        TrainingAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getIconResId(int i) {
            return R.drawable.shape_circle_green_6dp;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /**
     * 轮播图Item
     */
    public static class ItemFragment extends QsFragment {

        public static final int ITEM_STATE_NET = 0;

        public static final int ITEM_STATE_FILE = 1;

        public static final int ITEM_STATE_RES = 2;

        public static final String ITEM_URL = "ItemFragment_url";

        public static final String ITEM_STATE = "ItemFragment_state";

        public static final String ITEM_WEBVIEW_URL = "ItemFragment_web_view";


        @Bind(R.id.img_item)
        ImageView imgItem;
        String gotoUrl;

        public static ItemFragment getInstance(int state, Object url, String gotoUrl) {
            ItemFragment itemFragment = new ItemFragment();
            Bundle bundle = new Bundle();
            switch (state) {
                case ITEM_STATE_NET:
                case ITEM_STATE_FILE:
                    bundle.putString(ITEM_URL, String.valueOf(url));
                    break;
                case ITEM_STATE_RES:
                    bundle.putInt(ITEM_URL, (Integer) url);
                    break;
            }
            bundle.putInt(ITEM_STATE, state);
            bundle.putString(ITEM_WEBVIEW_URL, gotoUrl);
            itemFragment.setArguments(bundle);
            return itemFragment;
        }

        @Override
        public int layoutId() {
            return R.layout.fragment_training_img;
        }

        @Override
        public void initData(Bundle savedInstanceState) {
            int state = getArguments().getInt(ITEM_STATE);
            gotoUrl = getArguments().getString(ITEM_WEBVIEW_URL);
            String url;
            switch (state) {
                case ITEM_STATE_NET:
                    url = getArguments().getString(ITEM_URL);
                    QsHelper.getInstance().getImageHelper().createRequest().load(url).into(imgItem);
                    break;
                case ITEM_STATE_FILE:
                    url = getArguments().getString(ITEM_URL);
                    if (!TextUtils.isEmpty(url)) {
                        QsHelper.getInstance().getImageHelper().createRequest().load(new File(url)).into(imgItem);
                    }
                    break;
                case ITEM_STATE_RES:
                    int res = getArguments().getInt(ITEM_URL);
                    QsHelper.getInstance().getImageHelper().createRequest().load(res).into(imgItem);
                    break;
            }
        }

//        @Override
//        public boolean isFragmentBackground() {
//            return true;
//        }

//        @Override public boolean fragmentState() {
//            return false;
//        }
    }

    @Override
    public boolean onKeyDown(KeyEvent event, int keyCode) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

}
