package com.example.tufei.facedemo.utils;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wzh
 * @date 2017/12/29
 */
public class RxJava {

    /**
     * 单元测试的时候，利用RxJavaPlugins将io线程转换为trampoline
     * trampoline应该是立即执行的意思（待商榷），替代了Rx1的immediate。
     */
    public static void asyncToSync() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }
}
