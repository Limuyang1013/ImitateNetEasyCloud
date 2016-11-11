package com.stest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.stest.model.FolderModel;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;
import org.litepal.crud.DataSupport;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Limuyang on 2016/11/11.
 */

public class FolderAdapter extends SuperAdapter<FolderModel> {
    private List<MusicInfoDetail> music_info;

    public FolderAdapter(Context context, List<FolderModel> items, int layoutResId) {
        super(context, items, layoutResId);
        music_info = DataSupport.findAll(MusicInfoDetail.class);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, FolderModel item) {
        int num = 0;
        holder.setText(R.id.folder_path, item.getFolder_path());
        holder.setText(R.id.folder_name, item.getFolder_name());
        holder.setImageResource(R.id.folder_pic, R.drawable.list_icn_folder);
        for (int i = 0; i < music_info.size(); i++) {
            if (music_info.get(i).getUri().contains(item.getFolder_name()))
                num++;
        }
        holder.setText(R.id.folder_num, num + "首");
    }

    @Override
    public SuperViewHolder onCreate(View convertView, ViewGroup parent, int viewType) {
        final SuperViewHolder holder = super.onCreate(convertView, parent, viewType);
        holder.findViewById(R.id.popup_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return holder;
    }
}
