package com.stest.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.stest.NetEasyApplication;
import com.stest.manage.MusicPlayer;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.PlayingActivity;
import com.stest.neteasycloud.R;
import com.stest.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
                Intent intent = new Intent(NetEasyApplication.context, PlayingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                NetEasyApplication.context.startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgress.setProgress(0);
                        MusicPlayer.getPlayer().setNowPlaying(true);
                        play.setImageResource(R.drawable.pause_btn);
                        MusicPlayer.getPlayer().next();
                    }
                }, 20);
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateUI(final MusicInfoDetail info) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SPUtils.putValue(getContext(), "COVER_PATH", "PATH", info.getCoverUri());
                SPUtils.putValue(getContext(), "COVER_TITLE", "TITLE", info.getTitle());
                SPUtils.putValue(getContext(), "COVER_ARTIST", "ARTIST", info.getArtist());
                song_txt.setText(info.getTitle());
                singer_txt.setText(info.getArtist());
                play.setImageResource(R.drawable.pause_btn);
            }
        }, 20);
        Glide.with(getActivity())
                .load(info.getCoverUri())
                .placeholder(R.drawable.placeholder_disk_210)
                .error(R.drawable.placeholder_disk_210)
                .crossFade(20)
                .into(albumn);
        mProgress.setMax((int) info.getDuration());
        mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
        bottom_layout.setVisibility(View.VISIBLE);
        mProgress.postDelayed(runnable, 50);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (MusicPlayer.getPlayer().isNowPlaying()) {
                mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
                mProgress.postDelayed(runnable, 50);
            }
        }
    };

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
        mProgress.postDelayed(runnable, 50);
        String coverImgPath = SPUtils.getValue(getContext(), "COVER_PATH", "PATH", null);
        String coverTitle = SPUtils.getValue(getContext(), "COVER_TITLE", "TITLE", null);
        String coverArtist = SPUtils.getValue(getContext(), "COVER_ARTIST", "ARTIST", null);
        if (coverImgPath == null) {
            albumn.setImageResource(R.drawable.placeholder_disk_210);
        } else {
            Glide.with(getContext())
                    .load(coverImgPath)
                    .placeholder(R.drawable.placeholder_disk_210)
                    .error(R.drawable.placeholder_disk_210)
                    .into(albumn);
        }
        song_txt.setText(coverTitle);
        singer_txt.setText(coverArtist);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
