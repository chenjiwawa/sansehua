package com.zl.dappore.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by rzk on 2018/10/24.
 */
public class FileUtils {

    /**
     * 获取指定文件大小
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static long getFileSize(String path) {
        File file = new File(path);
        long size = 0;

        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
                fis.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("/"));
    }


    public static void saveBitmap(Bitmap bitmap, String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
