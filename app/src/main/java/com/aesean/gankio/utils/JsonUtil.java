package com.aesean.gankio.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JsonUtil
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class JsonUtil {
    private static final Gson sGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
            .create();

    /**
     * 所有的Json反序列化都必须使用当前方法。
     *
     * @param json     json
     * @param classOfT 目标类类型
     * @param <T>      目标范型
     * @return 处理结果
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return sGson.fromJson(json, classOfT);
    }

    /**
     * 所有的Json序列化都必须使用当前方法。
     *
     * @param target 目标对象
     * @return json String
     */
    public static String toJson(Object target) {
        return sGson.toJson(target);
    }
}
