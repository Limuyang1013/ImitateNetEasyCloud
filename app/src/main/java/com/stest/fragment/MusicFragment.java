package com.stest.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.MusicDetailAdapter;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.MusicInfoActivity;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;
import com.stest.view.NetEasyRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private View v;
    private static MusicFragment musicFragment;
    private MusicDetailAdapter mMusicDetailAdapter;
    //数据
    private List<String> data;
    private List<MusicInfoDetail> musicList;
    @ViewInject(R.id.lv)
    private DividerListView lv;
    @ViewInject(R.id.detail_number)
    private TextView detail_number;
    @ViewInject(R.id.refresh)
    private NetEasyRefreshLayout refreshLayout;
    @ViewInject(R.id.detail_creat)
    private RelativeLayout creat_layout;
    @ViewInject(R.id.detail_collect)
    private RelativeLayout collect_layout;
    @ViewInject(R.id.creat_expand_img)
    private ImageView creat_expand_img;
    @ViewInject(R.id.collect_expand_img)
    private ImageView collect_expand_img;
    private boolean isCreatRotate = true;
    private boolean isCollectRotate = true;
    private static final String TAG = "MusicFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            ViewUtils.inject(this, v);
            return v;
        }
        v = inflater.inflate(R.layout.music_fragment, container, false);
        ViewUtils.inject(this, v);
        initWidgets();
        return v;
    }

    //初始化
    private void initWidgets() {
        refreshLayout.setOnRefreshListener(this);
        creat_layout.setOnClickListener(this);
        collect_layout.setOnClickListener(this);
        refreshLayout.setColorSchemeResources(R.color.themeColor);
        data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_icn_data)));
        mMusicDetailAdapter = new MusicDetailAdapter(getActivity(), data, R.layout.music_detail_item);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //本地音乐
                    case 0:
                        MusicInfoActivity.start(getContext());
                        break;
                    //最近播放
                    case 1:
                        break;
                    //下载管理
                    case 2:
                        break;
                    //我的歌手
                    case 3:
                        break;
                    //我的电台
                    case 4:
                        break;
                    //我的MV
                    case 5:
                        break;

                }
            }
        });
        lv.setAdapter(mMusicDetailAdapter);
    }

    public static MusicFragment getInstance() {
        if (musicFragment == null) {
            musicFragment = new MusicFragment();
        }
        return musicFragment;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_creat:
                if (isCreatRotate) {
                    animCreat().start();
                    isCreatRotate = false;
                } else {
                    animCreat().reverse();
                    isCreatRotate = true;
                }
                break;
            case R.id.detail_collect:
                if (isCollectRotate) {
                    animCollect().start();
                    isCollectRotate = false;
                } else {
                    animCollect().reverse();
                    isCollectRotate = true;
                }
                break;
        }

    }

    public ObjectAnimator animCreat() {
        ObjectAnimator anim_creat = ObjectAnimator.ofFloat(creat_expand_img, "rotation", 90, 0, 0);
        anim_creat.setDuration(300);
        return anim_creat;
    }

    public ObjectAnimator animCollect() {
        ObjectAnimator anim_collect = ObjectAnimator.ofFloat(collect_expand_img, "rotation", 90, 0, 0);
        anim_collect.setDuration(300);
        return anim_collect;
    }
}
