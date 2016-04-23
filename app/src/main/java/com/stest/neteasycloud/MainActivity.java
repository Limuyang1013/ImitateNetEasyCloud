package com.stest.neteasycloud;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.AnchorAdapter;
import com.stest.adapter.MainPopAdapter;
import com.stest.adapter.MyrankAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.navigation_view)
    private NavigationView mNavigationView;
    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.drawerIcon)
    private ImageView drawerIcon;
    @ViewInject(R.id.drawer)
    private DrawerLayout mDrawerLayout;
    @ViewInject(R.id.bar_disco)
    private ImageView bar_disco;
    @ViewInject(R.id.bar_music)
    private ImageView bar_music;
    @ViewInject(R.id.bar_friends)
    private ImageView bar_friends;
    @ViewInject(R.id.view_pager)
    private ViewPager view_pager;
    @ViewInject(R.id.tabs)
    private TabLayout tabLayout;
    @ViewInject(R.id.play_btn)
    private ImageView play_btn;
    @ViewInject(R.id.bottom_music_more)
    private LinearLayout bottom_music_more;
    private ImageButton daily_btn;
    private TextView daily_text;
    private boolean isChanged = true;
    private ImageView anim_image;
    private boolean isOpen;
    //改变Daily颜色
    private boolean isClick = false;
    //存放每个Pager的View
    private List<View> mViewList;
    //存放Title
    private List<String> mTitleList;
    private LayoutInflater mLayoutInflater;
    private View recommend;
    private View list;
    private View anchor;
    private View ranking;
    //底部弹出视图
    private View popView;
    private PopupWindow popupWindow;
    private Date date;
    private SimpleDateFormat dateFm;
    private AnimationDrawable animationDrawable;
    //排行榜布局
    private ListView listView;
    //弹出视图ListView
    private ListView popListView;
    //精彩节目推荐
    private ListView anchorListView;
    private int[] imageIds = new int[]{R.drawable.ranklist_first, R.drawable.ranklist_second, R.drawable.ranklist_third,
            R.drawable.ranklist_fourth, R.drawable.ranklist_five, R.drawable.ranklist_six};
    private List<Map<String, Object>> mInfos = new ArrayList<>();
    private List<Map<String, Object>> popInfos = new ArrayList<>();
    private List<Map<String, Object>> anchorInfos = new ArrayList<>();
    private String[] Songs=new String[]{"知足-","倔强-","我不愿让你一个人-","干杯-","宠上天-","突然好想你-","星空-","如烟-","第二人生-"};
    private String[] teams = new String[]{"五月天", "苏打绿", "信乐团", "飞儿乐队", "凤凰传奇", "纵贯线",};
    private String[] places = new String[]{"上海演唱会", "香港红馆演唱会", "台湾火力全开演唱会", "北京鸟巢演唱会"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);//视图注入
        mLayoutInflater = LayoutInflater.from(MainActivity.this);
        recommend = mLayoutInflater.inflate(R.layout.recommend, null);
        list = mLayoutInflater.inflate(R.layout.list, null);
        anchor = mLayoutInflater.inflate(R.layout.anchor, null);
        ranking = mLayoutInflater.inflate(R.layout.ranking, null);
        popView = mLayoutInflater.inflate(R.layout.main_pop, null);
        initWidgets();
        addView();
        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        //给ViewPager设置适配器
        view_pager.setAdapter(mAdapter);
        //将ViewPager和TabLayout连接起来
        tabLayout.setupWithViewPager(view_pager);
        tabLayout.setTabsFromPagerAdapter(mAdapter);
        //设置点击事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                view_pager.setCurrentItem(tab.getPosition());
                if (view_pager.getCurrentItem() == 1) {
                    anim_image.setImageResource(R.drawable.loadanimation);
                    animationDrawable = (AnimationDrawable) anim_image.getDrawable();
                    animationDrawable.start();
                } else {
                    anim_image.setImageResource(R.drawable.loadanimation);
                    animationDrawable = (AnimationDrawable) anim_image.getDrawable();
                    animationDrawable.stop();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isOpen = true;

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isOpen = false;

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    //LEFT和RIGHT指的是现存DrawerLayout的方向
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    isOpen = true;
                }

            }
        });
    }

    private void addView() {
        mViewList.add(recommend);
        mViewList.add(list);
        mViewList.add(anchor);
        mViewList.add(ranking);

        mTitleList.add("个性推荐");
        mTitleList.add("歌单");
        mTitleList.add("主播电台");
        mTitleList.add("排行榜");
        //设置标签的模式,默认系统模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //添加TabLayout上的文本元素
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(3)));
    }

    /**
     * 初始化控件
     */
    private void initWidgets() {
        view_pager.setOffscreenPageLimit(2);
        daily_text = (TextView) recommend.findViewById(R.id.daily_text);
        daily_btn = (ImageButton) recommend.findViewById(R.id.daily_btn);
        anim_image = (ImageView) list.findViewById(R.id.anim_image);
        listView = (ListView) ranking.findViewById(R.id.listView);
        popListView = (ListView) popView.findViewById(R.id.main_pop_listview);
        anchorListView = (ListView) anchor.findViewById(R.id.anchor_list_view);
        daily_btn.setOnClickListener(this);
        bar_disco.setOnClickListener(this);
        bar_friends.setOnClickListener(this);
        bar_music.setOnClickListener(this);
        play_btn.setOnClickListener(this);
        bottom_music_more.setOnClickListener(this);
        bar_disco.setSelected(true);

        //自适配长、框设置
        popupWindow = new PopupWindow(popView, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        popupWindow.setOutsideTouchable(true);
        //刷新状态
        popupWindow.update();
        popupWindow.setTouchable(true);
        //这样点击返回键也能消失
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);

        setSupportActionBar(toolbar);
        //去除状态栏文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mNavigationView.setItemIconTintList(null);
        mViewList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        getDate();
        daily_text.setText(getDate());

        //加载排行榜ListView布局
        for (int i = 0; i < 12; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("imageView", imageIds[new Random().nextInt(6)]);
            item.put("rank_first_txt", "1.知足");
            item.put("rank_second_txt", "2.温柔");
            item.put("rank_third_txt", "3.干杯");
            mInfos.add(item);
        }
        listView.setAdapter(new MyrankAdapter(this, mInfos));
        //加载PopWindow布局
        for (int i = 0; i < 15; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("imageView", R.mipmap.list_icn_delete);
            item.put("txt_author", "五月天");
            item.put("txt_name", Songs[new Random().nextInt(9)]);
            popInfos.add(item);
        }
        popListView.setAdapter(new MainPopAdapter(this, popInfos));
        //加载主播电台布局
        for (int i = 1; i < 15; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("imageView",R.mipmap.list_fourth);
            item.put("txt_team", teams[new Random().nextInt(5)]);
            item.put("txt_place", places[new Random().nextInt(4)]);
            anchorInfos.add(item);
        }
        anchorListView.setAdapter(new AnchorAdapter(this, anchorInfos));
    }

    //更改时间
    private String getDate() {
        date = new Date();
        dateFm = new SimpleDateFormat("dd");
        return dateFm.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_disco:
                if (bar_music.isSelected()) {
                    bar_disco.setSelected(true);
                    bar_music.setSelected(false);
                } else if (bar_friends.isSelected()) {
                    bar_disco.setSelected(true);
                    bar_friends.setSelected(false);
                }
                break;
            case R.id.bar_friends:
                if (bar_disco.isSelected()) {
                    bar_friends.setSelected(true);
                    bar_disco.setSelected(false);
                } else if (bar_music.isSelected()) {
                    bar_friends.setSelected(true);
                    bar_music.setSelected(false);
                }
                break;
            case R.id.bar_music:
                if (bar_disco.isSelected()) {
                    bar_disco.setSelected(false);
                    bar_music.setSelected(true);
                } else if (bar_friends.isSelected()) {
                    bar_friends.setSelected(false);
                    bar_music.setSelected(true);
                }
                break;
            case R.id.play_btn:
                if (isChanged) {
                    play_btn.setBackground(null);
                    play_btn.setImageDrawable(getResources().getDrawable(R.drawable.pause_btn));
                } else {
                    play_btn.setImageDrawable(getResources().getDrawable(R.drawable.play_btn));
                    play_btn.setBackground(getResources().getDrawable(R.drawable.list_bg));
                }
                isChanged = !isChanged;
                break;
            case R.id.daily_btn:
                if (!isClick) {
                    daily_btn.setBackground(getResources().getDrawable(R.drawable.dailly_prs));
                    daily_text.setTextColor(Color.parseColor("#ffffff"));
                    isClick = true;
                } else if (isClick) {
                    daily_btn.setBackground(getResources().getDrawable(R.drawable.dailly_normal));
                    daily_text.setTextColor(Color.parseColor("#ffce3d3a"));
                    isClick = false;
                }
                break;
            case R.id.bottom_music_more:
                if (!popupWindow.isShowing()) {
                    popupWindow.showAsDropDown(bar_music, 0, 450);
                }
                break;
            default:
                break;
        }

    }


    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

    }
}
