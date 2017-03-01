package com.aesean.gankio.api.model;

/**
 * BaseResponse
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public abstract class BaseResponse {
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
