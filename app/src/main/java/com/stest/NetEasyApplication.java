package com.stest;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Limuyang on 2016/8/15.
 */
public class NetEasyApplication extends Application {
    private RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    public RequestQueue getmRequestQueue() {
        if (mQueue == null)
            mQueue = Volley.newRequestQueue(getApplicationContext());
        return mQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getmRequestQueue().add(request);
    }

}
