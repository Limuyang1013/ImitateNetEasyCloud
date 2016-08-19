package com.stest.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Limuyang on 2016/8/19.
 * 像素转换工具类
 */
public class SizeUtils {
    /**
     * dp转 px.
     *
     * @param dipValue
     * @return the int
     */
    public static int dp2px(double dipValue, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) (dipValue * (scale / 160) + 0.5f);
    }

    /**
     * px转dp.
     *
     * @param pxValue
     * @return the int
     */
    public static int px2dp(double pxValue, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) ((pxValue * 160) / scale + 0.5f);
    }

    /**
     * sp转px.
     *
     * @param spValue the value
     * @return the int
     */
    public static int sp2px(double spValue, Context mContext) {
        Resources r;
        if (mContext == null) {
            r = Resources.getSystem();
        } else {
            r = mContext.getResources();
        }
        double pxValue = spValue * r.getDisplayMetrics().scaledDensity;
        return (int) (pxValue + 0.5f);
    }

    /**
     * px转sp.
     *
     * @param pxValue the value
     * @return the int
     */
    public static int px2sp(double pxValue, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }
}
