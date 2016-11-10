package com.stest.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Limuyang on 2016/10/21.
 */

public abstract class BaseFragment extends Fragment {
    //是否可见
    protected boolean isVisble;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;

    /**
     * 实现Fragment数据的缓加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble=true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }
    protected void onInVisible() {
    }
    protected void onVisible() {
        //加载数据
        loadData();
    }
    protected abstract void loadData();

}
