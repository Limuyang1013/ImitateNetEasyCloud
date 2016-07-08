package com.stest.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Limuyang on 2016/7/8.
 * 解决SwipeRefreshLayout嵌套ViewPager滑动冲突问题
 */

public class NetEasyRefreshLayout extends SwipeRefreshLayout {
    private int mTouchSlop;
    // 上一次触摸时的X坐标
    private float mPrevX;
    private float mPrevY;
    //判断ViewPager是否滑动
    private boolean isSlid;

    public NetEasyRefreshLayout(Context context) {
        super(context);
    }

    public NetEasyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = event.getX();
                mPrevY = event.getY();
                isSlid = false;
                break;

            case MotionEvent.ACTION_MOVE:
                if (isSlid) {
                    return false;
                }
                final float eventX = event.getX();
                final float eventY = event.getY();
                float distanceX = Math.abs(eventX - mPrevX);
                float distanceY = Math.abs(eventY - mPrevY);
                if (distanceX > distanceY && distanceX > mTouchSlop + 10) {
                    isSlid = true;
                    return false;
                }
            case MotionEvent.ACTION_CANCEL:
                isSlid = false;
                break;
        }

        return super.onInterceptTouchEvent(event);
    }
}
