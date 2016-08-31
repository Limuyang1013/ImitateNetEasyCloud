package com.stest.neteasycloud;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.stest.InnerFragment.AlbumsFragment;
import com.stest.InnerFragment.FoldersFragment;
import com.stest.InnerFragment.SingersFragment;
import com.stest.InnerFragment.SinglesFragment;

import java.util.ArrayList;
import java.util.List;

public class MusicInfoActivity extends AppCompatActivity {
    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.infos_tab)
    private TabLayout infos_tab;
    @ViewInject(R.id.infos_viewPager)
    private ViewPager mPager;
    private ActionBar actionBar;
    private List<String> mTitleList = new ArrayList<>(4);
    private List<Fragment> fragments = new ArrayList<>(4);
    private static final String TAG = MusicInfoActivity.class.getSimpleName();
    private SinglesFragment mSinglesFragment;
    private SingersFragment mSingersFragment;
    private AlbumsFragment mAlbumsFragment;
    private FoldersFragment mFoldersFragment;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_info);
        ViewUtils.inject(this);
        //初始化控件
        initWidgets();
        applyKitKatTranslucency();
    }

    private void initWidgets() {
        toolbar.setTitle(R.string.local_music);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addView();
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.notifyDataSetChanged();
        mPager.setAdapter(myAdapter);
        mPager.setOffscreenPageLimit(2);
        infos_tab.setTabMode(TabLayout.MODE_FIXED);
        infos_tab.setupWithViewPager(mPager);

    }

    //滑动布局
    private void addView() {
        mTitleList.add("单曲");
        mTitleList.add("歌手");
        mTitleList.add("专辑");
        mTitleList.add("文件夹");

        if (mSinglesFragment == null) {
            mSinglesFragment = new SinglesFragment();
            fragments.add(mSinglesFragment);
        }
        if (mSingersFragment == null) {
            mSingersFragment = new SingersFragment();
            fragments.add(mSingersFragment);
        }
        if (mAlbumsFragment == null) {
            mAlbumsFragment = new AlbumsFragment();
            fragments.add(mAlbumsFragment);
        }
        if (mFoldersFragment == null) {
            mFoldersFragment = new FoldersFragment();
            fragments.add(mFoldersFragment);
        }

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MusicInfoActivity.class);
        context.startActivity(intent);
    }


    private void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.themeColor);//通知栏所需颜色
        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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
