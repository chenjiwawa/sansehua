package com.zl.dappore.common.widget.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MIN_SCALE = 0.84f;

    @Override
    public void transformPage(View page, float position) {

        ViewPager viewPager = (ViewPager) page.getParent();
        int scrollX = viewPager.getScrollX();
        int clientWidth = viewPager.getMeasuredWidth() -
                viewPager.getPaddingLeft() - viewPager.getPaddingRight();
        int offsetX = page.getLeft() - scrollX;
        int parentWidth = viewPager.getMeasuredWidth();
        int childWidth = page.getWidth();
        float deltaX = (float) (parentWidth - childWidth) / 2;
        float transformPos = (offsetX - deltaX) / clientWidth;

        if (transformPos < -1) { // [-Infinity,-1)
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);

        } else if (transformPos <= 1) { // [-1,1]
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(transformPos));
            page.setScaleX(0.96f);
            page.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);

        }

    }

}
