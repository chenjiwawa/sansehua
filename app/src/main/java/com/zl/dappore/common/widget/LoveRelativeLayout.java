package com.zl.dappore.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zl.dappore.R;

import java.util.Random;

public class LoveRelativeLayout extends RelativeLayout {
    private static final String TAG = "LoveRelativeLayout";

    Context mContext;
    float[] num = {-35f, -25f, 0f, 25f, 35f};
    long[] mHits = new long[3];
    float[] mDownX = new float[3];
    float[] mDownY = new float[3];

    private GestureI gestureI;

    public LoveRelativeLayout(Context context) {
        super(context);
        mContext = context;
    }

    public LoveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setGestureI(GestureI gestureI) {
        this.gestureI = gestureI;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.arraycopy(mDownX, 1, mDownX, 0, mDownX.length - 1);
                System.arraycopy(mDownY, 1, mDownY, 0, mDownY.length - 1);
                mDownX[mDownX.length - 1] = event.getX();
                mDownY[mDownY.length - 1] = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();

        if (mDownX[0] != -1f && mDownX[1] != -1f && mDownY[0] != -1f && mDownY[1] != -1f && Math.abs(mDownX[1] - mDownX[0]) < 200 && Math.abs(mDownY[1] - mDownY[0]) < 200)

            if (mHits[0] >= (SystemClock.uptimeMillis() - 500) && Math.abs(mHits[1] - mHits[0]) > 20) {

                ImageView iv = new ImageView(mContext);

                RelativeLayout.LayoutParams lp = new LayoutParams(300, 300);
                lp.leftMargin = (int) (event.getX() - 150f);
                lp.topMargin = (int) (event.getY() - 300f);

                iv.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_heart));
                iv.setLayoutParams(lp);

                addView(iv);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(
                        //缩放动画，X轴2倍缩小至0.9倍
                        scaleAni(iv, "scaleX", 2f, 0.9f, 100l, 0l))
                        //缩放动画，Y轴2倍缩放至0.9倍
                        .with(scaleAni(iv, "scaleY", 2f, 0.9f, 100l, 0l))
                        //旋转动画，随机旋转角
                        .with(rotation(iv, 0l, 0l, num[new Random().nextInt(4)]))
                        //渐变透明动画，透明度从0-1
                        .with(alphaAni(iv, 0F, 1F, 100l, 0l))
                        //缩放动画，X轴0.9倍缩小至
                        .with(scaleAni(iv, "scaleX", 0.9f, 1F, 50l, 150l))
                        //缩放动画，Y轴0.9倍缩放至
                        .with(scaleAni(iv, "scaleY", 0.9f, 1F, 50l, 150l))
                        //位移动画，Y轴从0上移至600
                        .with(translationY(iv, 0F, -600F, 800l, 400l))
                        //透明动画，从1-0
                        .with(alphaAni(iv, 1F, 0F, 300l, 400l))
                        //缩放动画，X轴1至3倍
                        .with(scaleAni(iv, "scaleX", 1F, 3f, 700l, 400l))
                        //缩放动画，Y轴1至3倍
                        .with(scaleAni(iv, "scaleY", 1F, 3f, 700l, 400l));

                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        if (gestureI != null) {
                            gestureI.onDoubleTap();
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        removeAllViews();
//                        mHits = new long[3];
//                        mDownX = new float[3];
//                        mDownX[0] = -1f;
//                        mDownX[1] = -1f;
//                        mDownX[2] = -1f;
//                        mDownY = new float[3];
//                        mDownY[0] = -1f;
//                        mDownY[1] = -1f;
//                        mDownY[2] = -1f;
                    }
                });
            }

        return super.onTouchEvent(event);
    }

    private ObjectAnimator scaleAni(View view, String propertyName, Float from, Float to, Long time, Long delayTime) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, propertyName, from, to);
        ani.setDuration(time);
        ani.setStartDelay(delayTime);
        ani.setInterpolator(new LinearInterpolator());
        return ani;
    }


    private ObjectAnimator translationX(View view, Float from, Float to, Long time, Long delayTime) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, "translationX", from, to);
        ani.setDuration(time);
        ani.setStartDelay(delayTime);
        ani.setInterpolator(new LinearInterpolator());
        return ani;
    }

    private ObjectAnimator translationY(View view, Float from, Float to, Long time, Long delayTime) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, "translationY", from, to);
        ani.setDuration(time);
        ani.setStartDelay(delayTime);
        ani.setInterpolator(new LinearInterpolator());
        return ani;
    }

    private ObjectAnimator alphaAni(View view, Float from, Float to, Long time, Long delayTime) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, "alpha", from, to);
        ani.setDuration(time);
        ani.setStartDelay(delayTime);
        ani.setInterpolator(new LinearInterpolator());
        return ani;
    }


    private ObjectAnimator rotation(View view, Long time, Long delayTime, float... values) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, "rotation", values);
        ani.setDuration(time);
        ani.setStartDelay(delayTime);
        ani.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
        return ani;
    }

    public interface GestureI {
        public void onDoubleTap();
    }

}