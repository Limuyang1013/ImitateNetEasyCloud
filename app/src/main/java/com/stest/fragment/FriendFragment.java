package com.stest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.InnerFragment.AnchorFragment;
import com.stest.InnerFragment.ListFragment;
import com.stest.InnerFragment.RankingFragment;
import com.stest.InnerFragment.RecommendFragment;
import com.stest.neteasycloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class FriendFragment extends Fragment {
    @ViewInject(R.id.friends_tab)
    private TabLayout friends_tab;
    @ViewInject(R.id.friends_viewPager)
    private ViewPager friends_viewPager;
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

        fragments.add(new RecommendFragment());
        fragments.add(new ListFragment());
        fragments.add(new AnchorFragment());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friend_fragment, container, false);
        ViewUtils.inject(this, v);
        MyAdapter myAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        myAdapter.notifyDataSetChanged();
        friends_viewPager.setAdapter(myAdapter);
        friends_tab.setTabsFromPagerAdapter(myAdapter);
        friends_tab.setTabMode(TabLayout.MODE_FIXED);
        friends_tab.setupWithViewPager(friends_viewPager);
        return v;
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

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
