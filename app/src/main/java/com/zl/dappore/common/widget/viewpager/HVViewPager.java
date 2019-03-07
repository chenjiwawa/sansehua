package com.zl.dappore.common.widget.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.qsmaxmin.qsbase.common.widget.viewpager.QsViewPager;

public class HVViewPager extends QsViewPager {
    public HVViewPager(Context context) {
        super(context);
    }

    public HVViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            default:
//                break;
//        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0.0f;
                xLast = ev.getX();
                yLast = ev.getY();

//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_DOWN MotionEvent  " + " xLast " + xLast + " yLast " + yLast + " xDistance " + xDistance + " yDistance " + yDistance);
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);

//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_MOVE MotionEvent  " + " xLast " + xLast + " yLast " + yLast + " xDistance " + xDistance + " yDistance " + yDistance);
                if (xDistance > yDistance)
                    return true;

                break;
            case MotionEvent.ACTION_UP:
//                L.i("MyViewPager", " onInterceptTouchEvent ACTION_UP MotionEvent  " + " xLast " + xLast + " yLast " + yLast + " xDistance " + xDistance + " yDistance " + yDistance);

                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                L.i("MyViewPager", " dispatchTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                L.i("MyViewPager", " dispatchTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                L.i("MyViewPager", " dispatchTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            default:
//                break;
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                L.i("MyViewPager", " onTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                L.i("MyViewPager", " onTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                L.i("MyViewPager", " onTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            default:
//                break;
//        }
//
//        return super.onTouchEvent(ev);
//    }
}
