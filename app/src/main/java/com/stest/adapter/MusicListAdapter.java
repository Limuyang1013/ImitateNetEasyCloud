package com.stest.adapter;

import android.content.Context;

import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.utils.MusicUtils;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

/**
 * Created by Limuyang on 2016/9/1.
 */
public class MusicListAdapter extends SuperAdapter<MusicInfoDetail> {
    public MusicListAdapter(Context context, List<MusicInfoDetail> items, int layoutResId) {
        super(context, items, layoutResId);
        MusicUtils.scanMusic(getContext(), items);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MusicInfoDetail item) {

        holder.setText(R.id.song_title, item.getTitle());
        holder.setText(R.id.song_artist, item.getArtist());
        holder.setText(R.id.song_albumn, item.getAlbum());
        holder.setImageResource(R.id.dld_ok,R.drawable.list_icn_dld_ok);
        holder.setImageResource(R.id.hq,R.drawable.list_icn_hq_sml);
        holder.setImageResource(R.id.popup_menu,R.drawable.list_icn_more);
    }
}
