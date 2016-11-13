package com.hyl.mis.xlistview_hyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyl.mis.xlistview_hyl.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mis on 2016/11/9.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public MyGridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            view = View.inflate(context, R.layout.mygridview_item, null);
            viewHolder=new ViewHolder(view);

            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tvItemMgv.setText(getItem(i));

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_mgv)
        TextView tvItemMgv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
