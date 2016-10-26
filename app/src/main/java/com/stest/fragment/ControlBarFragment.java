package com.stest.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.manage.MusicPlayer;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.PlayingActiivty;
import com.stest.neteasycloud.R;
import com.stest.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Limuyang on 2016/9/8.
 * 底部控制栏
 */

public class ControlBarFragment extends Fragment implements View.OnClickListener {
    private final static String TAG = ControlBarFragment.class.getSimpleName();
    private View v;
    @ViewInject(R.id.progress)
    private ProgressBar mProgress;
    @ViewInject(R.id.bottom_layout)
    private RelativeLayout control_layout;
    //专辑封面
    @ViewInject(R.id.albumn_pic)
    private ImageView albumn;
    @ViewInject(R.id.song)
    private TextView song_txt;
    @ViewInject(R.id.singer)
    private TextView singer_txt;
    @ViewInject(R.id.playlist_btn)
    private ImageView playlist;
    @ViewInject(R.id.play_btn)
    private ImageView play;
    @ViewInject(R.id.next_btn)
    private ImageView next;
    @ViewInject(R.id.bottom_layout)
    private RelativeLayout bottom_layout;

    public static ControlBarFragment newInstance() {
        return new ControlBarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            ViewUtils.inject(this, v);
            return v;
        }
        v = inflater.inflate(R.layout.bottom_music_layout, container, false);
        ViewUtils.inject(this, v);
        addView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        // 在onActivityCreated()和onStart()之间调用
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            song_txt.setText(savedInstanceState.getString("song_txt"));
            Log.d(TAG, "onSaveInstanceState" + song_txt.getText().toString());
            singer_txt.setText(savedInstanceState.getString("singer_txt"));
            song_txt.setText(savedInstanceState.getString("song_txt"));
            play.setImageResource(savedInstanceState.getBoolean("isPlaying") ? R.drawable.pause_btn : R.drawable.play_btn);
        }
    }

    private void addView() {
        control_layout.setOnClickListener(this);
        playlist.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        play.setImageResource(MusicPlayer.getPlayer().isNowPlaying() ? R.drawable.pause_btn : R.drawable.play_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //整个底部播放栏布局
            case R.id.bottom_layout:
                PlayingActiivty.start(getContext());
                break;
            //播放列表
            case R.id.playlist_btn:
                break;
            //播放控制
            case R.id.play_btn:
                //如果正在播放
                if (MusicPlayer.getPlayer().isNowPlaying()) {
                    play.setImageResource(R.drawable.play_btn);
                    MusicPlayer.getPlayer().setNowPlaying(false);
                    MusicPlayer.getPlayer().pause();
                } else if (!MusicPlayer.getPlayer().isNowPlaying()) {
                    play.setImageResource(R.drawable.pause_btn);
                    MusicPlayer.getPlayer().setNowPlaying(true);
                    MusicPlayer.getPlayer().resume();
                }
                break;
            //下一曲
            case R.id.next_btn:
                mProgress.setProgress(0);
                MusicPlayer.getPlayer().setNowPlaying(true);
                play.setImageResource(R.drawable.pause_btn);
                MusicPlayer.getPlayer().next();
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateUI(final MusicInfoDetail info) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                song_txt.setText(info.getTitle());
                singer_txt.setText(info.getArtist());
                play.setImageResource(R.drawable.pause_btn);
            }
        }, 20);
        //加载专辑图片
        Glide.with(getActivity())
                .load(info.getCoverUri())
                .placeholder(R.drawable.placeholder_disk_210)
                .error(R.drawable.placeholder_disk_210)
                .crossFade(20)
                .into(albumn);
        mProgress.setMax((int) info.getDuration());
        mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if (MusicPlayer.getPlayer().isNowPlaying()) {
                    mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
                } else {
                    mProgress.removeCallbacks(this);
                }

            }
        };

        timer.schedule(timerTask, 0, 50);
        bottom_layout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView被调用");
    }

    @Override
    public void onResume() {
        super.onResume();
        play.setImageResource(MusicPlayer.getPlayer().isNowPlaying() ? R.drawable.pause_btn : R.drawable.play_btn);
        mProgress.setMax(MusicPlayer.getPlayer().getDuration());
        mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if (MusicPlayer.getPlayer().isNowPlaying()) {
                    mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
                } else {
                    mProgress.removeCallbacks(this);
                }

            }
        };

        timer.schedule(timerTask, 0, 50);

    }
}

