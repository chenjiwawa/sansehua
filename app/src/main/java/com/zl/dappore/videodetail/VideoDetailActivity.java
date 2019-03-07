package com.zl.dappore.videodetail;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.zl.dappore.R;
import com.zl.dappore.comment.CommentI;
import com.zl.dappore.common.utils.KeyboardHelper;
import com.zl.dappore.videodetail.fragment.VideoDetailPagerFragment;

public class VideoDetailActivity extends QsActivity implements CommentI{
    VideoDetailPagerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void initData(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        fragment = (VideoDetailPagerFragment) VideoDetailPagerFragment.getInstance(extras == null ? new Bundle() : extras);
        commitFragment(fragment);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        L.i(initTag(), " dispatchTouchEvent event " + event.toString());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (fragment != null) {
                    fragment.setTouch(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (fragment != null) {
                    fragment.setTouch(false);
                }
                break;

        }
        return super.dispatchTouchEvent(event);
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        KeyboardHelper.hideSoftInputFromWindow(this);
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public void hideComment() {
        KeyboardHelper.hideSoftInputFromWindow(this);
    }


    @Override
    public boolean isTransparentStatusBar() {
        return true;
    }

}
