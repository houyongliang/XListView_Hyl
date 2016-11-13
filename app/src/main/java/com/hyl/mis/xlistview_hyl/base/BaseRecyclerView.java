package com.hyl.mis.xlistview_hyl.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.hyl.mis.xlistview_hyl.adapter.MyAdapter;

/**
 * Created by mis on 2016/11/11.
 */

public   class BaseRecyclerView extends RecyclerView {
    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private MyAdapter.MyItemClickListener mListener;
    private MyAdapter.MyItemLongClickListener mLongClickListener;
}
