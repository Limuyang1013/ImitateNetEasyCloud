package com.stest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.stest.model.MusicInfoDetail;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Limuyang on 2016/8/15.
 */
public class NetEasyApplication extends LitePalApplication {
    public static Context context;
    private static Gson gson;
    private static NetEasyApplication instance;
    //private RefWatcher refWatcher;
    private static int MAX_MEM = (int) Runtime.getRuntime().maxMemory() / 3;
    private static RefWatcher sRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        frescoInit();
        LeakCanary.install(this);
        Fresco.initialize(this);
        instance = this;
        context=this;
    }
    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }

    public static NetEasyApplication getInstance() {
        return instance;
    }

    public static Gson gsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
    private ImagePipelineConfig getConfigureCaches(Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEM,// 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中图片的最大数量。
                MAX_MEM,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE / 10);// 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true);
        builder.setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams);
        return builder.build();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        //清空内存缓存（包括Bitmap缓存和未解码图片的缓存）
        imagePipeline.clearMemoryCaches();
    }
    private void frescoInit() {
        Fresco.initialize(this, getConfigureCaches(this));
    }


}
