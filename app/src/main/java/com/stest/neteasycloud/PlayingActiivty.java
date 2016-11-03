package com.stest.neteasycloud;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.stest.model.MusicInfoDetail;
import com.stest.utils.NetWorkUtils;
import com.stest.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Limuyang on 2016/10/26.
 * 播放音乐界面
 */

public class PlayingActiivty extends AppCompatActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.needle)
    ImageView needle;
    @ViewInject(R.id.playSeekBar)
    SeekBar seekBar;
    @ViewInject(R.id.default_disk_img)
    //内部
    ImageView img_disk;
    @ViewInject(R.id.img_disc)
    //外边盘
    ImageView img_disc;
    @ViewInject(R.id.currentTime)
    TextView currentTime;
    @ViewInject(R.id.totalTime)
    TextView endTime;
    @ViewInject(R.id.song)
    TextView song_txt;
    @ViewInject(R.id.singer)
    TextView singer_txt;
    @ViewInject(R.id.play_back)
    ImageView play_back;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_playing);
        EventBus.getDefault().register(this);
        ViewUtils.inject(this);
        //初始化
        initWidgets();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PlayingActiivty.class);
        context.startActivity(intent);
    }

    private void initWidgets() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        }
        toolbar.inflateMenu(R.menu.music_playing_item);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    //点击分享按钮
                    case R.id.action_share:
                        if (!NetWorkUtils.isNetworkConnected(PlayingActiivty.this)) {
                            //没有网
                            ToastUtils.show(PlayingActiivty.this, getResources().getString(R.string.net_wrong), Toast.LENGTH_SHORT);
                        } else {
                            //分享到...
                        }
                        break;
                }
                return false;
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onStickyUpdateUI(final MusicInfoDetail info) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                song_txt.setText(info.getTitle());
                singer_txt.setText(info.getArtist());
                //加载专辑图片
                Glide.with(PlayingActiivty.this)
                        .load(info.getCoverUri())
                        .placeholder(R.drawable.placeholder_disk_play_song)
                        .error(R.drawable.placeholder_disk_play_song)
                        .centerCrop()
                        .crossFade(300)
                        .into(img_disk);

                //背景高斯模糊
                if (info.getCoverUri()!=null){
                    Glide.with(PlayingActiivty.this)
                            .load(info.getCoverUri())
                            .placeholder(R.drawable.placeholder_disk_play_song)
                            .error(R.drawable.placeholder_disk_play_song)
                            .centerCrop()
                            .crossFade(20)
                            .into(play_back);

                }else {
                    //默认背景
                    play_back.setImageResource(R.drawable.playpage_background);
                }
            }
        }, 20);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music_playing_item, menu);
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
