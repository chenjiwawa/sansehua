package com.tricolorflower.heartbeat.common.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;


public class EditTextUtil {
    public final static String TAG = EditTextUtil.class.getSimpleName();

    /**
     * 字节长度控制，一个中文两个字节
     */
    public void setTextCNLimit(final Context context, final EditText edit, final String alertStr, final int maxLength, final TextView textLeftCount, final EditTextWatcher watcher) {
        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    String value = s.toString();
                    int nowLength = lengthENCN(value);
                    if (textLeftCount != null) {
                        L.e(TAG, "leftCount=" + (maxLength - nowLength) / 2);
                        textLeftCount.setTextColor(((maxLength - nowLength) / 2 <= 0) ? Color.RED : 0xFFB0B0B1);
                        textLeftCount.setText(String.valueOf("" + ((maxLength - nowLength) / 2)));
                    }
                    if (nowLength > maxLength) {
                        try {
                            String str = limitStringENCN(value, maxLength);
                            L.e(TAG,"new value=" + str);
                            if (edit != null) {
                                edit.setText(str);
                                edit.setSelection(str.length());
                            }
                            if (alertStr != null) {
                                QsToast.show(alertStr);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (watcher != null) {
                    watcher.afterChanged();
                }

            }
        });
    }


    /**
     * 字符长度控制， 一个中文算一个字符
     */
    public void setTextLimitOnlyCharEn(final Context context, final EditText edit, final String alertStr, final int maxLength, final TextView textLeftCount, final EditTextWatcher watcher, final boolean filiterEmoji) {
        if (edit == null) {
            return;
        }
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    String value = s.toString();
                    int nowLength = value.length();
                    if (textLeftCount != null) {
                        L.e(TAG,"leftCount=" + (maxLength - nowLength));
                        textLeftCount.setTextColor(((maxLength - nowLength) <= 0) ? Color.RED : 0xFFB0B0B1);
                        textLeftCount.setText("" + (maxLength - nowLength));
                    }
                    if (nowLength > maxLength) {
                        try {
                            String str = value.substring(0, maxLength);
                            L.e(TAG,"new value=" + str);
                            edit.setText(str);
                            edit.setSelection(str.length());
                            QsToast.show(alertStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (filiterEmoji) {
                        try {
                            if (CommonStringUtils.isContainsEmoji(value + "")) {
                                String str = CommonStringUtils.filterEmoji(value);
                                edit.setText(str);
                                edit.setSelection(str.length());
                                L.e(TAG,"has  emoji!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (watcher != null) {
                    watcher.afterChanged();
                }

            }
        });
    }

    /**
     * 获得字节数
     */
    private static int lengthENCN(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 取前n个字节的字符串
     */
    private static String limitStringENCN(String value, int maxLenght) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }

            if (valueLength > maxLenght) {
                return value.substring(0, i);
            }
        }
        return "";
    }

    public interface EditTextWatcher {
        void afterChanged();
    }
}
