package com.stest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class MusicFragment extends Fragment {
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            ViewUtils.inject(this, v);
            return v;
        }
        v = inflater.inflate(R.layout.music_fragment, container, false);
        ViewUtils.inject(this, v);
        return v;
    }
}
