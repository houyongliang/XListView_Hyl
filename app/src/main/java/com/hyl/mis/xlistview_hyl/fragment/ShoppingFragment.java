package com.hyl.mis.xlistview_hyl.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hyl.mis.xlistview_hyl.R;
import com.hyl.mis.xlistview_hyl.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment {


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

}
