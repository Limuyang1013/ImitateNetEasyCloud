package com.stest.InnerFragment;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    //更改布局
    private LinearLayout item_change;
    //动态添加布局
    private LinearLayout dynamic_layout;
    //轮播图
    private String[] images;
    private static final String URL_1 = "http://p4.music.126.net/yuIj6uoIjQtK0GlSgt6KVg==/3252355409837033.jpg";
    private static final String URL_2 = "http://p3.music.126.net/9bb79OKLW8Re07TBSyAFwQ==/3412884115270589.jpg";
    private static final String URL_3 = "http://p3.music.126.net/pwbDutfRLFjHO21rOBj2SQ==/1401877339824820.jpg";
    private static final String URL_4 = "http://p3.music.126.net/VZVdskyRJX_P-5CXsbZQdQ==/1369991502537578.jpg";
    private static final String URL_5 = "http://p4.music.126.net/J0ENITf4Hb4k6WanIFaqig==/3418381661746316.jpg";
    private static final String URL_6 = "http://p4.music.126.net/2Q3WlnpBryOEU3BxnwHi1Q==/3413983625400914.jpg";
    private static final String URL_7 = "http://p3.music.126.net/150XRACsO8Arr4VfsRZyIQ==/3413983627049741.jpg";

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
        daily_text = findViewById(R.id.daily_text);
        daily_btn = findViewById(R.id.daily_btn);
        daily_text.setText(getDate());
        images = new String[]{URL_1, URL_2, URL_3, URL_4, URL_5, URL_6, URL_7};
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

}
