package com.aesean.gankio.gank;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aesean.gankio.R;
import com.aesean.gankio.api.model.Result;
import com.aesean.gankio.base.BaseViewHolder;
import com.aesean.gankio.utils.WebViewUtil;

/**
 * Type0ViewHolder
 *
 * @author xl
 * @version V1.0
 * @since 01/03/2017
 */
public class Type0ViewHolder extends BaseViewHolder<Result> {

    Type0ViewHolder(ViewGroup parent, int layoutRes) {
        super(parent, layoutRes);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView();
            }
        });
    }

    public Type0ViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_gank);
    }

    protected void openWebView() {
        Result data = getData();
        if (data != null) {
            WebViewUtil.open(itemView.getContext(), data.getUrl());
        }
    }

    @Override
    public void setData(Result result) {
        super.setData(result);
        TextView contentView = (TextView) itemView.findViewById(R.id.content);
        contentView.setText(result.getDesc());
        TextView bottomStartView = (TextView) itemView.findViewById(R.id.bottom_start);
        bottomStartView.setText(result.getWho());
        TextView bottomEndView = (TextView) itemView.findViewById(R.id.bottom_end);
        bottomEndView.setText(result.getCreatedAtYmd());
    }
}
