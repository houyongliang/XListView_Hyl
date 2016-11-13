package com.hyl.mis.xlistview_hyl.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyl.mis.xlistview_hyl.R;
import com.hyl.mis.xlistview_hyl.adapter.MyAdapter;
import com.hyl.mis.xlistview_hyl.bean.Bean;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by mis on 2016/11/11.
 */

public class RVBaseAdapter extends RecyclerView.Adapter<RVBaseAdapter.MyViewHolder> {

    private Context context;
    private List<Bean.DataBean> list;
    private MyAdapter.MyItemClickListener mListener;
    private MyAdapter.MyItemLongClickListener mLongClickListener;
    public RVBaseAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setMyItemClickListener(MyAdapter.MyItemClickListener mListener){
        this.mListener=mListener;
    }
    public void setMyItemLongClickListener(MyAdapter.MyItemLongClickListener mLongClickListener){
        this.mLongClickListener=mLongClickListener;
    }


    @Override
    public RVBaseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);

        return new RVBaseAdapter.MyViewHolder(view,mListener,mLongClickListener);
    }

    @Override
    public void onBindViewHolder(RVBaseAdapter.MyViewHolder holder, int position) {
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
        private MyAdapter.MyItemClickListener mListener;
        private MyAdapter.MyItemLongClickListener mLongClickListener;
        public MyViewHolder(View itemView, MyAdapter.MyItemClickListener listener, MyAdapter.MyItemLongClickListener longClickListener) {
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
