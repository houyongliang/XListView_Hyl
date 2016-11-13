package com.hyl.mis.xlistview_hyl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hyl.mis.xlistview_hyl.R;
import com.hyl.mis.xlistview_hyl.bean.Bean;
import com.lidroid.xutils.BitmapUtils;


import java.net.InterfaceAddress;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static android.webkit.WebSettings.PluginState.ON;

/**
 * Created by mis on 2016/11/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    private Context context;
    private List<Bean.DataBean> list;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;
    public MyAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setMyItemClickListener(MyItemClickListener mListener){
        this.mListener=mListener;
    }
    public void setMyItemLongClickListener(MyItemLongClickListener mLongClickListener){
        this.mLongClickListener=mLongClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);

        return new MyViewHolder(view,mListener,mLongClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bean.DataBean data = list.get(position);
        holder.tv.setText(data.getGoods_name());
        BitmapUtils bitmapUtils = new BitmapUtils(context);
    // 加载网络图片
        bitmapUtils.display(holder.iv, data.getGoods_img());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView tv;
        ImageView iv;
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        public MyViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_listview_item);
            iv = (ImageView) itemView.findViewById(R.id.iv_listview_item);
            this.mListener = listener;
            this.mLongClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onItemClick(view,getPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(view, getPosition());
            }
            return true;
        }
    }
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
    public interface MyItemLongClickListener {
        public void onItemLongClick(View view,int postion);
    }
}
