package com.aesean.gankio.api;

import com.aesean.gankio.api.model.GankResult;
import com.aesean.gankio.utils.JsonUtil;

/**
 * GankRequest
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class GankRequest {
    private static final String API = "http://gank.io/api/data/";

    public static final String TYPE_ANDROID = "Android";
    public static final String TYPE_IOS = "iOS";
    public static final String TYPE_FRONT = "前端";

    public static void get(String type, int count, int page, final HttpCallback<GankResult> callback) {
        HttpRequest<String> request = HttpRequestFactory.stringRequest();
        request.get(API + type + "/" + count + "/" + page, new HttpCallback<String>() {
            @Override
            public void success(String s) {
                GankResult result;
                try {
                    result = JsonUtil.fromJson(s, GankResult.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.fail(e.getMessage(), e);
                    return;
                }
                callback.success(result);
            }

            @Override
            public void fail(String s, Exception e) {
                callback.fail(s, e);
            }

            @Override
            public void finalDo() {
                callback.finalDo();
            }

        });
    }
}
