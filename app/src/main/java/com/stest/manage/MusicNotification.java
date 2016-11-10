package com.stest.manage;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.stest.NetEasyApplication;
import com.stest.neteasycloud.PlayingActiivty;
import com.stest.neteasycloud.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Limuyang on 2016/11/9.
 * 通知栏音乐控制
 */

public class MusicNotification extends Notification {

    //饿汉单例加载
    private static MusicNotification instance=null;


    //通知
    private Notification notification=null;

    //管理通知
    private NotificationManager manager=null;

    //界面实现
    private Builder builder=null;
    private Context context;
    private RemoteViews remoteViews;

    //给Service发送广播
    private final String MUSIC_NOTIFICATION_ACTION_PLAY="ACTION_PLAY";
    private final String MUSIC_NOTIFICATION_ACTION_NEXT="ACTION_NEXT";
    private final String MUSIC_NOTIFICATION_ACTION_PREV="ACTION_PREV";
    private final String MUSIC_NOTIFICATION_ACTION_CLOSE="ACTION_CLOSE";
    private final String MUSIC_NOTIFICATION_ACTION_LOVE="ACTION_LOVE";

    private Intent play=null,next=null,prev=null,close=null,love=null;

    public void setContext(Context context){
        this.context=context;
    }
    private MusicNotification(){
        //初始化
        remoteViews=new RemoteViews(NetEasyApplication.getInstance().getPackageName(), R.layout.notification);
        builder=new Builder(context);
        //初始化控制Intent
        play=new Intent();
        play.setAction(MUSIC_NOTIFICATION_ACTION_PLAY);
        next=new Intent();
        next.setAction(MUSIC_NOTIFICATION_ACTION_NEXT);
        prev=new Intent();
        prev.setAction(MUSIC_NOTIFICATION_ACTION_PREV);
        close=new Intent();
        close.setAction(MUSIC_NOTIFICATION_ACTION_CLOSE);
        love=new Intent();
        love.setAction(MUSIC_NOTIFICATION_ACTION_LOVE);
    }

    public static MusicNotification getMusicNotification(){
    if (instance==null){
        instance=new MusicNotification();
    }
        return instance;
    }

    /**
     * 初始化通知
     * @SuppressLint("NewApi") 屏蔽一切因版本而导致新api中才能使用的方法报的android lint错误
     */
    @SuppressLint("NewApi")
    public Notification onCreatMusicNotification(){
        //点击跳转主界面
        Intent jumpIntent=new Intent(context, PlayingActiivty.class);
        PendingIntent jump=PendingIntent.getActivity(context,0,jumpIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notice,jump);
        //注册控制点击事件

        //上一曲
        PendingIntent intent_prev=PendingIntent.getBroadcast(context,1,prev,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.prev,intent_prev);
        //下一曲
        PendingIntent intent_next=PendingIntent.getBroadcast(context,2,next,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.next,intent_next);

        //播放控制
        PendingIntent intent_play=PendingIntent.getBroadcast(context,3,play,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setImageViewResource(R.id.play,MusicPlayer.getPlayer().isNowPlaying()?R.drawable.note_btn_pause:R.drawable.note_btn_play);
        remoteViews.setOnClickPendingIntent(R.id.play,intent_play);

        //收藏
        PendingIntent intent_love=PendingIntent.getBroadcast(context,4,love,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.play,intent_love);

        builder
        .setSmallIcon(R.drawable.stat_notify)
        .setWhen(System.currentTimeMillis());
        notification=builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;

        return notification;
    }


}
