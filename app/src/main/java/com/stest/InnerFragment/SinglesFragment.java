package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.neteasycloud.R;

// 单曲
public class SinglesFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.play_all_layout)
    private RelativeLayout play_layout;
    @ViewInject(R.id.play_all_select)
    private ImageView select;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v != null) {
            return v;
        }
        View v = inflater.inflate(R.layout.fragment_singles, container, false);
        ViewUtils.inject(this, v);
        initWidgets();
        return v;
    }

    private void initWidgets() {
        play_layout.setOnClickListener(this);
        select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //播放全部
            case R.id.play_all_layout:
                break;
            //选择操作
            case R.id.play_all_select:
                break;
        }
    }
}
