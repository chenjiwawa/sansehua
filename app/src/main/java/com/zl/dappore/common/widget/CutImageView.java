package com.zl.dappore.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.IOException;

/**
 * @CreateBy qsmaxmin
 * @Date 16/9/18  下午4:56
 * @Description 图片裁切视图
 */
public class CutImageView extends AppCompatImageView implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private CutImageView mythis;

    private static final int BORDERDISTANCE = 50;

    private int height;
    private int width;

    public static final float DEFAULT_MAX_SCALE = 4.0f;
    public static final float DEFAULT_MID_SCALE = 2.0f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;

    private float minScale = DEFAULT_MIN_SCALE;
    private float midScale = DEFAULT_MID_SCALE;
    private float maxScale = DEFAULT_MAX_SCALE;

    private MultiGestureDetector multiGestureDetector;

    private int borderlength;

    private boolean isJusted;

    private final Matrix baseMatrix   = new Matrix();
    private final Matrix drawMatrix   = new Matrix();
    private final Matrix suppMatrix   = new Matrix();
    private final RectF displayRect  = new RectF();
    private final float[] matrixValues = new float[9];

    public CutImageView(Context context) {
        this(context, null);
    }

    public CutImageView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public CutImageView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);

        super.setScaleType(ScaleType.MATRIX);
        mythis = this;

        setOnTouchListener(this);

        multiGestureDetector = new MultiGestureDetector(context);

    }


    /**
     * 划外边框
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        // 边框长度，据屏幕左右边缘50px
        int borderlength = width - BORDERDISTANCE * 2;

        Paint mPaint = new Paint();
        mPaint.setColor(0x99000000);

        // 以下绘制透明暗色区域
        // top
        canvas.drawRect(0, 0, width, (height - borderlength) / 2, mPaint);
        // bottom
        canvas.drawRect(0, (height + borderlength) / 2, width, height, mPaint);
        // left
        canvas.drawRect(0, (height - borderlength) / 2, BORDERDISTANCE, (height + borderlength) / 2, mPaint);
        // right
        canvas.drawRect(borderlength + BORDERDISTANCE, (height - borderlength) / 2, width, (height + borderlength) / 2, mPaint);

        // 以下绘制边框线
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2.0f);
        // top
        canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2, width - BORDERDISTANCE, (height - borderlength) / 2, mPaint);
        // bottom
        canvas.drawLine(BORDERDISTANCE, (height + borderlength) / 2, width - BORDERDISTANCE, (height + borderlength) / 2, mPaint);
        // left
        canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2, BORDERDISTANCE, (height + borderlength) / 2, mPaint);
        // right
        canvas.drawLine(width - BORDERDISTANCE, (height - borderlength) / 2, width - BORDERDISTANCE, (height + borderlength) / 2, mPaint);
    }

    /**
     * 依据图片宽高比例，设置图像初始缩放等级和位置
     */
    private void configPosition() {
        super.setScaleType(ScaleType.MATRIX);
        Drawable d = getDrawable();
        if (d == null) {
            return;
        }
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        final int drawableWidth = d.getIntrinsicWidth();
        final int drawableHeight = d.getIntrinsicHeight();

        borderlength = (int) (viewWidth - BORDERDISTANCE * 2);
        float scale = 1.0f;
        /**
         * 判断图片宽高比例，调整显示位置和缩放大小
         */
        // 图片宽度小于等于高度
        if (drawableWidth <= drawableHeight) {
            // 判断图片宽度是否小于边框, 缩放铺满裁剪边框
            if (drawableWidth < borderlength) {
                baseMatrix.reset();
                scale = (float) borderlength / drawableWidth;
                // 缩放
                baseMatrix.postScale(scale, scale);
            }
            // 图片宽度大于高度
        } else {
            if (drawableHeight < borderlength) {
                baseMatrix.reset();
                scale = (float) borderlength / drawableHeight;
                // 缩放
                baseMatrix.postScale(scale, scale);
            }
        }
        // 移动居中
        baseMatrix.postTranslate((viewWidth - drawableWidth * scale) / 2, (viewHeight - drawableHeight * scale) / 2);

        resetMatrix();
        isJusted = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return multiGestureDetector.onTouchEvent(event);
    }

    private class MultiGestureDetector extends GestureDetector.SimpleOnGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        private final ScaleGestureDetector scaleGestureDetector;
        private final GestureDetector gestureDetector;
        private final float                scaledTouchSlop;

        private VelocityTracker velocityTracker;
        private boolean         isDragging;

        private float lastTouchX;
        private float lastTouchY;
        private float lastPointerCount;

        public MultiGestureDetector(Context context) {
            scaleGestureDetector = new ScaleGestureDetector(context, this);

            gestureDetector = new GestureDetector(context, this);
            gestureDetector.setOnDoubleTapListener(this);

            final ViewConfiguration configuration = ViewConfiguration.get(context);
            scaledTouchSlop = configuration.getScaledTouchSlop();
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = getScale();
            float scaleFactor = detector.getScaleFactor();
            if (getDrawable() != null && ((scale < maxScale && scaleFactor > 1.0f) || (scale > minScale && scaleFactor < 1.0f))) {
                if (scaleFactor * scale < minScale) {
                    scaleFactor = minScale / scale;
                }
                if (scaleFactor * scale > maxScale) {
                    scaleFactor = maxScale / scale;
                }
                suppMatrix.postScale(scaleFactor, scaleFactor, getWidth() / 2, getHeight() / 2);
                checkAndDisplayMatrix();
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {
                return true;
            }

            scaleGestureDetector.onTouchEvent(event);

            /*
             * Get the center x, y of all the pointers
             */
            float x = 0, y = 0;
            final int pointerCount = event.getPointerCount();
            for (int i = 0; i < pointerCount; i++) {
                x += event.getX(i);
                y += event.getY(i);
            }
            x = x / pointerCount;
            y = y / pointerCount;

            /*
             * If the pointer count has changed cancel the drag
             */
            if (pointerCount != lastPointerCount) {
                isDragging = false;
                if (velocityTracker != null) {
                    velocityTracker.clear();
                }
                lastTouchX = x;
                lastTouchY = y;
            }
            lastPointerCount = pointerCount;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (velocityTracker == null) {
                        velocityTracker = VelocityTracker.obtain();
                    } else {
                        velocityTracker.clear();
                    }
                    velocityTracker.addMovement(event);

                    lastTouchX = x;
                    lastTouchY = y;
                    isDragging = false;
                    break;

                case MotionEvent.ACTION_MOVE: {
                    final float dx = x - lastTouchX, dy = y - lastTouchY;

                    if (isDragging == false) {
                        // Use Pythagoras to see if drag length is larger than
                        // touch slop
                        isDragging = Math.sqrt((dx * dx) + (dy * dy)) >= scaledTouchSlop;
                    }

                    if (isDragging) {
                        if (getDrawable() != null) {
                            suppMatrix.postTranslate(dx, dy);
                            checkAndDisplayMatrix();
                        }

                        lastTouchX = x;
                        lastTouchY = y;

                        if (velocityTracker != null) {
                            velocityTracker.addMovement(event);
                        }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    lastPointerCount = 0;
                    if (velocityTracker != null) {
                        velocityTracker.recycle();
                        velocityTracker = null;
                    }
                    break;
            }

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            try {
                float scale = getScale();
                float x = getWidth() / 2;
                float y = getHeight() / 2;

                if (scale < midScale) {
                    post(new AnimatedZoomRunnable(scale, midScale, x, y));
                } else if ((scale >= midScale) && (scale < maxScale)) {
                    post(new AnimatedZoomRunnable(scale, maxScale, x, y));
                } else {
                    post(new AnimatedZoomRunnable(scale, minScale, x, y));
                }
            } catch (Exception e) {
                // Can sometimes happen when getX() and getY() is called
            }

            return true;
        }
    }

    private class AnimatedZoomRunnable implements Runnable {
        // These are 'postScale' values, means they're compounded each iteration
        static final float ANIMATION_SCALE_PER_ITERATION_IN  = 1.07f;
        static final float ANIMATION_SCALE_PER_ITERATION_OUT = 0.93f;

        private final float focalX, focalY;
        private final float targetZoom;
        private final float deltaScale;

        public AnimatedZoomRunnable(final float currentZoom, final float targetZoom, final float focalX, final float focalY) {
            this.targetZoom = targetZoom;
            this.focalX = focalX;
            this.focalY = focalY;

            if (currentZoom < targetZoom) {
                deltaScale = ANIMATION_SCALE_PER_ITERATION_IN;
            } else {
                deltaScale = ANIMATION_SCALE_PER_ITERATION_OUT;
            }
        }

        public void run() {
            suppMatrix.postScale(deltaScale, deltaScale, focalX, focalY);
            checkAndDisplayMatrix();

            final float currentScale = getScale();

            if (((deltaScale > 1f) && (currentScale < targetZoom)) || ((deltaScale < 1f) && (targetZoom < currentScale))) {
                // We haven't hit our target scale yet, so post ourselves
                // again
                postOnAnimation(CutImageView.this, this);

            } else {
                // We've scaled past our target zoom, so calculate the
                // necessary scale so we're back at target zoom
                final float delta = targetZoom / currentScale;
                suppMatrix.postScale(delta, delta, focalX, focalY);
                checkAndDisplayMatrix();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private void postOnAnimation(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    /**
     * Returns the current scale value
     * @return float - current scale value
     */
    public final float getScale() {
        suppMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    public void onGlobalLayout() {
        if (isJusted) {
            return;
        }
        // 调整视图位置
        configPosition();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressWarnings("deprecation") @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * Helper method that simply checks the Matrix, and then displays the result
     */
    private void checkAndDisplayMatrix() {
        checkMatrixBounds();
        setImageMatrix(getDisplayMatrix());
    }

    private void checkMatrixBounds() {
        final RectF rect = getDisplayRect(getDisplayMatrix());
        if (null == rect) {
            return;
        }

        float deltaX = 0, deltaY = 0;
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        //判断移动或缩放后，图片显示是否超出裁剪框边界
        if (rect.top > (viewHeight - borderlength) / 2) {
            deltaY = (viewHeight - borderlength) / 2 - rect.top;
        }
        if (rect.bottom < (viewHeight + borderlength) / 2) {
            deltaY = (viewHeight + borderlength) / 2 - rect.bottom;
        }
        if (rect.left > (viewWidth - borderlength) / 2) {
            deltaX = (viewWidth - borderlength) / 2 - rect.left;
        }
        if (rect.right < (viewWidth + borderlength) / 2) {
            deltaX = (viewWidth + borderlength) / 2 - rect.right;
        }
        // Finally actually translate the matrix
        suppMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * Helper method that maps the supplied Matrix to the current Drawable
     * @param matrix - Matrix to map Drawable against
     * @return RectF - Displayed Rectangle
     */
    private RectF getDisplayRect(Matrix matrix) {
        Drawable d = getDrawable();
        if (null != d) {
            displayRect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(displayRect);
            return displayRect;
        }

        return null;
    }

    /**
     * Resets the Matrix back to FIT_CENTER, and then displays it.s
     */
    private void resetMatrix() {
        if (suppMatrix == null) {
            return;
        }
        suppMatrix.reset();
        setImageMatrix(getDisplayMatrix());
    }

    protected Matrix getDisplayMatrix() {
        drawMatrix.set(baseMatrix);
        drawMatrix.postConcat(suppMatrix);
        return drawMatrix;
    }

    /**
     * 剪切图片，返回剪切后的bitmap对象
     */
    public Bitmap clip() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return Bitmap.createBitmap(bitmap, (getWidth() - borderlength) / 2, (getHeight() - borderlength) / 2, borderlength, borderlength);

    }

    public void setFilePath(final String file_path) {
        ViewTreeObserver vto = mythis.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mythis.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = mythis.getMeasuredHeight();
                int width = mythis.getMeasuredWidth();
                int new_borderlength = width - BORDERDISTANCE * 2;
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;

                Bitmap decodeFile = BitmapFactory.decodeFile(file_path, opts);
                opts.inSampleSize = calculateInSampleSize(opts, new_borderlength);
                opts.inPurgeable = true; // 内存处理策略
                opts.inInputShareable = true;// 内存处理策略
                opts.inJustDecodeBounds = false;
                Bitmap bitmap1 = BitmapFactory.decodeFile(file_path, opts);
                setImageBitmap(rotaingImageView(readPictureDegree(file_path), bitmap1));
                return true;
            }
        });

    }

    /**
     * 获取放大缩小比例
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        // 源图片的宽度
        final int width = options.outWidth > options.outHeight ? options.outWidth : options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth) {
            // 计算出实际宽度和目标宽度的比率
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }
        return inSampleSize;
    }

    /**
     * 旋转图片
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作 友盟崩溃修改  ZWC 空指针
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            // 创建新的图片
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            return resizedBitmap;
        }
        return null;
    }


    /**
     * 读取图片旋转信息
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
