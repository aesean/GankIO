package com.aesean.gankio.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import com.aesean.gankio.R;
import com.aesean.gankio.api.GankRequest;
import com.aesean.gankio.api.HttpCallback;
import com.aesean.gankio.api.model.GankResult;
import com.aesean.gankio.base.ListFragment;

/**
 * GankFragment
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class GankFragment extends ListFragment implements SearchView.OnQueryTextListener {
    public static final String KEY_TYPE = "KEY_TYPE";

    private int mCurrentPage = 1;
    private int mPageCount = 20;

    private GankAdapter mAdapter;

    public String getType() {
        Bundle arguments = getArguments();
        return arguments.getString(KEY_TYPE);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        refresh();
    }

    private void refresh() {
        loadData(mPageCount, 1);
    }

    private void loadData(int pageCount, int currentPage) {
        GankRequest.get(getType(), pageCount, currentPage, new HttpCallback<GankResult>() {
            @Override
            public void success(GankResult gankResult) {
                mAdapter.addItem(gankResult.getResults());
            }

            @Override
            public void fail(String s, Exception e) {
                Toast.makeText(getActivity(), getString(R.string.load_data_fail) + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void finalDo() {
                super.finalDo();
                hideSwipeRefreshLayout();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh();
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if (lastVisibleItem == (totalItemCount - 3)) {
                    loadMore();
                }
            }
        });
    }

    private void loadMore() {
        loadData(mPageCount, ++mCurrentPage);
    }

    @Override
    protected void setAdapter(RecyclerView recyclerView) {
        mAdapter = new GankAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.setFilter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.setFilter(newText);
        return false;
    }

    public static GankFragment newInstance(String type) {
        GankFragment fragment = new GankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
