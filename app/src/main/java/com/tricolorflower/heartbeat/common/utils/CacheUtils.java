package com.tricolorflower.heartbeat.common.utils;


import android.content.Context;

import com.google.gson.Gson;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/10  下午6:14
 * @Description 缓存处理类 将Model对象序列化到本地的/data/data/<package name>/fileName文件中 或者从file读取内容，转换成Model对象
 */
public class CacheUtils<T extends BaseModel> {
    public final static String TAG = CacheUtils.class.getSimpleName();

    public void saveObject2File(T model, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(model);
        try {
            FileOutputStream fs = QsHelper.getInstance().getApplication().openFileOutput(fileName, Context.MODE_PRIVATE);
            fs.write(json.getBytes());
            fs.close();
        } catch (Exception e) {
            L.e(TAG,e.getMessage());
        }
    }

    public T getObjectFromFile(String fileName, Class<T> tClass) {
        T model = null;
        String json;
        try {
            FileInputStream fileInputStream = QsHelper.getInstance().getApplication().openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            json = bufferedReader.readLine();
            bufferedReader.close();
            Gson gson = new Gson();
            model = gson.fromJson(json, tClass);
        } catch (Exception e) {
            L.e(TAG,e.getMessage());
        }
        return model;
    }
}
