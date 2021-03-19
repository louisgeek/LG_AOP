package com.louisgeek.aop;

import org.aspectj.lang.annotation.Aspect;

/**
 * Created by louisgeek on 2019/12/13.
 */
@Aspect
public class TestAspect {

    //所有的 android.view.View.OnClickListener.onClick 方法都会被织入，像第三方组件 RxView.clicks 里也会
 /*   @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void aroundViewOnClick(ProceedingJoinPoint joinPoint) throws Throwable {
        if (System.currentTimeMillis() - mLastClickTime >= SKIP_DURATION) {
            joinPoint.proceed();
            mLastClickTime = System.currentTimeMillis();
        } else {
            Log.e(TAG, "ThrottleClickAspect: 重复点击");
        }
    }
*/
//    @Around("execution(* android.app.Activity.setContentView(..))")
//    @Around("execution(* setContentView(..))")
   /* @Around("execution(* *..Activity.setContentView(..))")
    public void adviceSetContentView(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().toString();
        long time = System.currentTimeMillis();
        joinPoint.proceed();
        Log.e(TAG, name + " 耗时 " + (System.currentTimeMillis() - time));
    }*/
}
