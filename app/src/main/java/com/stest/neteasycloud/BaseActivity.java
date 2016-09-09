package com.stest.neteasycloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.stest.fragment.ControlBarFragment;

/**
 * Created by Limuyang on 2016/8/15.
 */
public class BaseActivity extends AppCompatActivity {
    private ControlBarFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示底部控制栏
        showBottomControlBar();
    }

    private void showBottomControlBar() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment == null) {
            fragment = ControlBarFragment.newInstance();
            ft.add(R.id.bottom_container, fragment).commit();
        }
    }


}
