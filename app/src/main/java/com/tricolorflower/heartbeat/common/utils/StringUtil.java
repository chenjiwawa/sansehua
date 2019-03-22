package com.tricolorflower.heartbeat.common.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;

import com.qsmaxmin.qsbase.common.utils.QsHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 *
 * @Author zhuanggy
 * @Date:2014-1-7
 * @Copyright (c) 2014, 方正电子 All Rights Reserved.
 */

public class StringUtil {

    /**
     * 是否为空
     */
    public static boolean isNull(String str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * 转换文件的大小，将文件的字节数转换为kb、mb、或gb
     *
     * @return 保留1位小数
     */
    public static String formatterFileSize(long size) {
        return Formatter.formatFileSize(QsHelper.getInstance().getApplication(), size);
    }

    /**
     * 过滤掉空行
     */
    public static String deleteEmptyLine(String str) {
        String dest = str;
        if (!TextUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 过滤出数字
     */
    public static String getNumbersFromString(String str) {
        String finalStr = "";
        if (!TextUtils.isEmpty(str)) {
            String sourceStr = str.trim();
            for (int i = 0; i < sourceStr.length(); i++) {
                if (sourceStr.charAt(i) >= 48 && sourceStr.charAt(i) <= 57) {
                    finalStr += sourceStr.charAt(i);
                }
            }
        }
        return finalStr;
    }

    /**
     * 获得网址最后一个斜杠后的内容
     */
    public static String getUrlLastParam(String url) {
        String result = "";
        if (!TextUtils.isEmpty(url) && url.length() > 0) {
            String[] strArray = url.split("/");
            result = strArray[strArray.length - 1];
        }
        return result;
    }


    /**
     * 根据自定义的分隔符分割字符串
     */
    public static String[] getSplitRow(String src, String split) {
        String[] result = {};
        if (!TextUtils.isEmpty(src)) {
            result = src.split(split);
        }
        return result;
    }

    public static boolean isPhoneNumber(String number) {
//      String rgx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        String rgx = "^((1[0-9][0-9]))\\d{8}$";
        return isCorrect(rgx, number);
    }

    private static boolean isCorrect(String rgx, String res) {
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(res);
        return m.matches();
    }


    /**
     * 处理ttf下载的zip包名， 把.zip之外的.转成_
     */
    public static String changeFileName(String zipname) {
        if (TextUtils.isEmpty(zipname) || !((zipname.toLowerCase()).endsWith(".zip"))) {
            return zipname;
        }
        return zipname.toLowerCase().substring(0, zipname.lastIndexOf(".")).replaceAll("\\.", "_") + ".zip";
    }

    public static void CopyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        clip.setText(text); // 复制
    }
}
