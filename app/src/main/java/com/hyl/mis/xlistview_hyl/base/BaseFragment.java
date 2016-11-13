package com.hyl.mis.xlistview_hyl.base;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.hyl.mis.xlistview_hyl.R;
import com.hyl.mis.xlistview_hyl.adapter.MyAdapter;
import com.hyl.mis.xlistview_hyl.bean.Bean;
import com.hyl.mis.xlistview_hyl.utils.OkHttp;
import com.hyl.mis.xlistview_hyl.utils.XListView;
import com.hyl.mis.xlistview_hyl.view.PullBaseView;
import com.hyl.mis.xlistview_hyl.view.PullRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;




/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener{
    @BindView(R.id.tv_base)
    TextView tvBase;
    @BindView(R.id.rcyview_base)
    PullRecyclerView rcyBase;
    private String url="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";

    private List<String> listAll = new ArrayList<String>();
    private List<String> listAdd;
    private OkHttpClient okHttpClient;
    private int page = 0;
    private int pagesize = 10;
    private final int INIT = 0;
    private final int ONREFLASH = 1;
    private final int LOADMORE = 2;
    private int count = 40;
    private String tag="MainActivity";
    private List<Bean.DataBean> data;
    private MyAdapter myAdapter;

    public void setPagePagesize(int page, int pagesize) {
        this.page = page;
        this.pagesize = pagesize;
    }

    public int getPage() {
        return page;
    }

    public int getPagesize() {
        return pagesize;
    }


    public BaseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String title = bundle.getString("title", "");
        tvBase.setText(title);
    }

    public void initData() {
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e(tag, "requestSuccess: "+ "失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e(tag, "requestSuccess: 成功"+ result);
                Bean bean = new Gson().fromJson(result, Bean.class);
                data = bean.getData();
                initEvent();
            }
        });
    }

    private void initEvent() {
        LinearLayoutManager layoutManger=new LinearLayoutManager(getActivity());
        layoutManger.setOrientation(OrientationHelper.VERTICAL);
//        GridLayoutManager glm=new GridLayoutManager(this,4);
//        glm.setOrientation(OrientationHelper.VERTICAL);

        rcyBase.setLayoutManager(layoutManger);
        if(data!=null){
            myAdapter = new MyAdapter(getActivity(), data);
            rcyBase.setAdapter(myAdapter);
            myAdapter.setMyItemClickListener(new MyAdapter.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    Toast.makeText(getActivity(), "ITEM+postion"+postion, Toast.LENGTH_SHORT).show();
                }
            });
            myAdapter.setMyItemLongClickListener(new MyAdapter.MyItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int postion) {
                    Toast.makeText(getActivity(), "LONG+postion"+postion, Toast.LENGTH_SHORT).show();
                }
            });
        }
        rcyBase.setOnHeaderRefreshListener(this);//设置下拉监听
        rcyBase.setOnFooterRefreshListener(this);//设置上拉监听

    }

    public String getUrl(int page, int pagesize) {
        return "http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37" +
                " &page=" + page + "&pagesize=" + pagesize + "&sort=asc&time=1418745237";
    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mDatas.add("TEXT更多");
                myAdapter.notifyDataSetChanged();
                rcyBase.onFooterRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mDatas.add("TEXT更多");
                myAdapter.notifyDataSetChanged();
                rcyBase.onHeaderRefreshComplete();
            }
        }, 3000);
    }
}
