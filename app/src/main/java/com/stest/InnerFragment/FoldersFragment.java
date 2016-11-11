package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.mobeta.android.dslv.DragSortListView;
import com.stest.adapter.FolderAdapter;
import com.stest.model.FolderModel;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Limuyang on 2016/8/31.
 * 文件夹
 */
public class FoldersFragment extends Fragment {

    @ViewInject(R.id.lv)
    DividerListView lv;
    private FolderAdapter mAdapter;
    private List<MusicInfoDetail> musicInfo;
    private List<FolderModel> folderModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singles, container, false);
        ViewUtils.inject(this, v);
        initWidget();
        return v;
    }

    private void initWidget() {
        musicInfo = new ArrayList<>();
        musicInfo = DataSupport.findAll(MusicInfoDetail.class);
        folderModels = new ArrayList<>();
        for (int i = 0; i < musicInfo.size(); i++) {
            FolderModel model = new FolderModel();
            //截取地址
            String combine = musicInfo.get(i).getUri().substring(0, musicInfo.get(i).getUri().lastIndexOf(File.separator));
            model.setFolder_path(combine.substring(0, combine.lastIndexOf(File.separator)));
            model.setFolder_name(combine.substring(combine.lastIndexOf(File.separator)));
            folderModels.add(model);
        }
        List<FolderModel> widthOutDup=new ArrayList<>(new HashSet<>(folderModels));
        mAdapter = new FolderAdapter(getContext(), widthOutDup, R.layout.folder_item_layout);
        lv.setAdapter(mAdapter);
    }


}
