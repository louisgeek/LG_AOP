package com.louisgeek.aop;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.louisgeek.aop.tool.AspectTool;
import com.louisgeek.aop.tool.NetworkTool;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CheckNetworkAspect {
    private static final String TAG = "CheckNetworkAspect";

    @Pointcut("execution(@com.louisgeek.aop.CheckNetwork * *(..))")
    public void pointcutCheckNetwork() {
        Log.e(TAG, "pointcutCheckNetwork: ");
    }

    @Around("pointcutCheckNetwork()")
    public Object aroundCheckNetwork(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNetwork checkNetwork = signature.getMethod().getAnnotation(CheckNetwork.class);
        if (checkNetwork == null) {
            return null;
        }
        Context context = AspectTool.getContext(joinPoint.getThis());
        if (context == null) {
            return null;
        }
        String tip = checkNetwork.tip();
        if (TextUtils.isEmpty(tip)) {
            tip = "网络连接异常";
        }
        if (NetworkTool.isConnected(context)) {
            Log.e(TAG, "aroundCheckNetwork: 网络已连接 ");
            joinPoint.proceed();
        } else {
            Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();

        }
        return null;
    }
}






