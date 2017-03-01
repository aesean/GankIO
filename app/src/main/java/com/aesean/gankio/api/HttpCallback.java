package com.aesean.gankio.api;

/**
 * HttpCallback
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public abstract class HttpCallback<T> {
    public abstract void success(T t);

    public void fail(String s, Exception e) {

    }

    public void finalDo() {

    }
}
