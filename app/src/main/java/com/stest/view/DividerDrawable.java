package com.stest.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.stest.constant.DividerBounds;

/**
 * Created by Limuyang on 2016/8/17.
 */
public class DividerDrawable extends Drawable {

    private Drawable mDrawable;
    private DividerBounds mDividerBounds;
    private Rect mRectBounds;

    public DividerDrawable(Drawable drawable, DividerBounds dividerBounds) {
        this.mDrawable = drawable;
        this.mDividerBounds = dividerBounds;
    }

    @Override
    public void draw(Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mDrawable.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        if (mDividerBounds != null) {
            // build a recycled rect.
            if (mRectBounds == null) {
                mRectBounds = new Rect();
            }
            // update the value of rect
            mRectBounds = mDividerBounds.getBounds(mRectBounds, left, top, right, bottom);
            left = mRectBounds.left;
            top = mRectBounds.top;
            right = mRectBounds.right;
            bottom = mRectBounds.bottom;
        }
        mDrawable.setBounds(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth();
    }
}