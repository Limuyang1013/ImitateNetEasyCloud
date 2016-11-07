package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/8/31.
 * 文件夹
 */
public class FoldersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_folders, container, false);
        ViewUtils.inject(this, v);
        return v;
    }

}
