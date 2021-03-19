package com.louisgeek.aop;

import android.util.Log;
import android.view.View;

/**
 * Created by louisgeek on 2016/12/13.
 */
public abstract class OnThrottleClickListener implements View.OnClickListener {
    private static final String TAG = "OnThrottleClickListener";
    private static final int SKIP_DURATION = 300;
    private long mLastClickTime;

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - mLastClickTime > SKIP_DURATION) {
            onThrottleClick(v);
            mLastClickTime = System.currentTimeMillis();
        } else {
            Log.e(TAG, "OnThrottleClickListener: 重复点击");
        }
    }

    protected abstract void onThrottleClick(View v);
}
