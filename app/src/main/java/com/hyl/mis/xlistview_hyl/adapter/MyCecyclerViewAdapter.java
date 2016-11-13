package com.hyl.mis.xlistview_hyl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyl.mis.xlistview_hyl.R;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by mis on 2016/11/9.
 */

public class MyCecyclerViewAdapter extends RecyclerView.Adapter<MyCecyclerViewAdapter.MyViewHolder> {
    private  Context context;
    private  List<String> list;
    public static final int ITEM_NUM = 3;
    public MyCecyclerViewAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        // 设置Item的宽度
//        ViewGroup.LayoutParams lp = view.getLayoutParams();
//        lp.width = getItemStdWidth();
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv_item_rcylview);

        }
    }
    // 获取标准宽度
//    public static int getItemStdWidth() {
//        DisplayMetrics displayMetrics = ChunyuApp.getAppContext().getResources().getDisplayMetrics();
//        return displayMetrics.widthPixels / ITEM_NUM;
//    }
}
