package com.aesean.gankio.api;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HttpRequestFactory
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
class HttpRequestFactory {
    private static final String TAG = "HttpRequest";

    private static void runOnMainThread(Runnable runnable) {
        InstanceHolder.sHandler.post(runnable);
    }

    /**
     * Http请求可能发生在任意线程，这里必须处理线程安全问题。
     */
    private static class InstanceHolder {
        static final OkHttpClient sOkHttpClient = new OkHttpClient();
        static final Handler sHandler = new Handler(Looper.getMainLooper());
    }

    private static OkHttpClient getInstance() {
        return InstanceHolder.sOkHttpClient;
    }

    /**
     * 原则上所有的http请求都必须走当前方法。可以在这里做http请求的一些全局处理。
     */
    public static HttpRequest<String> stringRequest() {
        return new HttpRequest<String>() {
            @Override
            public void get(String url, final HttpCallback<String> callback) {
                getInstance().newCall(new Request.Builder()
                        .get()
                        .url(url)
                        .build())
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(final Call call, final IOException e) {
                                runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e(TAG, "onFailure: ", e);
                                        callback.fail(e.getMessage(), e);
                                        callback.finalDo();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                final String result = response.body().string();
                                runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.success(result);
                                        callback.finalDo();
                                    }
                                });
                            }
                        });
            }

            @Override
            public void post(String url, AbstractMap<String, String> params, HttpCallback<String> callback) {
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addEncoded(entry.getKey(), entry.getValue());
                }
                getInstance().newCall(new Request.Builder()
                        .url(url)
                        .post(builder.build())
                        .build());
            }
        };
    }
}
