package com.tricolorflower.heartbeat.common.utils;

import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.model.UserConfig;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {
    public final static String TAG = OkhttpUtil.class.getSimpleName();


    private static OkHttpClient client = new OkHttpClient();

    public static void del(String id) {
        //post请求
        FormBody formBody = new FormBody.Builder().build();

        Request request = new Request.Builder().url("http://dappore.store/api/App/" + id + "/stars").
                addHeader("Content-Type", "application/json").addHeader("Accept", "application/json").addHeader("Auth-Token", UserConfig.getInstance().getAuthToken()).delete().build();

        L.i(TAG, " request " + request.toString());
        L.i(TAG, " request " + request.headers().toString());
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            public void onResponse(Call call, Response response) throws IOException {
                L.i(TAG, " call " + call);
                L.i(TAG, " response " + response);
                L.i(TAG, " response  headers " + response.headers());
                L.i(TAG, " response  headers " + response.body());
                if (response.code() >= 200 && response.code() < 300) {
                    System.out.println(response.body().string());
                }
            }
        });
    }


}
