package com.zl.dappore.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zl.dappore.R;


/**
 * @CreateBy qsmaxmin
 * @Date 16/11/25  下午3:32
 * @Description
 */
public class BeautyRatingBar extends View {

    private OnRatingChangeListener onRatingChangeListener;
    private Drawable emptyStarDrawable;
    private Drawable fillStarDrawable;
    private boolean                isIndicator;
    private int                    numStars;
    private float                  rating;
    private float                  starMargin;

    private Bitmap bpFill;
    private Bitmap bpEmpty;
    private Bitmap bpRating;

    public BeautyRatingBar(Context context) {
        this(context, null);
    }

    public BeautyRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BeautyRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BeautyRatingBar);
        emptyStarDrawable = typedArray.getDrawable(R.styleable.BeautyRatingBar_rb_emptyStar);
        fillStarDrawable = typedArray.getDrawable(R.styleable.BeautyRatingBar_rb_fillStar);
        isIndicator = typedArray.getBoolean(R.styleable.BeautyRatingBar_rb_isIndicator, false);
        numStars = typedArray.getInt(R.styleable.BeautyRatingBar_rb_numStars, 5);
        rating = typedArray.getFloat(R.styleable.BeautyRatingBar_rb_rating, 0.5f);
        starMargin = typedArray.getDimension(R.styleable.BeautyRatingBar_rb_starMargin, 0);
        typedArray.recycle();

        bpFill = ((BitmapDrawable) fillStarDrawable).getBitmap();
        bpEmpty = ((BitmapDrawable) emptyStarDrawable).getBitmap();
        updateRatingBitmap();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isIndicator) {
                    return false;
                }
                float x = event.getX();
                if (x < bpFill.getWidth() + getPaddingLeft()) {
                    setRating(1);
                } else {
                    float index = (x - getPaddingLeft()) / (bpFill.getWidth() + starMargin);
                    setRating((int) index + 1);
                }
                return true;
            }
        });
    }

    private void updateRatingBitmap() {
        int width = (int) ((rating - (int) rating) * bpFill.getWidth());
        if (width > 0) {
            bpRating = Bitmap.createBitmap(bpFill, 0, 0, width, bpFill.getHeight());
        } else {
            bpRating = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(bpFill.getHeight() + getPaddingTop() + getPaddingBottom(), MeasureSpec.getMode(heightMeasureSpec));
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(((int) (bpFill.getWidth() + starMargin)) * numStars - (int) starMargin + getPaddingLeft() + getPaddingRight(), MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < numStars; i++) {
            canvas.drawBitmap(bpEmpty, i * (bpFill.getWidth() + starMargin) + getPaddingLeft(), getPaddingTop(), null);
            if (i < (int) rating) {
                canvas.drawBitmap(bpFill, i * (bpFill.getWidth() + starMargin) + getPaddingLeft(), getPaddingTop(), null);
            }
        }
        if (bpRating != null) {
            canvas.drawBitmap(bpRating, ((int) rating) * (bpFill.getWidth() + starMargin) + getPaddingLeft(), getPaddingTop(), null);
        }
    }

    public OnRatingChangeListener getOnRatingChangeListener() {
        return onRatingChangeListener;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public Drawable getEmptyStarDrawable() {
        return emptyStarDrawable;
    }

    public void setEmptyStarDrawable(Drawable emptyStarDrawable) {
        this.emptyStarDrawable = emptyStarDrawable;
        invalidate();
    }

    public Drawable getFillStarDrawable() {
        return fillStarDrawable;
    }

    public void setFillStarDrawable(Drawable fillStarDrawable) {
        this.fillStarDrawable = fillStarDrawable;
        updateRatingBitmap();
        invalidate();
    }

    public boolean isIndicator() {
        return isIndicator;
    }

    public void setIsIndicator(boolean isIndicator) {
        this.isIndicator = isIndicator;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        if (this.numStars != numStars) {
            this.numStars = numStars;
            invalidate();
        }
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        if (this.rating != rating) {
            rating = rating > numStars ? numStars : (rating < 0 ? 0 : rating);
            this.rating = rating;
            updateRatingBitmap();
            invalidate();
            if (onRatingChangeListener != null) {
                onRatingChangeListener.onRatingChange(rating);
            }
        }
    }

    public float getStarMargin() {
        return starMargin;
    }

    public void setStarMargin(float starMargin) {
        if (this.starMargin != starMargin) {
            this.starMargin = starMargin;
            invalidate();
        }
    }

    /**
     * change start listener
     */
    public interface OnRatingChangeListener {
        void onRatingChange(float RatingCount);
    }
}
