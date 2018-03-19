package com.example.tufei.facedemo.utils;

import org.robolectric.shadows.ShadowLog;

/**
 * @author wzh
 * @date 2017/12/29
 */
public class Log {
    public static void out(){
        ShadowLog.stream = System.out;
    }
}
