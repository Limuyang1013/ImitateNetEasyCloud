package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.AlbumAdapter;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.utils.SPStrListUtils;
import com.stest.view.DividerListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Limuyang on 2016/8/31.
 * 专辑
 */
public class AlbumsFragment extends Fragment {
    @ViewInject(R.id.lv)
    DividerListView lv;
    private AlbumAdapter mAdapter;
    private List<MusicInfoDetail> musicInfo;
    private List<String> album;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_albumns, container, false);
        ViewUtils.inject(this, v);
        initWidget();
        return v;
    }

    private void initWidget() {
        musicInfo = new ArrayList<>();
        musicInfo= DataSupport.findAll(MusicInfoDetail.class);

        mAdapter = new AlbumAdapter(getContext(), musicInfo, R.layout.album_item_layout);
        lv.setAdapter(mAdapter);
    }

}
