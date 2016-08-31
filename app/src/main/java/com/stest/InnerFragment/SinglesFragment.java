package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.stest.neteasycloud.R;

// 单曲
public class SinglesFragment extends Fragment {

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v != null) {
            return v;
        }
        View v = inflater.inflate(R.layout.fragment_singles, container, false);
        ViewUtils.inject(this, v);
        return v;
    }

}
