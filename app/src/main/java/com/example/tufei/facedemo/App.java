package com.example.tufei.facedemo;

import android.content.Context;

import com.example.tufei.facedemo.di.AppComponent;
import com.example.tufei.facedemo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * @author wzh
 * @date 2017/12/28
 */
public class App extends DaggerApplication{
    public static Context context;
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static Context getContext() {
        return context;
    }
}
