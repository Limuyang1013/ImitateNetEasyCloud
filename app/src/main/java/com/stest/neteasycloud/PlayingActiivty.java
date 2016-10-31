package com.stest.neteasycloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.utils.NetWorkUtils;
import com.stest.utils.ToastUtils;

/**
 * Created by Limuyang on 2016/10/26.
 * 播放音乐界面
 */

public class PlayingActiivty extends AppCompatActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar actionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_playing_music);
        setTranslucent(PlayingActiivty.this);
        ViewUtils.inject(this);
        //初始化
        initWidgets();
    }

    public static void start(Context context){
        Intent intent=new Intent(context,PlayingActiivty.class);
        context.startActivity(intent);
    }
    private void initWidgets(){

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
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //点击分享按钮
                    case R.id.action_share:
                        if (!NetWorkUtils.isNetworkConnected(PlayingActiivty.this)){
                            //没有网
                            ToastUtils.show(PlayingActiivty.this,getResources().getString(R.string.net_wrong), Toast.LENGTH_SHORT);
                        }else{
                            //分享到...
                        }
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music_playing_item,menu);
        return true;
    }

    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }
}
