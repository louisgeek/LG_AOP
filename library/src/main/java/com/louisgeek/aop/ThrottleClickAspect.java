package com.louisgeek.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by louisgeek on 2019/12/13.
 */
@Aspect
public class ThrottleClickAspect {
    private static final String TAG = "ThrottleClickAspect";
    private static final int SKIP_DURATION = 300;
    //    private int mLastClickViewId;
    private long mLastClickTime;


    @Pointcut("execution(@com.louisgeek.aop.ThrottleClick * *(..))")
    public void pointcutThrottleClick() {
        Log.e(TAG, "pointcutThrottleClick: ");
    }


    @Around("pointcutThrottleClick()")
    public void aroundThrottleClick(ProceedingJoinPoint joinPoint) throws Throwable {
        //
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view == null) {
            return;
        }
        // 取出方法的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(ThrottleClick.class)) {
            return;
        }
        ThrottleClick throttleClick = method.getAnnotation(ThrottleClick.class);
        //
        if (throttleClick == null) {
            return;
        }
        int skipDuration = throttleClick.skipDuration();
        if (skipDuration < 0) {
            skipDuration = SKIP_DURATION;
        }
        if (System.currentTimeMillis() - mLastClickTime >= skipDuration) {
            //继续执行
            joinPoint.proceed();
            mLastClickTime = System.currentTimeMillis();
        } else {
            Log.e(TAG, "aroundThrottleClick: 重复点击");
        }
    }

}
