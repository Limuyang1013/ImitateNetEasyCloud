package com.stest.neteasycloud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.DragViewAdapter;
import com.stest.view.DividerListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Limuyang on 2016/8/17.
 * 个性推荐页面改变布局
 */
public class RecommendPageItemChangeActivity extends AppCompatActivity {

    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.drag_lv)
    private DividerListView drag_lv;
    //适配器
    private DragViewAdapter mAdapter;
    private ActionBar actionBar;
    //数据
    private List<String> data;
    private static final String TAG = RecommendPageItemChangeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pageitemchange_layout);
        ViewUtils.inject(this);
        //初始化控件
        initWidgets();
    }

    private void initWidgets() {
        toolbar.setTitle(R.string.change_item);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.drag_lv_data)));
        mAdapter = new DragViewAdapter(this, data, R.layout.drag_item);
        drag_lv.setAdapter(mAdapter);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RecommendPageItemChangeActivity.class);
        context.startActivity(intent);
    }
}
