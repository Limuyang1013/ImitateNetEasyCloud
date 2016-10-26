package com.stest.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stest.manage.MusicPlayer;
import com.stest.neteasycloud.R;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Random;

/**
 * Created by Limuyang on 2016/9/2.
 */

public class ListHeaderView extends LinearLayout {
    private Context context;
    private TextView textView;
    private ImageView imageView;
    private RelativeLayout play_all;
    private List<MusicInfoDetail> musicInfo;


    public ListHeaderView(Context context) {
        super(context);
        this.context = context;

        View v = LayoutInflater.from(this.context).inflate(R.layout.singles_head_layout, null);
        addView(v);
        textView = (TextView) v.findViewById(R.id.play_all_detail);
        imageView= (ImageView) findViewById(R.id.play_all_select);
        play_all= (RelativeLayout) findViewById(R.id.play_all);
        musicInfo= DataSupport.findAll(MusicInfoDetail.class);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置选择界面
            }
        });
        play_all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomIndex=new Random().nextInt(musicInfo.size());
                MusicPlayer.getPlayer().play(musicInfo.get(randomIndex));
                EventBus.getDefault().post(musicInfo.get(randomIndex));
                MusicPlayer.getPlayer().setNowPlaying(true);
            }
        });
    }

    public void setTextView(String text) {
        textView.setText(text);
    }
}
