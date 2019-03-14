package com.zl.dappore.common.widget.verticalviewpager;

import android.content.Context;
import android.util.AttributeSet;

public class HVVerticalViewPager extends VerticalViewPager {
    public HVVerticalViewPager(Context context) {
        super(context);
    }

    public HVVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                AgoraLog.i("MyVerticalViewPager", " onInterceptTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                AgoraLog.i("MyVerticalViewPager", " onInterceptTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                AgoraLog.i("MyVerticalViewPager", " onInterceptTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            default:
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                AgoraLog.i("MyVerticalViewPager", " dispatchTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                AgoraLog.i("MyVerticalViewPager", " dispatchTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                AgoraLog.i("MyVerticalViewPager", " dispatchTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
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
//                AgoraLog.i("MyVerticalViewPager", " onTouchEvent ACTION_DOWN MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                AgoraLog.i("MyVerticalViewPager", " onTouchEvent ACTION_MOVE MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                AgoraLog.i("MyVerticalViewPager", " onTouchEvent ACTION_UP MotionEvent  " + " " + ev.getRawX() + " " + ev.getRawY());
//                break;
//            default:
//                break;
//        }
//
//        return super.onTouchEvent(ev);
//    }
}
