package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/8/31.
 * 歌手
 */
public class SingersFragment extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            return v;
        }
        View v = inflater.inflate(R.layout.fragment_singers, container, false);
        ViewUtils.inject(this, v);
        return v;
    }
}
