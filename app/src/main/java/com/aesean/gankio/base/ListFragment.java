package com.aesean.gankio.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aesean.gankio.R;


/**
 * ListFragment
 *
 * @author xl
 * @version V1.0
 * @since 24/02/2017
 */
public abstract class ListFragment extends AppBaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        initSwipeRefreshLayout(mSwipeRefreshLayout);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initRecyclerView(recyclerView);
        setAdapter(recyclerView);
        showSwipeRefreshLayout();
    }

    protected abstract void setAdapter(RecyclerView recyclerView);

    protected void showSwipeRefreshLayout() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    protected void hideSwipeRefreshLayout() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipe_refresh_color_scheme));
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity()
//                , DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onRefresh() {
        showSwipeRefreshLayout();
    }
}
