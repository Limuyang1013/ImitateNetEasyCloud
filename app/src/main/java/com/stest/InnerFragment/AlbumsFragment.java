package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.AlbumAdapter;
import com.stest.model.AlbumModel;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashSet;
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
    private List<AlbumModel> albumModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_albumns, container, false);
        ViewUtils.inject(this, v);
        initWidget();
        return v;
    }

    private void initWidget() {
        albumModels=new ArrayList<>();
        musicInfo = new ArrayList<>();
        musicInfo= DataSupport.findAll(MusicInfoDetail.class);
        for (int i=0;i<musicInfo.size();i++){
            AlbumModel model=new AlbumModel();
            model.setAlbum(musicInfo.get(i).getAlbum());
            model.setArtist(musicInfo.get(i).getArtist());
            model.setCoverUri(musicInfo.get(i).getCoverUri());
            albumModels.add(model);
        }
        List<AlbumModel> data=new ArrayList<>(new HashSet<>(albumModels));
        mAdapter = new AlbumAdapter(getContext(), data, R.layout.album_item_layout);
        lv.setAdapter(mAdapter);
    }

}
