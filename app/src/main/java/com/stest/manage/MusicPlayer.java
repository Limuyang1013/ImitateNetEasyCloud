package com.stest.manage;

import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.stest.NetEasyApplication;
import com.stest.model.MusicInfoDetail;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Limuyang on 2016/9/6.
 */

public class MusicPlayer implements OnCompletionListener {
    private static MusicPlayer player = new MusicPlayer();

    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private List<MusicInfoDetail> mQueue;
    private int mQueueIndex;
    private PlayMode mPlayMode;
    private boolean isNowPlaying;
    private MusicInfoDetail mNextSong;
    private MusicInfoDetail mPrevSone;


    private enum PlayMode {
        LOOP, RANDOM, REPEAT
    }

    public static MusicPlayer getPlayer() {
        return player;
    }

    public static void setPlayer(MusicPlayer player) {
        MusicPlayer.player = player;
    }

    public MusicPlayer() {

        mMediaPlayer = new ManagedMediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);

        mQueue = new ArrayList<>();
        mQueueIndex = 0;

        mPlayMode = PlayMode.RANDOM;

        mNextSong = new MusicInfoDetail();
        mPrevSone = new MusicInfoDetail();
    }

    public void setQueue(List<MusicInfoDetail> queue, int index) {
        mQueue = queue;
        mQueueIndex = index;
        play(getNowPlaying());
    }

    public void play(MusicInfoDetail detail) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(detail.getUri());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    public void resume() {
        mMediaPlayer.start();
    }

    public void next() {
        mNextSong = getNextSong();
        play(mNextSong);
        EventBus.getDefault().post(mNextSong);
        EventBus.getDefault().postSticky(mNextSong);
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }

    public void previous() {
        mPrevSone = getPreviousSong();
        play(mPrevSone);
        EventBus.getDefault().post(mPrevSone);
        EventBus.getDefault().postSticky(mPrevSone);
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }

    public void seekTo(int msec) {
        mMediaPlayer.seekTo(msec);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    private MusicInfoDetail getNowPlaying() {
        if (mQueue.isEmpty()) {
            return null;
        }
        return mQueue.get(mQueueIndex);
    }

    private MusicInfoDetail getNextSong() {
        if (mQueue.isEmpty()) {
            return null;
        }
        switch (mPlayMode) {
            case LOOP:
                return mQueue.get(getNextIndex());
            case RANDOM:
                return mQueue.get(getRandomIndex());
            case REPEAT:
                return mQueue.get(mQueueIndex);
        }
        return null;
    }

    private MusicInfoDetail getPreviousSong() {
        if (mQueue.isEmpty()) {
            return null;
        }
        switch (mPlayMode) {
            case LOOP:
                return mQueue.get(getPreviousIndex());
            case RANDOM:
                return mQueue.get(getRandomIndex());
            case REPEAT:
                return mQueue.get(mQueueIndex);
        }
        return null;
    }


    public int getCurrentPosition() {
        if (getNowPlaying() != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (getNowPlaying() != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }

    public PlayMode getPlayMode() {
        return mPlayMode;
    }

    public void setPlayMode(PlayMode playMode) {
        mPlayMode = playMode;
    }

    private int getNextIndex() {
        mQueueIndex = (mQueueIndex + 1) % mQueue.size();
        return mQueueIndex;
    }

    private int getPreviousIndex() {
        mQueueIndex = (mQueueIndex - 1) % mQueue.size();
        return mQueueIndex;
    }

    private int getRandomIndex() {
        mQueueIndex = new Random().nextInt(mQueue.size()) % mQueue.size();
        return mQueueIndex;
    }

    private void release() {
        mMediaPlayer.release();
        mMediaPlayer = null;
        mContext = null;
    }


    public boolean isNowPlaying() {
        return isNowPlaying;
    }

    public void setNowPlaying(boolean nowPlaying) {
        isNowPlaying = nowPlaying;
    }


}