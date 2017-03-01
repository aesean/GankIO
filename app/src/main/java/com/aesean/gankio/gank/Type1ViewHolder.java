package com.aesean.gankio.gank;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aesean.gankio.R;
import com.aesean.gankio.api.model.Result;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Type1ViewHolder
 *
 * @author xl
 * @version V1.0
 * @since 01/03/2017
 */
public class Type1ViewHolder extends Type0ViewHolder {

    public Type1ViewHolder(ViewGroup parent) {
        super(parent, R.layout.item2_gank);
    }

    @Override
    public void setData(Result result) {
        super.setData(result);
        ViewPager viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
        List<View> list = new ArrayList<>();
        for (String url : result.getImages()) {
            View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.image_view, viewPager, false);
            SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.image_view);
            imageView.setImageURI(url);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWebView();
                }
            });
            list.add(view);
        }
        PictureViewAdapter viewAdapter = new PictureViewAdapter(list);
        viewPager.setAdapter(viewAdapter);
        itemView.findViewById(R.id.bottom_container_2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWebView();
                    }
                });
    }

    private class PictureViewAdapter extends PagerAdapter {
        List<View> viewList;

        public PictureViewAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = viewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}