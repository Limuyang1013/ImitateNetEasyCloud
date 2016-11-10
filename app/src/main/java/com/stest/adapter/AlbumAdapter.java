package com.stest.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stest.NetEasyApplication;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Limuyang on 2016/11/10.
 * 专辑Adapter
 */

public class AlbumAdapter extends SuperAdapter<MusicInfoDetail> {
    private ImageView album_Pic;
    public AlbumAdapter(Context context, List<MusicInfoDetail> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MusicInfoDetail item) {
        album_Pic=new ImageView(getContext());
        album_Pic=holder.getView(R.id.albumn_pic);
        Glide.with(getContext())
                .load(item.getCoverUri())
                .placeholder(R.drawable.placeholder_disk_210)
                .error(R.drawable.placeholder_disk_210)
                .crossFade()
                .into(album_Pic);
        holder.setText(R.id.albumn_name,item.getAlbum());
        List<MusicInfoDetail> num = DataSupport.where("album = ?", item.getAlbum()).find(MusicInfoDetail.class);
        holder.setText(R.id.song_num,num.size()+"首");
        holder.setText(R.id.singer_name,item.getArtist());
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
