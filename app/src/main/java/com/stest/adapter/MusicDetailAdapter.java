package com.stest.adapter;

import android.content.Context;

import org.byteam.superadapter.SuperAdapter;

import java.util.List;

/**
 * Created by Limuyang on 2016/8/29.
 */
public class MusicDetailAdapter extends SuperAdapter {

    public MusicDetailAdapter(Context context, List items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(Object holder, int viewType, int layoutPosition, Object item) {

    }
}
