package com.zl.dappore.common.utils;


import com.qsmaxmin.qsbase.common.log.L;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 写字先生、字工场等app，用户密码的加密处理
 * MD5 算法
 */
public class MD5Util {
    public final static String TAG = MD5Util.class.getSimpleName();

    private static MessageDigest messagedigest = null;
    // 全局数组
    private final static String[]      strDigits     = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            L.e(TAG,"初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    public MD5Util() {

    }

    public static String getMd5Code(String strObj) {
        if (!checkCanDo(strObj)) {
            return null;
        }
        return byteToString(messagedigest.digest(strObj.getBytes()));
    }

    public static String getMd5Code(File file) {
        if (checkCanDo(file)) {
            synchronized (MD5Util.class) {
                if (checkCanDo(file)) {
                    InputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                        byte[] buffer = new byte[1024 * 4];
                        int numRead;
                        while ((numRead = fis.read(buffer)) > 0) {
                            messagedigest.update(buffer, 0, numRead);
                        }
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fis != null) {
                                fis.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    BigInteger bigInt = new BigInteger(1, messagedigest.digest());
                    return bigInt.toString(16);
                }
            }
        }
        return null;
    }

    /**
     * 获取文件夹中文件的MD5值
     *
     * @param listChild true递归子目录中的文件
     */
    public static Map<String, String> getDirMD5(File file, boolean listChild) {
        if (!file.isDirectory()) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String md5;
        File files[] = file.listFiles();
        for (File f : files) {
            if (f.isDirectory() && listChild) {
                Map<String, String> childMaps = getDirMD5(f, true);
                if (childMaps != null) map.putAll(childMaps);
            } else {
                md5 = getMd5Code(f);
                if (md5 != null) {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }

    private static boolean checkCanDo(Object object) {
        return object != null && messagedigest != null;
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (byte aBByte : bByte) {
            sBuffer.append(byteToArrayString(aBByte));
        }
        return sBuffer.toString();
    }
}