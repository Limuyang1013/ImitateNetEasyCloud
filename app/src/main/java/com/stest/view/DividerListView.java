package com.stest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.stest.neteasycloud.DividerBounds;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/8/17.
 * 实现ListView左右留白
 */
public class DividerListView extends ListView implements DividerBounds {

    private int mDividerPaddingLeft;
    private int mDividerPaddingRight;

    public DividerListView(Context context) {
        this(context, null);
    }

    public DividerListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DividerPadding, defStyle, 0);
        mDividerPaddingLeft = a.getDimensionPixelSize(R.styleable.DividerPadding_dividerPaddingLeft, 0);
        mDividerPaddingRight = a.getDimensionPixelSize(R.styleable.DividerPadding_dividerPaddingRight, 0);
        a.recycle();
    }

    @Override
    public void setDivider(Drawable divider) {
        if (divider != null) {
            divider = new DividerDrawable(divider, this);
        }
        super.setDivider(divider);
    }

    /**
     * Set padding displayed on left side of dividers.
     *
     * @param padding Padding value in pixels that will be applied to each end
     * @see #setDivider(Drawable)
     * @see #getDividerPaddingLeft()
     */
    public void setDividerPaddingLeft(int padding) {
        mDividerPaddingLeft = padding;
    }

    /**
     * Get the left padding size used to inset dividers in pixels
     *
     * @see #setDivider(Drawable)
     * @see #setDividerPaddingLeft(int)
     */
    public int getDividerPaddingLeft() {
        return mDividerPaddingLeft;
    }

    /**
     * Set padding displayed on right side of dividers.
     *
     * @param padding Padding value in pixels that will be applied to each end
     * @see #setDivider(Drawable)
     * @see #getDividerPaddingRight()
     */
    public void setDividerPaddingRight(int padding) {
        mDividerPaddingRight = padding;
    }

    /**
     * Get the right padding size used to inset dividers in pixels
     *
     * @see #setDivider(Drawable)
     * @see #setDividerPaddingRight(int)
     */
    public int getDividerPaddingRight() {
        return mDividerPaddingRight;
    }

    @Override
    public Rect getBounds(Rect rect, int left, int top, int right, int bottom) {
        left += mDividerPaddingLeft;
        right -= mDividerPaddingRight;
        rect.set(left, top, right, bottom);
        return rect;
    }
}
