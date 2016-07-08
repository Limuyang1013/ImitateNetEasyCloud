package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/7/8.
 */
public class DynamicFragment extends Fragment {
    @ViewInject(R.id.txt)
    private TextView txt;
    @ViewInject(R.id.nact_btn)
    private ImageButton nact_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dynamic_fragment, container, false);
        ViewUtils.inject(this, v);
        return v;
    }
}
