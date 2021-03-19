package com.louisgeek.aop;

import android.util.Log;
import android.view.View;

/**
 * Created by louisgeek on 2019/12/13.
 */
public class ThrottleClickProxy implements View.OnClickListener {
    private static final String TAG = "ThrottleClickProxy";
    private static final int SKIP_DURATION = 300;
    private long mLastClickTime;
    //
    private final View.OnClickListener mOriginListener;

    public ThrottleClickProxy(View.OnClickListener mOriginListener) {
        this.mOriginListener = mOriginListener;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - mLastClickTime >= SKIP_DURATION) {
            mOriginListener.onClick(v);
            mLastClickTime = System.currentTimeMillis();
        } else {
            Log.e(TAG, "ThrottleClickProxy: 重复点击");
        }
    }
}
