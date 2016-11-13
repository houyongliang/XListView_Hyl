package com.hyl.mis.xlistview_hyl.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyl.mis.xlistview_hyl.R;
import com.hyl.mis.xlistview_hyl.base.BaseFragment;
import com.hyl.mis.xlistview_hyl.utils.OkHttp;
import com.hyl.mis.xlistview_hyl.utils.XListView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

import static android.os.Build.VERSION_CODES.N;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment {



    public ClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);

//        initData();
        return view;
    }
    public  void initData() {
        OkHttp.getAsync(getUrl(getPage(), getPagesize()), new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

            }
        });
    }
}
