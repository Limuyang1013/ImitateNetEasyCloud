package com.stest.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.stest.manage.MusicPlayer;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/9/8.
 * 底部控制栏
 */

public class ControlBarFragment extends Fragment implements View.OnClickListener {
    private View v;
    @ViewInject(R.id.progress)
    private ProgressBar mProgress;
    @ViewInject(R.id.music_control_layout)
    private LinearLayout control_layout;
    @ViewInject(R.id.albumn_pic)
    private SimpleDraweeView albumn;
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

    public static ControlBarFragment newInstance() {
        return new ControlBarFragment();
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

    private void addView() {
        control_layout.setOnClickListener(this);
        playlist.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        mProgress.setMax(MusicPlayer.getPlayer().getDuration());
        mProgress.setProgress(MusicPlayer.getPlayer().getCurrentPosition());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //整个底部播放栏布局
            case R.id.music_control_layout:
                break;
            //播放列表
            case R.id.playlist_btn:
                break;
            //播放控制
            case R.id.play_btn:
                if (MusicPlayer.getPlayer().isNowPlaying()) {
                    play.setImageResource(R.drawable.pause_btn);
                    MusicPlayer.getPlayer().pause();
                    MusicPlayer.getPlayer().setNowPlaying(false);
                } else {
                    play.setImageResource(R.drawable.play_btn);
                    MusicPlayer.getPlayer().setNowPlaying(true);
                }
                break;
            //下一曲
            case R.id.next_btn:
                MusicPlayer.getPlayer().next();
                break;
        }

    }
}
