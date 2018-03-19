package com.example.tufei.facedemo.utils;

import com.example.tufei.facedemo.di.AppComponent;
import com.example.tufei.facedemo.di.DaggerAppComponent;

import org.robolectric.RuntimeEnvironment;

/**
 * @author wzh
 * @date 2017/12/29
 */
public class Dagger {
    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().application(RuntimeEnvironment.application).build();
    }
}
