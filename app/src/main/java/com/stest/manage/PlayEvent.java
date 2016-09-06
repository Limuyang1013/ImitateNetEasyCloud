package com.stest.manage;

import com.stest.model.MusicInfoDetail;

import java.util.List;

/**
 * Created by Limuyang on 2016/9/6.
 */

public class PlayEvent {
    public enum Action {
        PLAY, STOP, RESUME, NEXT, PREVIOES, SEEK
    }

    private Action mAction;
    private MusicInfoDetail mSong;
    private List<MusicInfoDetail> mQueue;
    private int seekTo;
    private int position;

    public MusicInfoDetail getSong() {
        return mSong;
    }

    public void setSong(MusicInfoDetail song) {
        mSong = song;
    }

    public Action getAction() {
        return mAction;
    }

    public void setAction(Action action) {
        mAction = action;
    }

    public List<MusicInfoDetail> getQueue() {
        return mQueue;
    }

    public void setQueue(List<MusicInfoDetail> queue) {
        mQueue = queue;
    }

    public void setCurrentIndex(int currentPosition) {
        position = currentPosition;
    }

    public int getCurrentIndex() {
        return position;
    }

    public int getSeekTo() {
        return seekTo;
    }

    public void setSeekTo(int seekTo) {
        this.seekTo = seekTo;
    }
}
