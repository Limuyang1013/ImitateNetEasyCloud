package com.stest.adapter;

import android.content.Context;

import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.utils.MusicUtils;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Limuyang on 2016/8/29.
 */
public class MusicDetailAdapter extends SuperAdapter<String> {

    private int[] icn;
    private List<MusicInfoDetail> number;


    public MusicDetailAdapter(Context context, List<String> items, int layoutResIde) {
        super(context, items, layoutResIde);
        icn = new int[]{R.drawable.music_icn_local, R.drawable.music_icn_recent, R.drawable.music_icn_dld,
                R.drawable.music_icn_artist, R.drawable.music_icn_dj, R.drawable.music_icn_mv};
        number=new ArrayList<>();
        MusicUtils.scanMusic(getContext(), number);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, String item) {
        if (layoutPosition == 0) {
            holder.setText(R.id.detail_title, item);
            holder.setText(R.id.detail_number, number.size() > 0 ? "(" + number.size() + ")" : getContext().getResources().getString(R.string.detail_txt));
            holder.setImageResource(R.id.detail_icn, icn[layoutPosition]);
        } else {
            holder.setText(R.id.detail_title, item);
            holder.setText(R.id.detail_number, getContext().getResources().getString(R.string.detail_txt));
            holder.setImageResource(R.id.detail_icn, icn[layoutPosition]);
        }

    }
}

