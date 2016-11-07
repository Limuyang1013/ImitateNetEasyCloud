package com.stest.InnerFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.NetEasyApplication;
import com.stest.constant.API;
import com.stest.json.OkHttp3Stack;
import com.stest.json.PicUrlInfo;
import com.stest.neteasycloud.R;
import com.stest.neteasycloud.RecommendPageItemChangeActivity;
import com.stest.utils.GlideImageLoader;
import com.stest.utils.HttpUtils;
import com.stest.utils.NetWorkUtils;
import com.stest.utils.SPStrListUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Limuyang on 2016/7/7.
 * 个性推荐
 */
public class RecommendFragment extends Fragment implements View.OnClickListener {
    private Date date;
    private SimpleDateFormat dateFm;
    @ViewInject(R.id.daily_text)
    private TextView daily_text;
    @ViewInject(R.id.daily_btn)
    private ImageButton daily_btn;
    private LayoutInflater mInflater;
    @ViewInject(R.id.banner)
    private Banner mBanner;
    private List<String> netImages;
    private List<String> cacheImages;
    //更改布局
    @ViewInject(R.id.item_change)
    private LinearLayout item_change;
    //动态添加布局
//    private LinearLayout dynamic_layout;
    //

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewUtils.inject(this, v);
            return v;
        }
        View v = inflater.inflate(R.layout.recomment_fragment, container, false);
        ViewUtils.inject(this, v);
        initView();
        setListener();
        return v;
    }


    //更改时间
    private String getDate() {
        date = new Date();
        dateFm = new SimpleDateFormat("dd");
        return dateFm.format(date);
    }


    protected void initView() {
        netImages = new ArrayList<>();
        cacheImages = new ArrayList<>();
//        localImages = new Integer[5];
//        localImages[0] = R.mipmap.first;
//        localImages[1] = R.mipmap.second;
//        localImages[2] = R.mipmap.third;
//        localImages[3] = R.mipmap.fourth;
//        localImages[4] = R.mipmap.five;
        mInflater = LayoutInflater.from(getContext());
        daily_text.setText(getDate());
//        dynamic_layout = findViewById(R.id.dynamic_layout);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(3000);
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
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Log.d("LoadView", "Net OK");
            SPStrListUtils.remove(getContext(), "PIC_URL");
            getUrlInfo();
        }else {
            if (SPStrListUtils.getStrListValue(getContext(), "PIC_URL").size() > 0) {
                Log.d("LoadView", "Net OK");
                cacheImages.clear();
                cacheImages = SPStrListUtils.getStrListValue(getContext(), "PIC_URL");
                mBanner.setImages(cacheImages).setImageLoader(new GlideImageLoader()).start();
            }
        }

    }


    public void getUrlInfo() {
        OkHttpClient okClient = new OkHttpClient.Builder().build();
        RequestQueue mQueue = Volley.newRequestQueue(NetEasyApplication.getInstance(), new OkHttp3Stack(okClient));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API.BANNER, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JsonArray array = HttpUtils.getResposeJsonObject(response).get("data").getAsJsonArray();
                PicUrlInfo info = NetEasyApplication.gsonInstance().fromJson(array.get(0), PicUrlInfo.class);
                List<PicUrlInfo.DataBean> data = info.getData();
                for (int i = 0; i < data.size(); i++) {
                    //获取所有图片
                    PicUrlInfo.DataBean bean = data.get(i);
                    netImages.add(bean.getPicUrl());
                }
                mBanner.setImages(netImages).setImageLoader(new GlideImageLoader()).start();
                SPStrListUtils.remove(getContext(), "PIC_URL");
                SPStrListUtils.putStrListValue(getContext(), "PIC_URL", netImages);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("VOLLEY ERROR", error.toString());
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    protected void setListener() {
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
    public void onStart() {
        super.onStart();
        Log.d("Recom---------", "onStart");
        mBanner.isAutoPlay(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Recom---------", "onStop");
        mBanner.isAutoPlay(false);
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Recom---------", "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Recom---------", "onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
