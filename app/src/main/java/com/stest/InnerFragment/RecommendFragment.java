package com.stest.InnerFragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stest.NetEasyApplication;
import com.stest.neteasycloud.R;
import com.stest.neteasycloud.RecommendPageItemChangeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Limuyang on 2016/7/7.
 * 个性推荐
 */
public class RecommendFragment extends BaseInnerFragment implements View.OnClickListener {
    private Date date;
    private SimpleDateFormat dateFm;
    private TextView daily_text;
    private ImageButton daily_btn;
    private List<String> networkImages;
    private LayoutInflater mInflater;
    private ViewStub mStub;
    //更改布局
    private LinearLayout item_change;
    //动态添加布局
    private LinearLayout dynamic_layout;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.recomment_fragment;
    }


    //更改时间
    private String getDate() {
        date = new Date();
        dateFm = new SimpleDateFormat("dd");
        return dateFm.format(date);
    }


    @Override
    protected void initView() {
        super.initView();
        mStub = findViewById(R.id.recommend_stub);
        mStub.inflate();
        mInflater = LayoutInflater.from(getContext());
        daily_text = findViewById(R.id.daily_text);
        daily_btn = findViewById(R.id.daily_btn);
        daily_text.setText(getDate());
        item_change = findViewById(R.id.item_change);
        dynamic_layout = findViewById(R.id.dynamic_layout);
        daily_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    daily_btn.setBackgroundResource(R.drawable.recommend_icn_daily_prs);
                    daily_text.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    daily_btn.setBackgroundResource(R.drawable.recommend_icn_daily);
                    daily_text.setTextColor(Color.parseColor("#ffce3d3a"));
                }
                return false;
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        item_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_change:
                RecommendPageItemChangeActivity.start(getActivity());
                break;
            case R.id.daily_btn:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetEasyApplication.getRefWatcher().watch(this);
    }
}
