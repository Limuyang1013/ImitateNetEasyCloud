package com.stest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.stest.model.ArtistInfo;
import com.stest.neteasycloud.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

/**
 * Created by Limuyang on 2016/11/9.
 */

public class SingerAdapter extends SuperAdapter<ArtistInfo> {

    public SingerAdapter(Context context, List<ArtistInfo> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ArtistInfo item) {
        holder.setText(R.id.song_artist, item.getArtist_name());
        holder.setText(R.id.song_num, item.getNumber_of_tracks() + " 首");

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
