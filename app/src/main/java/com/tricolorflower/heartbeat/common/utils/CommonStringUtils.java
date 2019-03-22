package com.tricolorflower.heartbeat.common.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.text.format.Formatter;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;



public class CommonStringUtils {
    public final static String TAG = EditTextUtil.class.getSimpleName();

    /**
     * 取前length个字节的字符串
     */
    public static String limitString(String value, int length) {
        if (TextUtils.isEmpty(value)) {
            return "";
        }
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

            if (valueLength > length) {
                return value.substring(0, i) + "...";
            }
        }
        return value;
    }


    /**
     * 过滤emoji 或者 其他非文字类型的字符
     */
    public static String filterEmoji(String source) {
        if (!isContainsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (notEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return "";//如果没有正常字符，返回“”
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                return source;
            } else {
                return buf.toString();
            }
        }
    }


    /**
     * 检测是否有emoji字符
     */
    public static boolean isContainsEmoji(String source) {

//        if (org.apache.commons.lang.StringUtils.isBlank(source)) {
//            return false;
//        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!notEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    private static boolean notEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000));
    }

    /**
     * 转换文件的大小，将文件的字节数转换为kb、mb、或gb
     */
    public static String formatterFileSize(long size) {
        return Formatter.formatFileSize(QsHelper.getInstance().getApplication(), size);
    }

    public static String formatterShortFileSize(long size) {
        return Formatter.formatShortFileSize(QsHelper.getInstance().getApplication(), size);
    }


    /**
     * {@link CommonStringUtils#getSizeByUrl(String, int, int, float, float)}
     *
     * @param photoUrl 图片url
     * @return 返回[宽，高]的int集合
     */
    public static int[] getSizeByUrl(String photoUrl) {
        return getSizeByUrl(photoUrl, 3000, 3000, 4f, 0.25f);
    }

    /**
     * 如果图片url path中有尺寸信息如:
     * aaa/bbbb/cccc_111_222  (如果111<maxWidth并且222<maxHeight 则返回[111,222])
     * aaa/bbbb/cccc_111_222.jpg  (如果111<maxWidth并且222<maxHeight 返回[111,222])
     * aaa/bbbb/cccc_111_222_333.xxx  (如果222<maxWidth并且333<maxHeight 则返回[222,333]  否则判断111<maxWidth并且222<maxHeight并返回)
     * 截取图片尺寸，并检查图片宽高比（minAspectRation<宽高比<maxAspectRatio），返回[宽，高]的int集合
     * 如果没有尺寸信息，或者尺寸宽高比例异常则返回null
     *
     * @param photoUrl        图片url
     * @param maxWidth        图片最大宽度
     * @param maxHeight       图片最大高度
     * @param maxAspectRatio  图片最大宽高比
     * @param minAspectRation 图片最小宽高比
     */
    public static int[] getSizeByUrl(String photoUrl, int maxWidth, int maxHeight, float maxAspectRatio, float minAspectRation) {
        if (TextUtils.isEmpty(photoUrl)) return null;
        String urlPath = Uri.parse(photoUrl).getPath();
        if (TextUtils.isEmpty(urlPath)) return null;
        String[] splitUrl = urlPath.split("/");
        if (splitUrl.length < 1 || !splitUrl[splitUrl.length - 1].contains("_")) return null;
        String[] nameArr = splitUrl[splitUrl.length - 1].split("_");
        int[] ints = new int[2];
        if (nameArr.length < 3) return null;
        int length = nameArr.length;
        String tempEndStr = nameArr[nameArr.length - 1];
        String endStr = tempEndStr.contains(".") ? tempEndStr.substring(0, tempEndStr.indexOf(".")).trim() : tempEndStr.trim();
        String centerStr = nameArr[length - 2].trim();
        String firstStr = nameArr[length - 3].trim();
        if (!TextUtils.isEmpty(endStr) && !TextUtils.isEmpty(centerStr) && CommonUtils.isNumber(endStr) && CommonUtils.isNumber(centerStr)) {
            long centerNumber = Long.parseLong(centerStr);
            long endNumber = Long.parseLong(endStr);
            if (centerNumber > 0 && endNumber > 0 && centerNumber < maxWidth && endNumber < maxHeight && (centerNumber * 100 / endNumber > 100 * minAspectRation) && (centerNumber * 100 / endNumber < 100 * maxAspectRatio)) {
                ints[0] = (int) centerNumber;
                ints[1] = (int) endNumber;
                L.i(TAG,"getSizeByUrl  2&3 ints:(" + ints[0] + "," + ints[1] + ")  path=" + urlPath);
                return ints;
            } else if (centerNumber > 0 && centerNumber < maxHeight) {
                if (!TextUtils.isEmpty(firstStr) && CommonUtils.isNumber(firstStr)) {
                    long firstNumber = Long.parseLong(firstStr);
                    if (firstNumber > 0 && firstNumber < maxWidth && (firstNumber * 100 / centerNumber > 100 * minAspectRation) && (firstNumber * 100 / centerNumber < 100 * maxAspectRatio)) {
                        ints[0] = (int) firstNumber;
                        ints[1] = (int) centerNumber;
                        L.i(TAG,"getSizeByUrl  1&2 ints:(" + ints[0] + "," + ints[1] + ")  path=" + urlPath);
                        return ints;
                    }
                }
            }
        }
        L.i(TAG,"getSizeByUrl  return  null !!  path=" + urlPath);
        return null;
    }
}
