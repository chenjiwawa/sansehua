package com.tricolorflower.heartbeat.common.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;


/**
 * @CreateBy qsmaxmin
 * @Date 2017/4/25 16:38
 * @Description
 */

public class TextViewUtils {

    public static void setTextViewShowMore(final Context context,
                                           final TextView textView,
                                           final int maxLines,
                                           final String originText,
                                           final String endText,
                                           @ColorRes final int endColorID,
                                           final Action1<?> func,
                                           final boolean isExpand) {
        if (android.text.TextUtils.isEmpty(originText)) {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (isExpand) {
                    textView.setText(originText);
                } else {
                    int paddingLeft = textView.getPaddingLeft();
                    int paddingRight = textView.getPaddingRight();
                    TextPaint paint = textView.getPaint();
                    //特意多算一个字的长度，因为可能由于标点符号等影响"endText"在一行显示不全
                    float moreTextLen = paint.measureText(endText) + textView.getTextSize();
                    final float originTextLen = paint.measureText(originText);
                    float availableTextWidth = (textView.getWidth() - paddingLeft - paddingRight) * maxLines;
                    if (originTextLen <= availableTextWidth) {
                        return;
                    }
                    CharSequence ellipsizeStr = android.text.TextUtils.ellipsize(originText, paint,
                            availableTextWidth - moreTextLen, android.text.TextUtils.TruncateAt.END);
                    if (ellipsizeStr.length() < originText.length()) {
                        class MyClick extends ClickableSpan {
                            @Override
                            public void onClick(View widget) {
                                func.call();
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setColor(context.getResources().getColor(endColorID));
                            }
                        }
                        CharSequence temp = ellipsizeStr + endText;
                        SpannableStringBuilder ssb = new SpannableStringBuilder(temp);
                        //这个一定要记得设置，不然点击不生效
                        textView.setMovementMethod(LinkMovementMethod.getInstance());
                        ssb.setSpan(new MyClick(), temp.length() - endText.length(), temp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        textView.setText(ssb);
                    } else {
                        textView.setText(originText);
                    }
                }
            }
        });
    }

    public interface Action1<T> {
        public void call(T... t);
    }
}
