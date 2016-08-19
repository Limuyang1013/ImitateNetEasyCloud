package com.stest.neteasycloud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.SimpleFloatViewManager;

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
    private DragSortListView drag_lv;
    @ViewInject(R.id.drag_restore_txt)
    private TextView drag_txt;
    //适配器
    private ArrayAdapter<String> mAdapter;
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
        mAdapter = new ArrayAdapter<>(this, R.layout.drag_item, R.id.text, data);
        drag_lv.setAdapter(mAdapter);
        drag_lv.setDropListener(onDrop);
        drag_lv.setRemoveListener(onRemove);
        //显示顶部横线
        drag_lv.addHeaderView(new ViewStub(this));
        drag_lv.addFooterView(new ViewStub(this));

        DragSortController controller = new DragSortController(drag_lv);
        controller.setDragHandleId(R.id.move);
        //controller.setClickRemoveId(R.id.);
        controller.setRemoveEnabled(false);
        controller.setSortEnabled(true);
        controller.setDragInitMode(1);

        drag_lv.setFloatViewManager(controller);
        drag_lv.setOnTouchListener(controller);
        drag_lv.setDragEnabled(true);

        //防止拖拽过程中ListView背景变黑
        SimpleFloatViewManager simpleFloatViewManager = new SimpleFloatViewManager(drag_lv);
        simpleFloatViewManager.setBackgroundColor(Color.TRANSPARENT);
        drag_lv.setFloatViewManager(simpleFloatViewManager);

        //添加下划线
        drag_txt.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RecommendPageItemChangeActivity.class);
        context.startActivity(intent);
    }

    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {
            if (from != to) {
                String item = mAdapter.getItem(from);
                mAdapter.remove(item);
                mAdapter.insert(item, to);
            }
        }
    };
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            mAdapter.remove(mAdapter.getItem(which));
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
