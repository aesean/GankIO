package com.aesean.gankio.base;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * BaseViewHolder
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class BaseViewHolder<D> extends RecyclerView.ViewHolder {
    private D mData;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public D getData() {
        return mData;
    }

    @CallSuper
    public void setData(D d) {
        mData = d;
    }
}
