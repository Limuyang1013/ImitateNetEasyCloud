package com.stest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.MusicDetailAdapter;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;
import com.stest.view.NetEasyRefreshLayout;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View v;
    private static MusicFragment musicFragment;
    private MusicDetailAdapter mMusicDetailAdapter;
    //数据
    private List<String> data;
    @ViewInject(R.id.lv)
    private DividerListView lv;
    @ViewInject(R.id.detail_number)
    private TextView detail_number;
    @ViewInject(R.id.refresh)
    private NetEasyRefreshLayout refreshLayout;

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
        refreshLayout.setColorSchemeResources(R.color.themeColor);
        data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_icn_data)));
        mMusicDetailAdapter = new MusicDetailAdapter(getActivity(), data, R.layout.music_detail_item);
        //开启加载动画
        mMusicDetailAdapter.openLoadAnimation();
        mMusicDetailAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

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
}
