package com.hyl.mis.xlistview_hyl;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.hyl.mis.xlistview_hyl.adapter.MyCecyclerViewAdapter;
import com.hyl.mis.xlistview_hyl.base.BaseFragment;
import com.hyl.mis.xlistview_hyl.fragment.ClassFragment;
import com.hyl.mis.xlistview_hyl.fragment.HomeFragment;
import com.hyl.mis.xlistview_hyl.fragment.ShoppingFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   String[] titles = new String[] {"美女", "汽车", "金融", "旅行", "北京", "段子", "科技", "奥运会", "八维" };
    @BindView(R.id.tabi)
    TabPageIndicator tabi;
    private int num = 3;

    //    @BindView(R.id.ll_add)
//    LinearLayout llAdd;
//    @BindView(R.id.hscrollview)
//    HorizontalScrollView hscrollview;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rv)
    RecyclerView rv;
    //    @BindView(R.id.activity_main)
//    LinearLayout activityMain;
    private List<String> list;
    private List<Fragment> listFg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData(num);
        initEvent();


    }

    private void initEvent() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        MyCecyclerViewAdapter adapter = new MyCecyclerViewAdapter(this, list);

        rv.setAdapter(adapter);

        vp.setOffscreenPageLimit(titles.length);
        vp.setAdapter(new MyFgPageAdapter(getSupportFragmentManager(), listFg,titles));
        vp.setCurrentItem(0);

        tabi.setViewPager(vp);
    }

    private void initData(int count) {
        //初始化头部数据
        list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            list.add("展示" + i + "条目");
        }

//        Button bt=null;
//       List<View> listView = new ArrayList<View>();
//        for (int i = 0; i <= list.size(); i++) {
//        if(i < list.size()){
//            bt=new Button(this);
//            bt.setText(list.get(i));
//            bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            listView.add(bt);
//            llAdd.addView(bt);
//        }else if(i == list.size()){
//            ImageButton ib=new ImageButton(this);
//            ib.setBackgroundResource(R.mipmap.add);
//            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.gravity = Gravity.CENTER;
//            params.leftMargin = 20;
//            ib.setLayoutParams(params);
//            listView.add(ib);
//            llAdd.addView(ib);
//        }
//        }
//初始化vp数据
        listFg = new ArrayList<Fragment>();
        for (int i = 0; i < titles.length; i++) {
            BaseFragment baseFragment = new BaseFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",titles[i]);
            baseFragment.setArguments(bundle);
            listFg.add(baseFragment);
        }


    }

    private void initView() {
        ButterKnife.bind(this);
    }

    class MyFgPageAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;
        private String[] title;
        public MyFgPageAdapter(FragmentManager fm, List<Fragment> list,String[] title) {
            super(fm);
            this.list = list;
            this.title=title;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
