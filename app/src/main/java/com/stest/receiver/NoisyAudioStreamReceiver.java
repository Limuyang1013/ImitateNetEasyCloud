package com.stest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.stest.constant.Actions;
import com.stest.service.MusicPlayService;

/**
 * Created by Limuyang on 2016/11/8.
 */

public class NoisyAudioStreamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, MusicPlayService.class);
        serviceIntent.setAction(Actions.ACTION_MEDIA_PLAY_PAUSE);
        context.startService(serviceIntent);
    }
}