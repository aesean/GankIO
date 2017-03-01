package com.aesean.gankio.api.model;

import java.util.List;

/**
 * GankResult
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class GankResult extends BaseResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return this.results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
