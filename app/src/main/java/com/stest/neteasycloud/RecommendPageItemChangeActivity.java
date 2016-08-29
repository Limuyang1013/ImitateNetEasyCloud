package com.stest.neteasycloud;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.SimpleFloatViewManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.stest.utils.SPStrListUtils;
import com.stest.utils.SPUtils;

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
    private List<String> order_data;
    private static final String TAG = RecommendPageItemChangeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pageitemchange_layout);
        ViewUtils.inject(this);
        //初始化控件
        initWidgets();
        applyKitKatTranslucency();
    }


    private void initWidgets() {
        boolean original = SPUtils.getValue(this, TAG, "ORINGAL", false);
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
        order_data = new ArrayList<>();
        SPStrListUtils.remove(this, "ORDER");
        SPStrListUtils.putStrListValue(this, "ORDER", data);
        if (!SPStrListUtils.getStrListValue(this, "DRAG_ORDER").isEmpty() && !original) {
            mAdapter = new ArrayAdapter<>(this, R.layout.drag_item, R.id.text, SPStrListUtils.getStrListValue(this, "DRAG_ORDER"));
        } else {
            mAdapter = new ArrayAdapter<>(this, R.layout.drag_item, R.id.text, SPStrListUtils.getStrListValue(this, "ORDER"));
        }
        drag_lv.setAdapter(mAdapter);
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

        drag_lv.setDragSortListener(new DragSortListView.DragSortListener() {
            @Override
            public void drag(int i, int i1) {

            }

            @Override
            public void remove(int which) {

                mAdapter.remove(mAdapter.getItem(which));
            }

            @Override
            public void drop(int from, int to) {
                if (from != to) {
                    String item = mAdapter.getItem(from);
                    mAdapter.remove(item);
                    mAdapter.insert(item, to);
                }
                order_data.clear();
                for (int i = 0; i < 5; i++) {
                    order_data.add(mAdapter.getItem(i));
                }
                SPStrListUtils.remove(RecommendPageItemChangeActivity.this, "DRAG_ORDER");
                SPStrListUtils.putStrListValue(RecommendPageItemChangeActivity.this, "DRAG_ORDER", order_data);
                SPUtils.putValue(RecommendPageItemChangeActivity.this, TAG, "ORINGAL", false);
            }

        });
        drag_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    mAdapter.remove(data.get(i));
                    mAdapter.insert(data.get(i), i);
                    mAdapter.notifyDataSetChanged();
                    SPUtils.putValue(RecommendPageItemChangeActivity.this, TAG, "ORINGAL", true);
                }

            }
        });
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, RecommendPageItemChangeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    private void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.themeColor);//通知栏所需颜色
        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
