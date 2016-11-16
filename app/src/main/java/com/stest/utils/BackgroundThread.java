package com.stest.utils;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by Limuyang on 2016/11/16.
 */

public class BackgroundThread extends HandlerThread {
    private static BackgroundThread mInstance;
    private static Handler mHandler;

    public BackgroundThread() {
        super("ThreadName", android.os.Process.THREAD_PRIORITY_DEFAULT);
    }

    public static void prepareThread() {
        if (mInstance == null) {
            mInstance = new BackgroundThread();
            // 创建HandlerThread后一定要记得start()
            mInstance.start();
            // 获取HandlerThread的Looper,创建Handler，通过Looper初始化
            mHandler = new Handler(mInstance.getLooper());
        }
    }

    /**
     * 如果需要在后台线程做一件事情，那么直接调用post方法，使用非常方便
     */
    public static void post(final Runnable runnable) {
        mHandler.post(runnable);
    }

    public static void postDelayed(final Runnable runnable, long nDelay) {
        mHandler.postDelayed(runnable, nDelay);
    }

    /**
     * 退出HandlerThread
     */
    public static void destroyThread() {
        if (mInstance != null) {
            mInstance.quit();
            mInstance = null;
            mHandler = null;
        }
    }
}