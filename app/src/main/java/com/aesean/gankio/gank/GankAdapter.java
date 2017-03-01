package com.aesean.gankio.gank;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.aesean.gankio.api.model.Result;
import com.aesean.gankio.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * GankAdapter
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class GankAdapter extends RecyclerView.Adapter<BaseViewHolder<Result>> {

    private SortedList<Result> mData;
    private List<Result> mOriginData;
    private String mFilter;

    public GankAdapter() {
        mData = new SortedList<>(Result.class, new SortedListAdapterCallback<Result>(this) {
            @Override
            public int compare(Result o1, Result o2) {
                return Result.compare(o1, o2);
            }

            @Override
            public boolean areContentsTheSame(Result oldItem, Result newItem) {
                return Result.areContentsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areItemsTheSame(Result item1, Result item2) {
                return Result.areItemsTheSame(item1, item2);
            }
        });
        mOriginData = new ArrayList<>();
    }

    public void setItem(List<Result> list) {
        mOriginData.clear();
        mOriginData.addAll(list);
        updateData();
    }

    public void addItem(List<Result> list) {
        mOriginData.addAll(list);
        updateData();
    }

    public void addItem(Result result) {
        mOriginData.add(result);
        updateData();
    }

    private void updateData() {
        if (TextUtils.isEmpty(mFilter)) {
            mData.addAll(mOriginData);
            return;
        }
        mData.clear();
        for (Result result : mOriginData) {
            if (result.getDesc().toUpperCase().contains(mFilter.toUpperCase())) {
                mData.add(result);
            }
        }
    }

    public void clearFilter() {
        if (mFilter != null) {
            mFilter = null;
            notifyDataSetChanged();
        }
    }

    public void setFilter(String s) {
        if (s.equals(mFilter)) {
            return;
        }
        mFilter = s;
        updateData();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Result result = mData.get(position);
        List<String> images = result.getImages();
        if (images != null && images.size() > 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder<Result> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new Type1ViewHolder(parent);
        }
        return new Type0ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<Result> holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
