package com.stest.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stest.neteasycloud.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Limuayng on 2016/11/7.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).placeholder(R.drawable.placeholder_detail_radio_ban).into(imageView);
    }
}
