package com.stest.InnerFragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.MusicListAdapter;
import com.stest.manage.PlayEvent;
import com.stest.model.ListHeaderView;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.service.MusicPlayService;
import com.stest.view.DividerListView;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

// 单曲
public class SinglesFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.lv)
    private DividerListView lv;
    private View v;
    private MusicListAdapter mAdapter;
    private List<MusicInfoDetail> musicInfo;
    private PlayEvent playEvent;
    private int currentlyPlayingPosition = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v != null) {
            return v;
        }
        v = inflater.inflate(R.layout.fragment_singles, container, false);
        ViewUtils.inject(this, v);
        initWidgets();
        return v;
    }

    /**
     * 初始化
     */
    private void initWidgets() {
        musicInfo = new ArrayList<>();
        musicInfo= DataSupport.findAll(MusicInfoDetail.class);
        playEvent = new PlayEvent();
        mAdapter = new MusicListAdapter(getContext(), musicInfo, R.layout.music_list_item_layout);
        playEvent.setAction(PlayEvent.Action.PLAY);
        playEvent.setQueue(musicInfo);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    currentlyPlayingPosition = position - 1;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            playEvent.setCurrentIndex(currentlyPlayingPosition);
                            EventBus.getDefault().post(playEvent);
                        }
                    }).start();
                } else {
                    //跳转
                }
            }
        });
        ListHeaderView headerView = new ListHeaderView(getContext());
        headerView.setTextView("(" + musicInfo.size() + ")");
        lv.addHeaderView(headerView);
        lv.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context.startService(new Intent(context, MusicPlayService.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

}
