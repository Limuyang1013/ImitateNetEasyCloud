package com.stest.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Limuyang on 2016/7/11.
 */
public class CustomIndexListView extends View {

    //绘制背景
    private Paint mBackPaint;
    // 字母列表颜色
    int mLetterColor = 0xff666666;

    public CustomIndexListView(Context context) {
        this(context, null);
    }

    public CustomIndexListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomIndexListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }
}
