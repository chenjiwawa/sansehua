package com.tricolorflower.heartbeat.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.qsmaxmin.qsbase.common.utils.QsHelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class ApplicationUtil {

    /**
     * 获取客户端名称
     */
    public static String getAppClientVersion() {
        String strVersion = "";
        try {
            strVersion = QsHelper.getInstance().getApplication().getPackageManager().getPackageInfo(QsHelper.getInstance().getApplication().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strVersion;
    }

    /**
     * 获取客户端code
     */
    public static int getAppClientVersionCode() {
        int versionCode = 0;
        try {
            versionCode = QsHelper.getInstance().getApplication().getPackageManager().getPackageInfo(QsHelper.getInstance().getApplication().getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 重新启动App
     */
    public static void restartApp() {
        QsHelper.getInstance().getScreenHelper().popAllActivityExceptMain(null);
        Intent intent = QsHelper.getInstance().getApplication().getPackageManager().getLaunchIntentForPackage(QsHelper.getInstance().getApplication().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        QsHelper.getInstance().getApplication().startActivity(intent);
    }

    /**
     * 判断某应用是否安装
     */
    public static boolean checkInstall(String packageName) {
        try {
            PackageInfo packageInfo = QsHelper.getInstance().getApplication().getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean openApplication(String packageName) {
        try {
            Intent intent = QsHelper.getInstance().getApplication().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                QsHelper.getInstance().getApplication().startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    //这个是获取SHA1的方法
    public static String getCertificateSHA1Fingerprint(Context context) {
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取当前要获取SHA1值的包名，也可以用其他的包名，但需要注意，
        //在用其他包名的前提是，此方法传递的参数Context应该是对应包的上下文。
        String packageName = context.getPackageName();
        //返回包括在包中的签名信息
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            //获得包的所有内容信息类
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //签名信息
        Signature[] signatures = new Signature[0];
        if (packageInfo != null) {
            signatures = packageInfo.signatures;
        }
        byte[] cert = signatures[0].toByteArray();
        //将签名转换为字节数组流
        InputStream input = new ByteArrayInputStream(cert);
        //证书工厂类，这个类实现了出厂合格证算法的功能
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        //X509证书，X.509是一种非常通用的证书格式
        X509Certificate c = null;
        try {
            if (cf != null) {
                c = (X509Certificate) cf.generateCertificate(input);
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        String hexString = null;
        try {
            //加密算法的类，这里的参数可以使MD4,MD5等加密算法
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //获得公钥
            byte[] publicKey = md.digest(c.getEncoded());
            //字节到十六进制的格式转换
            hexString = byte2HexFormatted(publicKey);
        } catch (NoSuchAlgorithmException | CertificateEncodingException e1) {
            e1.printStackTrace();
        }
        return hexString;
    }

    //这里是将获取到得编码进行16进制转换
    private static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }
}
