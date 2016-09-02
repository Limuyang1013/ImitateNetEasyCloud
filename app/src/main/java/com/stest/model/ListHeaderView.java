package com.stest.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stest.neteasycloud.R;

/**
 * Created by Limuyang on 2016/9/2.
 */

public class ListHeaderView extends LinearLayout {
    private Context context;
    private TextView textView;

    public ListHeaderView(Context context) {
        super(context);
        this.context = context;

        View v = LayoutInflater.from(this.context).inflate(R.layout.singles_head_layout, null);
        addView(v);
        textView = (TextView) v.findViewById(R.id.play_all_detail);
    }

    public void setTextView(String text) {
        textView.setText(text);
    }
}
