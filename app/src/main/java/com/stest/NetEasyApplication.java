package com.stest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Limuyang on 2016/8/15.
 */
public class NetEasyApplication extends Application {
    private static Gson gson;
    private static NetEasyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        LeakCanary.install(this);
        instance = this;

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


}
