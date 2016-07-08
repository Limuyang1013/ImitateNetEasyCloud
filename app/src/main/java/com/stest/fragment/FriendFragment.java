package com.stest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.InnerFragment.DynamicFragment;
import com.stest.InnerFragment.PartnerFragment;
import com.stest.neteasycloud.R;
import com.stest.view.NetEasyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class FriendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @ViewInject(R.id.friends_tab)
    private TabLayout friends_tab;
    @ViewInject(R.id.friends_viewPager)
    private ViewPager friends_viewPager;
    @ViewInject(R.id.refresh)
    private NetEasyRefreshLayout refreshLayout;
    private List<String> mTitleList = new ArrayList<>(3);
    private List<Fragment> fragments = new ArrayList<>(3);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView();
    }

    private void addView() {
        mTitleList.add("动态");
        mTitleList.add("附近");
        mTitleList.add("好友");

        fragments.add(new DynamicFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new PartnerFragment());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friend_fragment, container, false);
        ViewUtils.inject(this, v);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.themeColor);
        MyAdapter myAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        myAdapter.notifyDataSetChanged();
        friends_viewPager.setOffscreenPageLimit(3);
        friends_viewPager.setAdapter(myAdapter);
        friends_tab.setTabMode(TabLayout.MODE_FIXED);
        friends_tab.setupWithViewPager(friends_viewPager);
        friends_tab.setTabsFromPagerAdapter(myAdapter);
        return v;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2500);
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

    }
}
