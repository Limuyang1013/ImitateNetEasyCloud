package com.stest.adapter;

import android.content.Context;

import com.stest.neteasycloud.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

/**
 * Created by Limuyang on 2016/8/18.
 */
public class DragViewAdapter extends SuperAdapter<String> {
    public DragViewAdapter(Context context, List<String> list, int layoutResId) {
        super(context, list, layoutResId);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, String item) {
        holder.setText(R.id.text, item);
        holder.setImageResource(R.id.move, R.drawable.list_icn_move);
    }
}
