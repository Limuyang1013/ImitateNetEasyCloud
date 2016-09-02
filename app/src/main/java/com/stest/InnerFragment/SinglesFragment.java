package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.MusicListAdapter;
import com.stest.model.ListHeaderView;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;

import java.util.ArrayList;
import java.util.List;

// 单曲
public class SinglesFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.lv)
    private DividerListView lv;
    private View v;
    private MusicListAdapter mAdapter;
    private List<MusicInfoDetail> musicInfo;

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

    /**
     * 初始化
     */
    private void initWidgets() {
        musicInfo = new ArrayList<>();
        Log.d("wqeqw", musicInfo.size() + "");
        mAdapter = new MusicListAdapter(getContext(), musicInfo, R.layout.music_list_item_layout);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        ListHeaderView headerView = new ListHeaderView(getContext());
        headerView.setTextView("(" + musicInfo.size() + ")");
        mAdapter.addHeaderView(headerView);
        lv.setAdapter(mAdapter);
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
