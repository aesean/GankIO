package com.aesean.gankio.api;

import java.util.AbstractMap;

/**
 * HttpRequest
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
abstract class HttpRequest<T> {
    public abstract void get(String url, HttpCallback<T> callback);

    public abstract void post(String url, AbstractMap<String, String> params, HttpCallback<T> callback);
}
